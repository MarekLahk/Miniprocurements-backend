package ee.taltech.procurementSystemBackend.service;

import ee.taltech.procurementSystemBackend.models.Dto.EmployeeResponse;
import ee.taltech.procurementSystemBackend.models.model.person.Employee;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.person.EmployeeRepository;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepository;
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

    public void addNewEmployeeIfNeeded(Authentication authentication) {
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        String username = principal.getPreferredUsername();
        String fullName = principal.getFullName();
        if (personRepository.findByPersonEmail(username).isEmpty()) {
            Employee employee = new Employee();
            employee.setEMail(username);
            employee.setPersonName(fullName);
            employee.setTimeOfRegister(LocalDateTime.now());
            employeeRepository.save(employee);
        }
    }

    public String group1(Authentication authentication) {
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        String username = principal.getPreferredUsername();
        String fullName = principal.getFullName();
        return "Hello " + username + " your name is " + fullName;
    }

    public EmployeeResponse getEmployeeResponse(Authentication authentication) {
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        String username = principal.getPreferredUsername();
        String fullName = principal.getFullName();
        return new EmployeeResponse(username, fullName);
    }
}
