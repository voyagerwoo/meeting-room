package vw.meetingroom.app.exceptions;

public class InvalidUserIdException extends IllegalArgumentException implements ClientException {
    public InvalidUserIdException(Long userId) {
        super("Invalid user ID (" + userId + ").");
    }
}
