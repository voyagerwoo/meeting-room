package vw.meetingroom.app.exceptions;

public class AlreadyReservedException extends IllegalStateException implements ClientException {
    public AlreadyReservedException() {
        super("Already reserved.");
    }

}
