package vw.meetingroom.query;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import vw.meetingroom.domain.MeetingRoom;

import javax.sql.DataSource;

@AutoConfigureDataJpa
@DataJpaTest
class MeetingRoomDaoTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MeetingRoomDao meetingRoomDao;

    @Test
    void findAll() {
        Page<MeetingRoom> result = meetingRoomDao.findAll(PageRequest.of(0, 10));

        System.out.println(result.getContent());
    }
}