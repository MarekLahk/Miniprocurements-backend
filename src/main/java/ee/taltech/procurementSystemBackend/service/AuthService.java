package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.models.Dto.EmployeeResponse;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.person.EmployeeRepository;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepository;
import ee.taltech.procurementSystemBackend.utils.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {

    private final PersonRepository personRepository;
    private final EmployeeRepository employeeRepository;
    private final AuthUtils authUtils;

    public void addNewEmployeeIfNeeded(Authentication authentication) {
        String[] usernameAndFullName = authUtils
                .getUsernameAndFullNameFormAuthentication(authentication);
        if (personRepository.findByeMail(usernameAndFullName[0]).isEmpty()) {
            Employee employee = new Employee();
            employee.setEMail(usernameAndFullName[0]);
            employee.setPersonName(usernameAndFullName[1]);
            employee.setTimeOfRegister(LocalDateTime.now());
            employeeRepository.save(employee);
        }
    }

    public String group1(Authentication authentication) {
        String[] usernameAndFullName = authUtils
                .getUsernameAndFullNameFormAuthentication(authentication);
        return "Hello " + usernameAndFullName[0] + " your name is " + usernameAndFullName[1];
    }

    public EmployeeResponse getEmployeeResponse(Authentication authentication) {
        return authUtils.getEmployeeResponse(authentication);
    }

    public String getRedirectUrl(String queryString) {
        return authUtils.getRedirectUrl(queryString);
    }
}
