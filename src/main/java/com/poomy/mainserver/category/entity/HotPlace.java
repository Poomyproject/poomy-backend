package com.poomy.mainserver.category.entity;

import com.poomy.mainserver.category.type.HotPlaceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Table(name = "hot_places")
public class HotPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private HotPlaceType name;

    private String imgUrl;

    private Double latitude;

    private Double longitude;

}
