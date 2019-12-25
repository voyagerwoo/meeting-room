package vw.meetingroom.gql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import vw.meetingroom.domain.MeetingRoom;
import vw.meetingroom.query.MeetingRoomDao;
import vw.meetingroom.query.ReservationData;
import vw.meetingroom.query.mapper.ReservationDataDao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class MeetingRoomGqlQuery implements GraphQLQueryResolver {
    private final MeetingRoomDao meetingRoomDao;
    private final ReservationDataDao reservationDataDao;

    public MeetingRoomGqlQuery(MeetingRoomDao meetingRoomDao, ReservationDataDao reservationDataDao) {
        this.meetingRoomDao = meetingRoomDao;
        this.reservationDataDao = reservationDataDao;
    }

    public List<MeetingRoom> getMeetingRooms(int pageNo, int pageSize) {
        return meetingRoomDao.findAll(PageRequest.of(pageNo, pageSize))
                .getContent();
    }

    public Optional<MeetingRoom> getMeetingRoom(long id) {
        return meetingRoomDao.findById(id);
    }

    public List<ReservationData> findReservationsByStartTimeBetween(String startTime, String endTime) {
        return reservationDataDao.findByStartTimeBetween(LocalDate.parse(startTime), LocalDate.parse(endTime));
    }
}
