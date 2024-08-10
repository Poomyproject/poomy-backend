package com.poomy.mainserver.home.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "poom_shop_img")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShopImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
