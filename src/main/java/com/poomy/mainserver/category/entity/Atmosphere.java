package com.poomy.mainserver.category.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poomy.mainserver.category.type.AtmosphereType;
import com.poomy.mainserver.user.entity.UserAtmosphere;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@ToString
@Getter
@Table(name = "atmospheres")
public class Atmosphere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private AtmosphereType name;

    private String imgUrl;

    @Column(length = 15)
    private String prefix;

    @JsonManagedReference
    @OneToMany(mappedBy = "atmosphere", fetch = FetchType.EAGER)
    private List<UserAtmosphere> userAtmospheres;

}
