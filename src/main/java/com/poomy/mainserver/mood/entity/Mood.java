package com.poomy.mainserver.mood.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poomy.mainserver.user.entity.UserMood;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
@Getter
@Table(name = "moods")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(length = 15)
    private String prefix;

    @JsonManagedReference
    @OneToMany(mappedBy = "mood", fetch = FetchType.EAGER)
    private List<UserMood> userMoods;

}
