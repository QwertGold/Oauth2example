package dk.dindulk.oauth2.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller is not part of the server, it is included to make a standalone example.
 * This controller performs intercepts the redirect which is basically a callback to the client.
 *
 * @author Klaus Groenbaek
 *         Created 23/06/15.
 */
@Controller
@RequestMapping(value = "/noauth")
public class ClientController {

    @Autowired
    ObjectMapper objectMapper;

    // for testing grant to token
    @RequestMapping(method = RequestMethod.GET, value = "/redirect")
    public ModelAndView redirect(@RequestParam() Map<String, String> params) {

        String code = params.get("code");
        if (code != null) {
            // the response was a code, we not need to exchange this for an access_token
            // the client then posts the code to /oauth/token (including basic auth) to get the
            // here we send the user to a view with a form, when the user submits it the request arrives in exchangeCode() below
            ModelAndView mv = new ModelAndView("clientCode");
            mv.addObject("code", code);
            return mv;
        } else {
            // the redirect contains the token in a fragment. Since fragments only exists in the browser the client
            // can access it in the backend. instead the client returns JavaScript which can extract it
            return new ModelAndView("clientToken");
        }
    }

    /**
     * The logic in this methods is what the client performs when the has gotten a code and need to exchange it to a token
     */
    @RequestMapping(value = "/exchangeCode")
    public ModelAndView exchangeCode(HttpServletRequest request, @RequestParam() Map<String, String> params) throws Exception {


        // If the client is Spring one may use OAuth2RestTemplate, here the raw template is used here to show what happens

        String clientId = params.get(OAuth2Utils.CLIENT_ID);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
        // basic auth with blank password (unless you set the secret() field when you build the client in OAuthConfiguration)
        headers.set("Authorization", "Basic " + new String(Base64.encode((clientId + ":").getBytes())));

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.set(OAuth2Utils.CLIENT_ID, clientId);
        parameters.set(OAuth2Utils.GRANT_TYPE, params.get(OAuth2Utils.GRANT_TYPE));
        parameters.set("code", params.get("code"));

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(parameters, headers);
        RestTemplate template = new RestTemplate();
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/oauth/token";
        ResponseEntity<DefaultOAuth2AccessToken> responseEntity = template.postForEntity(url, entity, DefaultOAuth2AccessToken.class);
        DefaultOAuth2AccessToken body = responseEntity.getBody();
        String json = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(body);
        ModelAndView mv = new ModelAndView("token");
        mv.addObject("json", json);
        return mv;
    }

}
