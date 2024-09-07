package com.poomy.mainserver.spot.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spots_prefix")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SpotPrefix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
