package ee.taltech.procurementSystemBackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TestController {

    @GetMapping("public")
    public String openEndpoint() {
        return "This is not secured endpoint.";
    }

    @GetMapping("group1")
    @PreAuthorize("hasRole('ROLE_group1')")
    public String group1(Authentication authentication) {
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        String username = principal.getPreferredUsername();
        return "Hello " + username;
    }

    @GetMapping("userdetails")
    @PreAuthorize("hasRole('ROLE_group1')")
    public DefaultOidcUser user(Authentication authentication) {
        return (DefaultOidcUser) authentication.getPrincipal();
    }
}
