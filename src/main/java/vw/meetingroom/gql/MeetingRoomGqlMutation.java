package vw.meetingroom.gql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import vw.meetingroom.app.ReservationService;
import vw.meetingroom.domain.Reservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MeetingRoomGqlMutation implements GraphQLMutationResolver {
    private final ReservationService reservationService;

    public MeetingRoomGqlMutation(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public Reservation reserve(long userId, long meetingRoomId, String startTime, String endTime) {
        return reservationService.reserve(userId, meetingRoomId,
                LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
