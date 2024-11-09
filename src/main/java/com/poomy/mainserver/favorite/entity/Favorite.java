package com.poomy.mainserver.favorite.entity;

import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_favorite_poom_shop")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "is_favorite")
    private Boolean isFavorite;
}
