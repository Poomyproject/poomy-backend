package com.poomy.mainserver.user.service;

import com.poomy.mainserver.user.entity.UserEntity;
import com.poomy.mainserver.user.repository.UserRepository;
import com.poomy.mainserver.user.type.UserRoleType;
import com.poomy.mainserver.util.exception.common.BError;
import com.poomy.mainserver.util.exception.common.CommonException;
import com.poomy.mainserver.util.exception.common.Error;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity loginGoogle(String googleEmail){
        Optional<UserEntity> userEntity = userRepository.findByGoogleEmail(googleEmail);
        if(userEntity.isEmpty()){
            UserEntity user = UserEntity.builder()
                    .googleEmail(googleEmail)
                    .role(UserRoleType.ROLE_USER)
                    .build();
            return userRepository.save(user);
        }
        return userEntity.get();
    }

    public UserEntity loginPoomy(String googleEmail){
        return userRepository.findByGoogleEmail(googleEmail)
                .orElseThrow(() -> new CommonException(BError.NOT_EXIST, "User"));
    }

}
