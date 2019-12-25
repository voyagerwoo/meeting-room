package vw.meetingroom.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import vw.meetingroom.query.MeetingRoomData;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Mapper
public interface MeetingRoomDataDao {

    List<MeetingRoomData> findAvailableMeetingRoom(@Param("startTime") LocalDateTime startTime,
                                                 @Param("endTime") LocalDateTime endTime);

}
