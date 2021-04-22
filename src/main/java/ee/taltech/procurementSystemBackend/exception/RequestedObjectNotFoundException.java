package ee.taltech.procurementSystemBackend.exception;

public class RequestedObjectNotFoundException extends RuntimeException {

    public RequestedObjectNotFoundException() {
    }

    public RequestedObjectNotFoundException(String message) {
        super(message);
    }
}
