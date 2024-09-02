package com.poomy.mainserver.review.entity;

import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.entity.UserMood;
import com.poomy.mainserver.util.vo.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(name = "reviews")
public class Review extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poom_shop_id")
    private Shop shop;

    @Column(name = "is_recommend")
    private Boolean isRecommend;

    @Column(length = 511)
    private String content;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewMood> reviewMoods;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewImage> reviewImages;



}
