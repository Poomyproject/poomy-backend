package com.poomy.mainserver.user.repository;

import com.poomy.mainserver.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByGoogleEmail(String googleEmail);
    Optional<User> findByNickName(String nickName);

}
