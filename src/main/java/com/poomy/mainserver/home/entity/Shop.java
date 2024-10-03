package com.poomy.mainserver.home.entity;

import com.poomy.mainserver.detail.dto.res.ShopDetailRes;
import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.review.entity.Review;
import com.poomy.mainserver.spot.entity.Spot;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "poom_shops")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String location;

    @Column(name = "nearby_station")
    private String nearbyStation;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "spot_id", nullable = false)
    private Spot spot;

    @ManyToOne
    @JoinColumn(name = "mood_id", nullable = false)
    private Mood mood;

    private Double latitude;

    private Double longitude;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<Review> reviews;

    public ShopDetailRes toDto(Boolean favorite, List<ShopImage> shopImageList) {
        return ShopDetailRes.builder()
                .shopId(id)
                .name(name)
                .location(location)
                .phoneNumber(phoneNumber)
                .nearbyStation(nearbyStation)
                .favorite(favorite)
                .spot(spot.getName())
                .mood(mood.getName())
                .latitude(latitude)
                .longitude(longitude)
                .shopImageList(shopImageList)
                .openingHours("test 영업시간 ~~")
                .build();
    }
}
