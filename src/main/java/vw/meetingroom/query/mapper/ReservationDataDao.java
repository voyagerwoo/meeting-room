package vw.meetingroom.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import vw.meetingroom.query.ReservationData;

import java.time.LocalDate;
import java.util.List;

@Repository
@Mapper
public interface ReservationDataDao {

    List<ReservationData> findByStartTimeBetween(@Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);

}
