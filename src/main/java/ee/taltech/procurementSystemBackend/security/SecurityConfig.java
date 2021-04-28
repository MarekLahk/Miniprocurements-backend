package ee.taltech.procurementSystemBackend.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService;

    public SecurityConfig(OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService) {
        this.oidcUserService = oidcUserService;
    }

    // TODO: 28/04/2021 Revert changes
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().httpStrictTransportSecurity().disable()
                .and()
                .authorizeRequests().antMatchers("/api/*").permitAll();
//                .authorizeRequests()
//                .antMatchers("/api/*").hasRole("group1")
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint()
//                .oidcUserService(oidcUserService);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/api/public/bids/*");
    }
}

