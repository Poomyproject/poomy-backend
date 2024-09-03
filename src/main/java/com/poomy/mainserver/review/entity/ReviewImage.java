package com.poomy.mainserver.review.entity;

import com.poomy.mainserver.review.dto.res.ReviewImageResDto;
import com.poomy.mainserver.util.vo.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_imgs",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"review_id", "url"})
        })
public class ReviewImage extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(length = 511)
    private String url;

    public ReviewImageResDto toReviewImageResDto(){
        return ReviewImageResDto.builder()
                .id(id)
                .url(url)
                .build();
    }

}
