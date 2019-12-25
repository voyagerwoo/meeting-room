package vw.meetingroom.app.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IllegalTimeRangeException extends IllegalArgumentException implements ClientException {
    public IllegalTimeRangeException(LocalDateTime startTime, LocalDateTime endTime) {
        super("Illegal time range: " +
                "startTime (" + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ")" +
                " ~ endTime (" + endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ")");
    }
}
