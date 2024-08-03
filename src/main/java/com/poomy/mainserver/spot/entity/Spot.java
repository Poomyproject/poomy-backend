package com.poomy.mainserver.spot.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poomy.mainserver.user.entity.UserSpot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
@Getter
@Table(name = "spots")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    private Double latitude;

    private Double longitude;

    @JsonManagedReference
    @OneToMany(mappedBy = "spot", fetch = FetchType.EAGER)
    private List<UserSpot> userSpots;

}
