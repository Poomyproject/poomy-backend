package com.poomy.mainserver.user.repository;

import com.poomy.mainserver.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByGoogleEmail(String googleEmail);
    Optional<UserEntity> findByNickName(String nickName);

}
