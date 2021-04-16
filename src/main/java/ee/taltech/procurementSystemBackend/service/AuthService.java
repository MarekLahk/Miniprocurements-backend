package ee.taltech.procurementSystemBackend.service;

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
            Person person = new Person();
            person.setEMail(username);
            person.setPersonName(fullName);
            person.setTimeOfRegister(LocalDateTime.now());
            Person per = personRepository.save(person);
            System.out.println(per.getEMail());
            employeeRepository.addEmployee(per.getPersonID());
        }
    }

    public String group1(Authentication authentication) {
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        String username = principal.getPreferredUsername();
        String fullName = principal.getFullName();
        return "Hello " + username + " your name is " + fullName;
    }
}
