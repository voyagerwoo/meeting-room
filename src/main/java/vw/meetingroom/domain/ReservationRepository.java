package vw.meetingroom.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends Repository<Reservation, Long> {
    Optional<Reservation> findById(Long id);
    Reservation save(Reservation reservation);

    @Query("select r from Reservation as r " +
            "where r.meetingRoomId = :meetingRoomId " +
            "and (r.startTime between :startTime and :endTime " +
            "   or r.endTime between :startTime and :endTime)")
    List<Reservation> findAlreadyBookedReservation(
            Long meetingRoomId,
            LocalDateTime startTime,
            LocalDateTime endTime);
}
