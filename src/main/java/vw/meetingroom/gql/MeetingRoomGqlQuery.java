package vw.meetingroom.gql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import vw.meetingroom.query.MeetingRoomData;
import vw.meetingroom.query.ReservationData;
import vw.meetingroom.query.mapper.MeetingRoomDataDao;
import vw.meetingroom.query.mapper.ReservationDataDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class MeetingRoomGqlQuery implements GraphQLQueryResolver {
    private final ReservationDataDao reservationDataDao;
    private final MeetingRoomDataDao meetingRoomDataDao;

    public MeetingRoomGqlQuery(ReservationDataDao reservationDataDao, MeetingRoomDataDao meetingRoomDataDao) {
        this.reservationDataDao = reservationDataDao;
        this.meetingRoomDataDao = meetingRoomDataDao;
    }

    @Cacheable(value = "findReservationsByStartTimeBetween")
    public List<ReservationData> findReservationsByStartTimeBetween(String startDate, String endDate) {
        return reservationDataDao.findByStartTimeBetween(LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    public List<MeetingRoomData> findAvailableMeetingRoom(String startTime, String endTime) {
        return meetingRoomDataDao.findAvailableMeetingRoom(
                LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
}
