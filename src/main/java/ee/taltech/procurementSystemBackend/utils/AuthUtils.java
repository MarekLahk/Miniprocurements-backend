package ee.taltech.procurementSystemBackend.utils;

import ee.taltech.procurementSystemBackend.exception.RequestedObjectNotFoundException;
import ee.taltech.procurementSystemBackend.models.model.person.Person;
import ee.taltech.procurementSystemBackend.repository.person.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthUtils {

    private final PersonRepository personRepository;

    public Person getPersonToPerformOperations(String email) {
        return personRepository.findByPersonEmail(email)
                .orElseThrow(() -> new RequestedObjectNotFoundException("No such person"));
    }
}
