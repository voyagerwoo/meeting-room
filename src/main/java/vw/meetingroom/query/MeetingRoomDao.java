package vw.meetingroom.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import vw.meetingroom.domain.MeetingRoom;

import java.util.Optional;

public interface MeetingRoomDao extends Repository<MeetingRoom, Long> {
    Page<MeetingRoom> findAll(Pageable pageable);

    Optional<MeetingRoom> findById(Long id);
}
