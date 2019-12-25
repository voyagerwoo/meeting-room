package vw.meetingroom.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    Optional<User> findById(Long id);
    User save(User user);
}
