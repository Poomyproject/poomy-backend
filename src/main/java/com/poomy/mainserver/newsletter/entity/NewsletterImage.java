package com.poomy.mainserver.newsletter.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news_letter_images")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class NewsletterImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "newsletter_id")
    private Newsletter newsletter;

    @Column(name = "main_photo")
    private String mainPhoto;

    @Column(name = "first_shop_image1")
    private String firstShopImage1;

    @Column(name = "first_shop_image2")
    private String firstShopImage2;

    @Column(name = "second_shop_image1")
    private String secondShopImage1;

    @Column(name = "second_shop_image2")
    private String secondShopImage2;

    @Column(name = "third_shop_image1")
    private String thirdShopImage1;

    @Column(name = "third_shop_image2")
    private String thirdShopImage2;
}
