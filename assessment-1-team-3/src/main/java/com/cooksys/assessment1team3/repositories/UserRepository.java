package com.cooksys.assessment1team3.repositories;

import com.cooksys.assessment1team3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // derived method
    User findByCredentialsUsername(String username);

    List<User> findAllByDeletedFalse();

    List<User> findAllByFollowersAndDeletedFalse(User user);

    List<User> findAllByFollowingAndDeletedFalse(User user);

}
