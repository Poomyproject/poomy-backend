package com.poomy.mainserver.favorite.repository;

import com.poomy.mainserver.favorite.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {

    int countFavoriteByShop_Id(Long shopId);
}
