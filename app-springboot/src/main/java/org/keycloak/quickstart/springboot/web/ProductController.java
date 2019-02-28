/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.quickstart.springboot.web;

import net.rossillo.spring.web.mvc.CacheControl;
import net.rossillo.spring.web.mvc.CachePolicy;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.common.util.KeycloakUriBuilder;
import org.keycloak.constants.ServiceUrlConstants;
import org.keycloak.quickstart.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
@CacheControl(policy = CachePolicy.NO_CACHE)
@EnableConfigurationProperties(KeycloakSpringBootProperties.class)
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private KeycloakSpringBootProperties keycloakProperties;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String handleCustomersRequest(Principal principal, Model model) {
        model.addAttribute("products", productService.getProducts());
        model.addAttribute("name", ((KeycloakPrincipal)((KeycloakAuthenticationToken) principal).getPrincipal()).getKeycloakSecurityContext().getIdToken().getName());
        String logoutUri = KeycloakUriBuilder.fromUri(keycloakProperties.getAuthServerUrl()).path(ServiceUrlConstants.TOKEN_SERVICE_LOGOUT_PATH)
                .queryParam("redirect_uri", "http://localhost:8080/").build(keycloakProperties.getRealm()).toString();
        model.addAttribute("logout", logoutUri);
        return "products";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String handleLogout() throws ServletException {
        request.logout();
        return "landing";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landing() throws ServletException {
        return "landing";
    }

}
