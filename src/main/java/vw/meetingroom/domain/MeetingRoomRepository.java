package vw.meetingroom.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MeetingRoomRepository extends Repository<MeetingRoom, Long> {
    Optional<MeetingRoom> findById(Long id);
    MeetingRoom save(MeetingRoom user);
}
