package vw.meetingroom.query;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import vw.meetingroom.domain.MeetingRoom;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureDataJpa
@DataJpaTest
class MeetingRoomDaoTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MeetingRoomDao meetingRoomDao;

    @Test
    void findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("insert into meeting_room(id, name, size) values (10001, '도토리방', 8)");
        jdbcTemplate.execute("insert into meeting_room(id, name, size) values (10002, '참깨방', 4)");
        jdbcTemplate.execute("insert into meeting_room(id, name, size) values (10003, '참나무방', 12)");

        Page<MeetingRoom> result = meetingRoomDao.findAll(PageRequest.of(0, 10));

        System.out.println(result.getContent());
    }
}