package com.poomy.mainserver.user.service;

import com.poomy.mainserver.user.dto.CustomUserDetails;
import com.poomy.mainserver.user.entity.UserEntity;
import com.poomy.mainserver.user.repository.UserRepository;
import com.poomy.mainserver.user.type.UserRoleType;
import com.poomy.mainserver.util.exception.common.BError;
import com.poomy.mainserver.util.exception.common.CommonException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Slf4j
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

    @Transactional
    public UserEntity registerNickName(Integer userId, String nickName){
        Optional<UserEntity> userEntity = userRepository.findByNickName(nickName);
        if(userEntity.isPresent()){
            throw new CommonException(BError.EXIST, "nickName");
        }
        UserEntity registeredUserEntity = userRepository.findById(userId)
                        .orElseThrow(() -> new CommonException(BError.NOT_EXIST, "user"));
        registeredUserEntity.setNickName(nickName);
        return registeredUserEntity;
    }

    @Transactional
    public Integer getUserId(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String googleEmail = Optional.ofNullable(customUserDetails.getUsername())
                .orElseThrow(()-> new CommonException(BError.NOT_VALID, "user"));
        return getUserEntityByGoogleEmail(googleEmail).getId();
    }

    @Transactional(readOnly = true)
    public UserEntity getUserEntityByGoogleEmail(String googleEmail) {
        return userRepository.findByGoogleEmail(googleEmail)
                .orElseThrow(() -> new CommonException(BError.NOT_EXIST, "user"));
    }

}
