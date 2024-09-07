package com.poomy.mainserver.mood.entity;

import com.poomy.mainserver.review.entity.ReviewMood;
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
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    @OneToMany(mappedBy = "mood", fetch = FetchType.LAZY)
    private List<UserMood> userMoods;

    @OneToMany(mappedBy = "mood", fetch = FetchType.LAZY)
    private List<ReviewMood> reviewMoods;

}
