package net.anas.jee_demo.Exception;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
                    super(message);
    }
}
