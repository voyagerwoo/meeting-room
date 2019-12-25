package vw.meetingroom.app;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import vw.meetingroom.app.exceptions.AlreadyReservedException;
import vw.meetingroom.app.exceptions.IllegalTimeRangeException;
import vw.meetingroom.app.exceptions.InvalidMeetingRoomIdException;
import vw.meetingroom.app.exceptions.InvalidUserIdException;
import vw.meetingroom.domain.Reservation;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        initData();
    }

    @Test
    void reserve_InvalidUserIdException() {
        Assertions.assertThatThrownBy(() ->
                reservationService.reserve(1000L, 10001L,
                        LocalDateTime.of(2019, 12, 25, 9, 0, 0), LocalDateTime.of(2019, 12, 25, 10, 0, 0))
        ).isExactlyInstanceOf(InvalidUserIdException.class);
    }

    @Test
    void reserve_InvalidMeetingRoomIdException() {
        Assertions.assertThatThrownBy(() ->
                reservationService.reserve(1001L, 1001L,
                        LocalDateTime.of(2019, 12, 25, 9, 0, 0), LocalDateTime.of(2019, 12, 25, 10, 0, 0))
        ).isExactlyInstanceOf(InvalidMeetingRoomIdException.class);
    }

    @Test
    void reserve_AlreadyReservedException() {
        Assertions.assertThatThrownBy(() ->
                reservationService.reserve(1002L, 10001L,
                        LocalDateTime.of(2019, 12, 26, 9, 0, 0), LocalDateTime.of(2019, 12, 26, 10, 0, 0))
        ).isExactlyInstanceOf(AlreadyReservedException.class);
    }

    @Test
    void reserve_IllegalTimeRangeException() {
        Assertions.assertThatThrownBy(() ->
                reservationService.reserve(1002L, 10001L,
                        LocalDateTime.of(2019, 12, 26, 9, 0, 0), LocalDateTime.of(2019, 12, 26, 9, 0, 0))
        ).isExactlyInstanceOf(IllegalTimeRangeException.class);
    }

    @Test
    void reserve_IllegalTimeRangeException2() {
        Assertions.assertThatThrownBy(() ->
                reservationService.reserve(1002L, 10001L,
                        LocalDateTime.of(2019, 12, 26, 10, 0, 0), LocalDateTime.of(2019, 12, 26, 9, 0, 0))
        ).isExactlyInstanceOf(IllegalTimeRangeException.class);
    }

    @Test
    void reserve() {
        Reservation reservation = reservationService.reserve(1002L, 10001L,
                LocalDateTime.of(2019, 12, 25, 9, 0, 0), LocalDateTime.of(2019, 12, 25, 10, 0, 0));

        Map<String, Object> map = jdbcTemplate.queryForMap("select * from reservation where id = ?", reservation.getId());
        assertAll(
                () -> assertThat((long) map.get("user_id")).isEqualTo(1002L),
                () -> assertThat((long) map.get("meeting_room_id")).isEqualTo(10001L),
                () -> assertThat((Timestamp) map.get("start_time")).isEqualTo(Timestamp.valueOf(LocalDateTime.of(2019, 12, 25, 9, 0, 0))),
                () -> assertThat((Timestamp) map.get("end_time")).isEqualTo(Timestamp.valueOf(LocalDateTime.of(2019, 12, 25, 10, 0, 0)))
        );

    }

    private void initData() {
        jdbcTemplate.execute(
                "SET REFERENTIAL_INTEGRITY FALSE;\n" +
                        "truncate table user;\n" +
                        "truncate table meeting_room;\n" +
                        "truncate table reservation;\n" +
                        "SET REFERENTIAL_INTEGRITY true;"+

                        "insert into user(id, name, dept) values (1001, 'voyagerwoo', 'Backend dev team');\n" +
                        "insert into user(id, name, dept) values (1002, 'humanbeing', 'frontend dev team');\n" +

                        "insert into meeting_room(id, name, size) values (10001, '도토리방', 8);\n" +
                        "insert into meeting_room(id, name, size) values (10002, '참깨방', 4);\n" +
                        "insert into meeting_room(id, name, size) values (10003, '참나무방', 12);\n" +

                        "insert into reservation(user_id, meeting_room_id, start_time, end_time) values (1001, 10001, '2019-12-26 09:00:00', '2019-12-26 10:00:00');\n" +
                        "insert into reservation(user_id, meeting_room_id, start_time, end_time) values (1001, 10002, '2019-12-27 09:00:00', '2019-12-27 10:00:00');\n" +
                        "insert into reservation(user_id, meeting_room_id, start_time, end_time) values (1001, 10002, '2019-12-28 09:00:00', '2019-12-28 10:00:00');\n" +
                        "insert into reservation(user_id, meeting_room_id, start_time, end_time) values (1002, 10001, '2019-12-27 09:00:00', '2019-12-27 10:00:00');");
    }
}