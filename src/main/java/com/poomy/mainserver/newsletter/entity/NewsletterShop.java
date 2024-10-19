package com.poomy.mainserver.newsletter.entity;

import com.poomy.mainserver.home.entity.Shop;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

@Entity
@Table(name = "news_letter_shops")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class NewsletterShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "shop_title")
    private String ShopTitle;

    @Column(name = "shop_text", columnDefinition = "TEXT")
    private String ShopText;
}
