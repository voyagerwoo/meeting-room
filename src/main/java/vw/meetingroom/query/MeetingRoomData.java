package vw.meetingroom.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class MeetingRoomData {
    private Long id;
    private String name;
    private int size;
}
