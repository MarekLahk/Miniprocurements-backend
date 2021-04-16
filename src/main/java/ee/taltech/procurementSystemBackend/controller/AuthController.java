package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("login")
    //@PreAuthorize("hasRole('ROLE_group1')")
    public String login(Authentication authentication) {
        authService.addNewEmployeeIfNeeded(authentication);
        return "<script>window.location.href = 'http://localhost:3000/';</script>";
    }


    @GetMapping("group1")
    //@PreAuthorize("hasRole('ROLE_group1')")
    public String group1(Authentication authentication) {
        return authService.group1(authentication);
    }

    @GetMapping("details")
    //@PreAuthorize("hasRole('ROLE_group1')")
    public DefaultOidcUser user(Authentication authentication) {
        return (DefaultOidcUser) authentication.getPrincipal();
    }
}
