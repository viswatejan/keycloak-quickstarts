package com.jbhunt.photoz.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;

@Controller
public class FrontController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landing() throws ServletException {
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, 
                       @Value("${keycloak.auth-server-url}") String keycloakAuthServerUrl,
                       @Value("${photoz-restful-api.origin}") String apiOrigin) throws ServletException {
        model.addAttribute("keycloak_auth_server_url", keycloakAuthServerUrl);
        model.addAttribute("photoz_restful_api_origin", apiOrigin);
        return "index";
    }
}
