package om.school.studentmanagement.Exceptions;

public class StudentNotFoundException extends Exception {
    private static final String DEFAULT_MESSAGE = "Student Not Found";
    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}