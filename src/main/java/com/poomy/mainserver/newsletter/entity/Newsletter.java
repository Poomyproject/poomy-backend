package com.poomy.mainserver.newsletter.entity;

import com.poomy.mainserver.home.entity.Shop;
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

    private String headline;

    private String subtopic;

    @OneToOne
    @JoinColumn(name = "shop1_id")
    private Shop shop1;

    @OneToOne
    @JoinColumn(name = "shop2_id")
    private Shop shop2;

    @OneToOne
    @JoinColumn(name = "shop3_id")
    private Shop shop3;

    @Column(name = "first_keyword")
    private String firstKeyword;

    @Column(name = "second_keyword")
    private String secondKeyword;

    @Column(name = "third_keyword")
    private String thirdKeyword;

    @Column(name = "text_top")
    private String textTop;

    @Column(name = "text_bottom")
    private String textBottom;

    @Column(name = "user_feedback")
    private Long userFeedback;

}

