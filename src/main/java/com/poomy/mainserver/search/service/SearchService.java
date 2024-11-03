package com.poomy.mainserver.search.service;

import com.poomy.mainserver.favorite.entity.Favorite;
import com.poomy.mainserver.favorite.repository.FavoriteRepository;
import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.home.entity.ShopImage;
import com.poomy.mainserver.home.repository.ShopImageRepository;
import com.poomy.mainserver.home.repository.ShopRepository;
import com.poomy.mainserver.search.dto.SearchCountResDto;
import com.poomy.mainserver.search.dto.SearchShopResDto;
import com.poomy.mainserver.search.dto.TopFiveShopResDto;
import com.poomy.mainserver.search.entity.Search;
import com.poomy.mainserver.search.repository.SearchRepository;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.service.UserService;
import com.poomy.mainserver.util.exception.common.BError;
import com.poomy.mainserver.util.exception.common.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchRepository searchRepository;
    private final ShopImageRepository shopImageRepository;
    private final FavoriteRepository favoriteRepository;
    private final ShopRepository shopRepository;
    private final UserService userService;

    public List<SearchShopResDto> getShopListByName(String word) {
        if (word == null || word.isEmpty()) {
            return Collections.emptyList();
        }

        User user = userService.getUser();
        List<SearchShopResDto> searchShopResDtos = new ArrayList<>();
        List<SearchShopResDto> searchShopResDtos1 = shopRepository.findFirstShopsByName(word).stream()
                .map(shop -> {
                    String image = shopImageRepository.findTop1ByShop_Id(shop.getId()).map(ShopImage::getUrl).orElseThrow();
                    Favorite favorite = favoriteRepository.getUserLike(shop.getId(), user.getId());
                    Boolean isFavorite = false;
                    if (favorite !=null) {
                        isFavorite=favorite.getIsFavorite();
                    }
                    return SearchShopResDto.ofSearchShopByName(shop, image, isFavorite);
                }).toList();
        List<SearchShopResDto> searchShopResDtos2 = shopRepository.findSecondShopsByName(word).stream()
                .map(shop -> {
                    String image = shopImageRepository.findTop1ByShop_Id(shop.getId()).map(ShopImage::getUrl).orElseThrow();
                    Favorite favorite = favoriteRepository.getUserLike(shop.getId(), user.getId());
                    Boolean isFavorite = false;
                    if (favorite !=null) {
                        isFavorite=favorite.getIsFavorite();
                    }
                    return SearchShopResDto.ofSearchShopByName(shop, image, isFavorite);
                }).toList();
        searchShopResDtos = Stream.concat(searchShopResDtos1.stream(), searchShopResDtos2.stream()).toList();
        if (searchShopResDtos.isEmpty()) {
            return Collections.emptyList();
        } else{
            return searchShopResDtos;
        }
    }

    public SearchCountResDto updateSearchCountById(Long shopId) {
        Search checkSearch = searchRepository.getSearchCountByShopId(shopId);
        if(checkSearch ==null){
            searchRepository.insertSearchCountById(shopId);
        }

        searchRepository.increaseSearchCountById(shopId);

        Shop shop = shopRepository.findShopById(shopId);
        Search search = searchRepository.getSearchCountByShopId(shopId);
        SearchCountResDto searchCountResDto = SearchCountResDto.of(shop, search);
        return searchCountResDto;
    }

    public List<TopFiveShopResDto> getTopFiveShop() {
        List<Search> searchList = searchRepository.findTop5ByOrderByCountDesc();
        if (searchList == null) {
            searchList = List.of();
        }

        int searchCount = searchList.size();

        if (searchCount < 5) {
            int remainingCount = 5 - searchCount;
            List<Shop> shopList = shopRepository.findRandomRemainingCount(remainingCount);
            List<TopFiveShopResDto> shops = shopList.stream()
                    .map(TopFiveShopResDto::ofTopFiveShop).toList();

            List<TopFiveShopResDto> searchs = searchList.stream()
                    .map(search -> {
                        Shop shop = search.getShop();
                        return TopFiveShopResDto.ofTopFiveShop(shop);
                    }).toList();

            List<TopFiveShopResDto> results = Stream.concat(searchs.stream(), shops.stream()).toList();

            return results;
        }


        List<TopFiveShopResDto> topFiveShopResDtos = searchList.stream()
                .map(search -> {
                    Shop shop = search.getShop();
                    return TopFiveShopResDto.ofTopFiveShop(shop);
                })
                .toList();
        return topFiveShopResDtos;
    }
}
