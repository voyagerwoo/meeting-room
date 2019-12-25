package vw.meetingroom.query.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import vw.meetingroom.query.MeetingRoomData;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureDataJpa
@MybatisTest
class MeetingRoomDataDaoTest {
    @Autowired
    MeetingRoomDataDao meetingRoomDataDao;

    @Test
    void findAvailableMeetingRoom1() {
        List<MeetingRoomData> result = meetingRoomDataDao.findAvailableMeetingRoom(
                LocalDateTime.of(2019, 12, 25, 9, 0, 0), LocalDateTime.of(2019, 12, 25, 11, 0, 0));

        System.out.println(result);

        assertThat(result).hasSize(3);
    }

    @Test
    void findAvailableMeetingRoom2() {
        List<MeetingRoomData> result = meetingRoomDataDao.findAvailableMeetingRoom(
                LocalDateTime.of(2019, 12, 26, 9, 0, 0), LocalDateTime.of(2019, 12, 26, 11, 0, 0));

        System.out.println(result);

        assertThat(result).hasSize(2);
    }
}