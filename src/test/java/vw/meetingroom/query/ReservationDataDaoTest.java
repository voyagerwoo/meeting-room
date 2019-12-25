package vw.meetingroom.query;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import vw.meetingroom.query.mapper.ReservationDataDao;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

@AutoConfigureDataJpa
@MybatisTest
class ReservationDataDaoTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ReservationDataDao reservationDataDao;

    @Test
    void findByStartTimeBetween() {
        List<ReservationData> result = reservationDataDao.findByStartTimeBetween(LocalDate.of(2019, 12, 23), LocalDate.of(2019, 12, 30));

        System.out.println(result);
    }
}