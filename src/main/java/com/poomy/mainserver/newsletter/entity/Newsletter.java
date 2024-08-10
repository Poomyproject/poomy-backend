package com.poomy.mainserver.newsletter.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news_letter")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String thumbnail;

    @Column(name = "sub_title")
    private String subTitle;

    private String explanation;
}
