package com.poomy.mainserver.category.entity;

import com.poomy.mainserver.category.type.AtmosphereType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Table(name = "atmospheres")
public class AtmosphereEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private AtmosphereType name;

    private String imgUrl;

    @Column(length = 15)
    private String prefix;

}
