package dk.dindulk.oauth2.web;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller that can be used to check if the application is online (and healthy). This is required by load-balancers
 * to detect when the application is ready
 *
 * @author Klaus Groenbaek
 *         Created 10/06/15.
 */
@Controller
@Slf4j
public class PingController {

    @RequestMapping(method = RequestMethod.GET, value = "/ping")
    @ResponseBody
    public void ping(HttpServletRequest request) {
        log.debug("Ping request received from " + request.getRemoteAddr());
    }
}
