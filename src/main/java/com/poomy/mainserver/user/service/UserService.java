package com.poomy.mainserver.user.service;

import com.poomy.mainserver.mood.dto.MoodNmResDto;
import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.mood.mapper.MoodMapper;
import com.poomy.mainserver.spot.dto.SpotNmResDto;
import com.poomy.mainserver.spot.entity.Spot;
import com.poomy.mainserver.spot.mapper.SpotMapper;
import com.poomy.mainserver.user.dto.CustomUserDetails;
import com.poomy.mainserver.user.dto.res.UserInfoResDto;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMoodRepository userMoodRepository;
    private final UserSpotRepository userSpotRepository;
    private final UserMapper userMapper;
    private final MoodMapper moodMapper;
    private final SpotMapper spotMapper;

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

    public User loginApple(String appleEmail, String appleSub) {
        // Apple 이메일이 없을 경우 더미 이메일 생성
        String emailToStore = (appleEmail != null) ? appleEmail : "apple_user_" + appleSub + "@example.com";

        // 이메일로 사용자 조회
        Optional<User> user = userRepository.findByGoogleEmail(emailToStore);

        if (user.isEmpty()) {
            // 새로운 사용자 생성
            User newUser = User.builder()
                    .googleEmail(emailToStore) // 이메일 주소에 appleSub 포함
                    .role(UserRoleType.ROLE_USER) // 기본 역할 설정
                    .nickname("유저" + UUID.randomUUID().toString().substring(0, 2)) // 기본 닉네임 생성
                    .build();

            // 새 사용자 저장
            return userRepository.save(newUser);
        }

        return user.get();
    }




    public User loginPoomy(String googleEmail){
        return userRepository.findByGoogleEmail(googleEmail)
                .orElseThrow(() -> new CommonException(BError.NOT_EXIST, "User"));
    }

    public User loginGuest(String guestUserName){
        Optional<User> user = userRepository.findByNickname(guestUserName);
        if(user.isEmpty()){
            User newUser = User.builder()
                    .googleEmail(guestUserName)
                    .role(UserRoleType.ROLE_GUEST)
                    .build();
            return userRepository.save(newUser);
        }
        return user.get();
    }

    public User registerNickname(User user, String nickname){
        boolean existedNickname = checkUserNickname(nickname);
        if(existedNickname){
            throw new CommonException(BError.EXIST, "Nickname");
        }
        user.setNickname(nickname);
        return user;
    }

    public boolean checkUserNickname(String nickname){
        return userRepository.existsByNickname(nickname);
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
        userMoodRepository.deleteAllByUser(user);

        List<UserMood> userMoods = moods.stream()
                .map(mood -> userMapper.toUserMood(user, mood))
                .toList();

        List<UserMood> savedUserMoods = new ArrayList<>();
        for (UserMood userMood : userMoods) {
            boolean exists = userMoodRepository.existsByUserIdAndMoodId(userMood.getUser().getId(), userMood.getMood().getId());
            if (!exists) {
                savedUserMoods.add(userMoodRepository.save(userMood));
            }
        }

        userMoods = userMoodRepository.saveAll(userMoods);
        user.setUserMoods(userMoods);
        return userMoods;
    }

    public List<UserSpot> registerUserSpot(List<Spot> spots){
        User user = getUser();
        userSpotRepository.deleteAllByUser(user);
        List<UserSpot> userSpots = spots.stream()
                .map(spot -> userMapper.toUserSpot(user, spot))
                .toList();

        List<UserSpot> savedUserSpots = new ArrayList<>();
        for (UserSpot userSpot : userSpots) {
            boolean exists = userSpotRepository.existsByUserIdAndSpotId(userSpot.getUser().getId(), userSpot.getSpot().getId());
            if (!exists) {
                savedUserSpots.add(userSpotRepository.save(userSpot));
            }
        }

        userSpots = userSpotRepository.saveAll(userSpots);
        user.setUserSpots(userSpots);
        return userSpots;
    }

    public UserInfoResDto getUserInfo() {
        User user = getUser();
        List<MoodNmResDto> moodNmResDtos = user.getUserMoods().stream()
                .map(userMood -> moodMapper.toMoodNmResDto(userMood.getMood()))
                .toList();
        List<SpotNmResDto> spotNmResDtos = user.getUserSpots().stream()
                .map(userSpot -> spotMapper.toSpotNmResDto(userSpot.getSpot()))
                .toList();
        return UserInfoResDto.builder()
                .nickname(user.getNickname())
                .googleEmail(user.getGoogleEmail())
                .imgUrl(user.getImgUrl())
                .moods(moodNmResDtos)
                .spots(spotNmResDtos)
                .build();
    }
}
