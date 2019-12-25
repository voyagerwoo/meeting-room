package vw.meetingroom.gql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import vw.meetingroom.domain.MeetingRoom;
import vw.meetingroom.query.MeetingRoomDao;

import java.util.List;
import java.util.Optional;

@Component
public class MeetingRoomGqlQuery implements GraphQLQueryResolver {
    private final MeetingRoomDao meetingRoomDao;

    public MeetingRoomGqlQuery(MeetingRoomDao meetingRoomDao) {
        this.meetingRoomDao = meetingRoomDao;
    }

    public List<MeetingRoom> getMeetingRooms(int pageNo, int pageSize) {
        return meetingRoomDao.findAll(PageRequest.of(pageNo, pageSize))
                .getContent();
    }

    public Optional<MeetingRoom> getMeetingRoom(long id) {
        return meetingRoomDao.findById(id);
    }

}
