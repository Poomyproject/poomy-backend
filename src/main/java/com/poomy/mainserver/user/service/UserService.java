package com.poomy.mainserver.user.service;

import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.spot.entity.Spot;
import com.poomy.mainserver.user.dto.CustomUserDetails;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.entity.UserMood;
import com.poomy.mainserver.user.entity.UserSpot;
import com.poomy.mainserver.user.mapper.UserMapper;
import com.poomy.mainserver.user.repository.UserMoodRepository;
import com.poomy.mainserver.user.repository.UserSpotRepository;
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
    private final UserMoodRepository userMoodRepository;
    private final UserSpotRepository userSpotRepository;
    private final UserMapper userMapper;

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

    public User registerNickname(User user, String nickname){
        boolean existedNickname = userRepository.existsByNickname(nickname);
        if(existedNickname){
            throw new CommonException(BError.EXIST, "Nickname");
        }
        user.setNickname(nickname);
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

    public List<UserMood> registerUserMood(List<Mood> moods) {
        User user = getUser();
        List<UserMood> userMoods = moods.stream()
                .map(mood -> userMapper.toUserMood(user, mood))
                .toList();
        userMoods = userMoodRepository.saveAll(userMoods);
        user.setUserMoods(userMoods);
        return userMoods;
    }

    public List<UserSpot> registerUserSpot(List<Spot> spots){
        User user = getUser();
        List<UserSpot> userSpots = spots.stream()
                .map(spot -> userMapper.toUserSpot(user, spot))
                .toList();
        userSpots = userSpotRepository.saveAll(userSpots);
        user.setUserSpots(userSpots);
        return userSpots;
    }

}
