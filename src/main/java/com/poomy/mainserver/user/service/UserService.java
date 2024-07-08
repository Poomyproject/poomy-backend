package com.poomy.mainserver.user.service;

import com.poomy.mainserver.user.dto.CustomUserDetails;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.repository.UserRepository;
import com.poomy.mainserver.user.type.UserRoleType;
import com.poomy.mainserver.util.exception.common.BError;
import com.poomy.mainserver.util.exception.common.CommonException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User loginGoogle(String googleEmail){
        Optional<User> user = userRepository.findByGoogleEmail(googleEmail);
        if(user.isEmpty()){
            User newUser = User.builder()
                    .googleEmail(googleEmail)
                    .role(UserRoleType.ROLE_USER)
                    .build();
            return userRepository.save(newUser);
        }
        return user.get();
    }

    public User loginPoomy(String googleEmail){
        return userRepository.findByGoogleEmail(googleEmail)
                .orElseThrow(() -> new CommonException(BError.NOT_EXIST, "User"));
    }

    @Transactional
    public User registerNickName(Integer userId, String nickName){
        Optional<User> user = userRepository.findByNickName(nickName);
        if(user.isPresent()){
            throw new CommonException(BError.EXIST, "nickName");
        }
        User registeredUser = userRepository.findById(userId)
                        .orElseThrow(() -> new CommonException(BError.NOT_EXIST, "user"));
        registeredUser.setNickName(nickName);
        return registeredUser;
    }

    @Transactional
    public Integer getUserId(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String googleEmail = Optional.ofNullable(customUserDetails.getUsername())
                .orElseThrow(()-> new CommonException(BError.NOT_VALID, "user"));
        return getUserEntityByGoogleEmail(googleEmail).getId();
    }

    @Transactional(readOnly = true)
    public User getUserEntityByGoogleEmail(String googleEmail) {
        return userRepository.findByGoogleEmail(googleEmail)
                .orElseThrow(() -> new CommonException(BError.NOT_EXIST, "user"));
    }

}
