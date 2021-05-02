package ee.taltech.procurementSystemBackend.exception;

public class ProcurementException extends RuntimeException {

    public ProcurementException() {
        super();
    }

    public ProcurementException(String message) {
        super(message);
    }
}
