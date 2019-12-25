package vw.meetingroom.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
public class ReservationData {
    private Long id;
    private Long userId;
    private String userName;
    private String userDept;
    private Long meetingRoomId;
    private String meetingRoomName;
    private String meetingRoomSize;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
