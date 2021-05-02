package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.configuration.WebAuthConfig;
import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.Dto.EmployeeResponse;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class AuthUtils {

    private final PersonRepository personRepository;
    private final WebAuthConfig webAuthConfig;

    public String getRedirectUrl(String queryString) {
        String url = webAuthConfig.getRedirectUrl();
        if (queryString != null) {
            String query = queryString;
            if (queryString.startsWith("/")) {
                query = queryString.substring(1);
            }
            url += query;
        }
        return url;
    }

    public Person getPersonToPerformOperations(Authentication authentication) {
        String email = getUsernameAndFullNameFormAuthentication(authentication)[0];
        return getPersonByEmail(email);
    }

    private Person getPersonByEmail(String email) {
        return personRepository.findByPersonEmail(email)
                .orElseThrow(() -> new RequestedObjectNotFoundException("No such person"));
    }

    public String[] getUsernameAndFullNameFormAuthentication(Authentication authentication) {
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        String username = principal.getPreferredUsername();
        String fullName = principal.getFullName();
        return new String[]{username, fullName};
    }

    public EmployeeResponse getEmployeeResponse(Authentication authentication) {
        String[] usernameAndFullName = getUsernameAndFullNameFormAuthentication(authentication);
        Integer employeeId = getPersonByEmail(usernameAndFullName[0]).getId();
        return new EmployeeResponse(employeeId, usernameAndFullName[0], usernameAndFullName[1]);
    }
}
