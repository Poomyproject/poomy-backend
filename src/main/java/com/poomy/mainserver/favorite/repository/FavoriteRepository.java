package com.poomy.mainserver.favorite.repository;

import com.poomy.mainserver.favorite.entity.Favorite;
import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    int countFavoriteByShop_Id(Long shopId);

    Boolean existsByUserAndShop(User user, Shop shop);
}
