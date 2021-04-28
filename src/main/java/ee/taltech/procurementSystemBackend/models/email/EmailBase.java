package ee.taltech.procurementSystemBackend.models.email;

import org.thymeleaf.context.Context;

public interface EmailBase {

    Context buildContext();
}
