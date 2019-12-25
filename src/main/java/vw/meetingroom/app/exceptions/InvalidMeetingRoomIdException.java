package vw.meetingroom.app.exceptions;

public class InvalidMeetingRoomIdException extends IllegalArgumentException implements ClientException {
    public InvalidMeetingRoomIdException(Long meetingRoomId) {
        super("Invalid meeting room ID (" + meetingRoomId + ").");
    }
}
