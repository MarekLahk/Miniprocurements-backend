package ee.taltech.procurementSystemBackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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
    public String group1() {
        return "Hello Group 1 Users!";
    }
}
