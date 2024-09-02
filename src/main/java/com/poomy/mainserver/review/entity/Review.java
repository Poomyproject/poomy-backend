package com.poomy.mainserver.review.entity;

import com.poomy.mainserver.home.entity.Shop;
import com.poomy.mainserver.review.dto.res.ReviewImageResDto;
import com.poomy.mainserver.review.dto.res.ReviewResDto;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.util.date.ChangeFormat;
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
    private Long id;

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

    public ReviewResDto toReviewResDto(){
        String reviewDateFormat = ChangeFormat.reviewFormat(getCreatedAt());
        List<ReviewImageResDto> imgUrls = reviewImages.stream()
                .map(ReviewImage::toReviewImageResDto)
                .toList();
        return ReviewResDto.builder()
                .id(id)
                .userNickName(user.getNickname())
                .userImgUrl(user.getImgUrl())
                .date(reviewDateFormat)
                .content(content)
                .isRecommend(isRecommend)
                .imgUrls(imgUrls)
                .build();
    }

}
