package com.poomy.mainserver.home.service;

import com.poomy.mainserver.favorite.repository.FavoriteRepository;
import com.poomy.mainserver.home.dto.res.*;
import com.poomy.mainserver.home.repository.ShopImageRepository;
import com.poomy.mainserver.home.repository.ShopRepository;
import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.mood.repository.MoodRepository;
import com.poomy.mainserver.spot.repository.SpotRepository;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.entity.UserMood;
import com.poomy.mainserver.user.entity.UserSpot;
import com.poomy.mainserver.user.repository.UserMoodRepository;
import com.poomy.mainserver.user.repository.UserSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final UserMoodRepository userMoodRepository;
    private final UserSpotRepository userSpotRepository;
    private final ShopRepository shopRepository;
    private final ShopImageRepository shopImageRepository;
    private final SpotRepository spotRepository;
    private final MoodRepository moodRepository;
    private final FavoriteRepository favoriteRepository;


    public HomeShopRes getShopListBySpot(User user) {
        List<UserSpot> userSpots = userSpotRepository.findUserSpotsByUser(user);

        UserSpot randomUserSpot = userSpots.get(0);

        if (userSpots.size() == 2) {
            randomUserSpot = userSpots.get(new Random().nextInt(2));
        }

        List<ShopBySpotRes> shops = shopRepository.findShopsBySpot(randomUserSpot.getSpot().getId()).stream().map(shop -> {
            String image = shopImageRepository.findShopImageByShop_Id(shop.getId()).getUrl();
            int favoriteNum = favoriteRepository.countFavoriteByShop_Id(shop.getId());
            return ShopBySpotRes.ofShopBySpot(shop, image, favoriteNum);
        }).toList();

        return HomeShopRes.builder()
                .id(randomUserSpot.getId())
                .prefix("prefix")
                .shopList(shops)
                .build();

    }

    public List<SpotsRes> getSpotsList() {
        return spotRepository.findSpots().stream().map(SpotsRes::of).toList();
    }


    public List<HomeShopRes> getShopListByMood(User user) {

        List<Mood> userMoods = new ArrayList<>(userMoodRepository.findUserMoodsByUser(user).stream().map(UserMood::getMood).toList());

        List<HomeShopRes> shopList = new ArrayList<>();

        if (userMoods.size() == 1) {
            Mood randomMood = moodRepository.getRandomMood();
            userMoods.add(randomMood);
        }

        for (Mood userMood : userMoods) {
            List<ShopByMoodRes> homeShopResList = shopRepository.findShopsByMood(userMood.getId()).stream().map(shop -> {
                String image = shopImageRepository.findShopImageByShop_Id(shop.getId()).getUrl();
                return ShopByMoodRes.ofShopByMood(shop, image);
            }).toList();

            shopList.add(HomeShopRes.builder()
                    .id(userMood.getId())
                    .prefix(userMood.getPrefix())
                    .shopList(homeShopResList)
                    .build());
        }
        return shopList;

    }


}
