package ee.taltech.procurementSystemBackend.controller;

import ee.taltech.procurementSystemBackend.models.Dto.EmployeeResponse;
import ee.taltech.procurementSystemBackend.service.AuthService;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("login")
    //@PreAuthorize("hasRole('ROLE_group1')")
    public ResponseEntity<Void> login(Authentication authentication,
                                      @RequestParam(value = "redirect", required = false) String queryString) {
        authService.addNewEmployeeIfNeeded(authentication);

        //return "<script>window.location.href = 'http://localhost:3000/';</script>";
//        return ResponseEntity.status(HttpStatus.FOUND).location(
//                URI.create(authService.getRedirectUrl(queryString))).build();
        return ResponseEntity.status(HttpStatus.FOUND).location(
                URI.create(authService.getRedirectUrl(queryString))).build();
    }

    @Deprecated
    @GetMapping("group1")
    //@PreAuthorize("hasRole('ROLE_group1')")
    public String group1(Authentication authentication) {
        return authService.group1(authentication);
    }

    @Deprecated
    @GetMapping("details")
    //@PreAuthorize("hasRole('ROLE_group1')")
    public DefaultOidcUser user(Authentication authentication) {
        return (DefaultOidcUser) authentication.getPrincipal();
    }

    @GetMapping("employee")
    public EmployeeResponse getEmployeeResponse(Authentication authentication) {
        return authService.getEmployeeResponse(authentication);
    }
}
