package com.poomy.mainserver.user.service;

import com.poomy.mainserver.category.entity.Atmosphere;
import com.poomy.mainserver.category.repository.AtmosphereRepository;
import com.poomy.mainserver.user.dto.CustomUserDetails;
import com.poomy.mainserver.user.dto.RegisterUserAtmospheresReqDto;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.entity.UserAtmosphere;
import com.poomy.mainserver.user.repository.UserAtmosphereRepository;
import com.poomy.mainserver.user.repository.UserRepository;
import com.poomy.mainserver.user.type.UserRoleType;
import com.poomy.mainserver.util.exception.common.BError;
import com.poomy.mainserver.util.exception.common.CommonException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AtmosphereRepository atmosphereRepository;
    private final UserAtmosphereRepository userAtmosphereRepository;

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

    public User registerNickName(User user, String nickName){
        boolean existedNickName = userRepository.existsByNickName(nickName);
        if(existedNickName){
            throw new CommonException(BError.EXIST, "NickName");
        }
        user.setNickName(nickName);
        return user;
    }

    public User getUser(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String googleEmail = Optional.ofNullable(customUserDetails.getUsername())
                .orElseThrow(()-> new CommonException(BError.NOT_VALID, "user"));
        return getUserByGoogleEmail(googleEmail);
    }

    public User getUserByGoogleEmail(String googleEmail) {
        return userRepository.findByGoogleEmail(googleEmail)
                .orElseThrow(() -> new CommonException(BError.NOT_EXIST, "user"));
    }

    public List<UserAtmosphere> registerUserAtmosphere(RegisterUserAtmospheresReqDto registerUserAtmospheresReqDto) {
        User user = getUser();
        List<Integer> atmosphereIds = registerUserAtmospheresReqDto.getAtmosphereIds();
        List<Atmosphere> atmospheres = findAtmospheres(atmosphereIds);
        List<UserAtmosphere> userAtmospheres = saveUserAtmosphere(user, atmospheres);
        user.setUserAtmospheres(userAtmospheres);
        return userAtmospheres;
    }

    private List<Atmosphere> findAtmospheres(List<Integer> atmosphereIds){
        return atmosphereIds.stream()
                .map(atmosphereId -> atmosphereRepository.findById(atmosphereId)
                        .orElseThrow(() -> new CommonException(BError.NOT_EXIST, atmosphereId + " of atmosphereId")))
                .toList();
    }

    private List<UserAtmosphere> saveUserAtmosphere(User user, List<Atmosphere> atmospheres){
        return atmospheres.stream()
                .map(atmosphere -> {
                    UserAtmosphere userAtmosphere = UserAtmosphere.builder()
                            .user(user)
                            .atmosphere(atmosphere)
                            .build();
                    return userAtmosphereRepository.save(userAtmosphere);
                }).toList();
    }

}
