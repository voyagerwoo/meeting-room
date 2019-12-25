package vw.meetingroom.app;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import vw.meetingroom.app.exceptions.AlreadyReservedException;
import vw.meetingroom.app.exceptions.IllegalTimeRangeException;
import vw.meetingroom.app.exceptions.InvalidMeetingRoomIdException;
import vw.meetingroom.app.exceptions.InvalidUserIdException;
import vw.meetingroom.domain.MeetingRoomRepository;
import vw.meetingroom.domain.Reservation;
import vw.meetingroom.domain.ReservationRepository;
import vw.meetingroom.domain.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    private final UserRepository userRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(UserRepository userRepository, MeetingRoomRepository meetingRoomRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.meetingRoomRepository = meetingRoomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    @CacheEvict(value = "findReservationsByStartTimeBetween", allEntries = true)
    public Reservation reserve(long userId, long meetingRoomId, LocalDateTime startTime, LocalDateTime endTime) {
        userRepository.findById(userId).orElseThrow(() -> new InvalidUserIdException(userId));
        meetingRoomRepository.findById(meetingRoomId).orElseThrow(() -> new InvalidMeetingRoomIdException(meetingRoomId));
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new IllegalTimeRangeException(startTime, endTime);
        }
        List<Reservation> alreadyBookedReservation = reservationRepository.findAlreadyBookedReservation(meetingRoomId, startTime, endTime);
        if (!alreadyBookedReservation.isEmpty()) {
            throw new AlreadyReservedException();
        }

        return reservationRepository.save(new Reservation(userId, meetingRoomId, startTime, endTime));
    }
}
