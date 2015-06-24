package dk.dindulk.oauth2.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller is not part of the server, it is included to make a standalone example.
 * This controller performs intercepts the redirect which is basically a callback to the client.
 *
 * @author Klaus Groenbaek
 *         Created 23/06/15.
 */
@Controller
public class ClientController {

    // for testing grand to token
    @RequestMapping(method = RequestMethod.GET, value = "/redirect")
    public ModelAndView redirect(@RequestParam() Map<String, String> params) {

        String code = params.get("code");
        if (code != null) {
            // the response was a code, we not need to exchange this for an access_token
            return new ModelAndView("clientCode");
        } else {
            // the redirect contains the token in a fragment. Since fragments only exists in the browser the client
            // can access it in the backend. instead the client returns JavaScript which can extract it
            return new ModelAndView("clientToken");
        }
    }
}
