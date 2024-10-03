package com.poomy.mainserver.favorite.repository;

import com.poomy.mainserver.favorite.entity.Favorite;
import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    int countFavoriteByShop_Id(Long shopId);

    Boolean existsByUserAndShop(User user, Shop shop);

    @Query(value = "select * from user_favorite_poom_shop where user_id= :userId and is_favorite = true", nativeQuery = true)
    List<Favorite> getAllFavoriteShop(Integer userId);

    @Query(value = "select * from user_favorite_poom_shop where user_id= :userId and shop_id= :shopId", nativeQuery = true)
    Favorite getFavoriteShop(Integer userId, Long shopId);

    @Transactional
    @Query(value = "insert into user_favorite_poom_shop (user_id, shop_id, is_favorite) values(:userId, :shopId, true)", nativeQuery = true)
    @Modifying(clearAutomatically = true)
    void insertUserToFavorite(Integer userId, Long shopId);

    @Transactional
    @Query(value = "update user_favorite_poom_shop set is_favorite = true where user_id= :userId and shop_id= :shopId", nativeQuery = true)
    @Modifying(clearAutomatically = true)
    void updateLikeShopById(Integer userId, Long shopId);

    @Transactional
    @Query(value = "update user_favorite_poom_shop set is_favorite = false where user_id= :userId and shop_id= :shopId", nativeQuery = true)
    @Modifying(clearAutomatically = true)
    void updateUnlikeShopById(Integer userId, Long shopId);
}
