package com.poomy.mainserver.category.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poomy.mainserver.category.type.HotPlaceType;
import com.poomy.mainserver.user.entity.UserAtmosphere;
import com.poomy.mainserver.user.entity.UserHotPlace;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
@Getter
@Table(name = "hot_places")
public class HotPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private HotPlaceType name;

    @Column(name = "img_url")
    private String imgUrl;

    private Double latitude;

    private Double longitude;

    @JsonManagedReference
    @OneToMany(mappedBy = "hotPlace", fetch = FetchType.EAGER)
    private List<UserHotPlace> userHotPlaces;
}
