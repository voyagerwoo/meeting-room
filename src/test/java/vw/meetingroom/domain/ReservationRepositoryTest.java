package vw.meetingroom.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureDataJpa
@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private DataSource dataSource;

    @Test
    void findAlreadyBookedReservation() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("insert into reservation(start_time, end_time, user_id, meeting_room_id) values ('2019-12-25 09:00:00', '2019-12-25 11:00:00', 1, 12)");
        jdbcTemplate.execute("insert into reservation(start_time, end_time, user_id, meeting_room_id) values ('2019-12-25 11:00:00', '2019-12-25 12:00:00', 2, 12)");
        jdbcTemplate.execute("insert into reservation(start_time, end_time, user_id, meeting_room_id) values ('2019-12-25 12:00:00', '2019-12-25 13:00:00', 2, 12)");

        List<Reservation> result = reservationRepository.findAlreadyBookedReservation(
                12L,
                LocalDateTime.of(2019, 12, 25, 11, 30, 0),
                LocalDateTime.of(2019, 12, 25, 13, 0, 0)
        );

        System.out.println(result);

        assertThat(result).hasSize(2);
    }
}