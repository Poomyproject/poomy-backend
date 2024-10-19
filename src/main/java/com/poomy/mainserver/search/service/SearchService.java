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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchRepository searchRepository;
    private final ShopImageRepository shopImageRepository;
    private final FavoriteRepository favoriteRepository;
    private final ShopRepository shopRepository;
    private final UserService userService;

    public List<SearchShopResDto> getShopListByName(String word) {
        User user = userService.getUser();
        List<SearchShopResDto> searchShopResDtos = shopRepository.findFirstShopsByName(word).stream()
                .map(shop -> {
                    String image = shopImageRepository.findTop1ByShop_Id(shop.getId()).map(ShopImage::getUrl).orElseThrow();
                    Favorite favorite = favoriteRepository.getUserLike(shop.getId(), user.getId());
                    Boolean isFavorite = false;
                    if (favorite !=null) {
                        isFavorite=favorite.getIsFavorite();
                    }
                    return SearchShopResDto.ofSearchShopByName(shop, image, isFavorite);
                }).toList();
        return searchShopResDtos;
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
        List<TopFiveShopResDto> topFiveShopResDtos = searchRepository.getTopFiveShops().stream().map(search -> {
            Shop shop = search.getShop();
            return TopFiveShopResDto.ofTopFiveShop(shop);
        }).toList();

        return topFiveShopResDtos;
    }
}
