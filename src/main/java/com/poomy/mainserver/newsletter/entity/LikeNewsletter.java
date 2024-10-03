package com.poomy.mainserver.newsletter.entity;


import com.poomy.mainserver.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "news_letter_users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class LikeNewsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "newsletter_id", nullable = false)
    private Newsletter newsletter;

    @Column(name = "is_like")
    private Boolean isLike;
}
