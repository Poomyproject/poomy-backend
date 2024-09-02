package com.poomy.mainserver.review.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.util.vo.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_moods",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"review_id", "mood_id"})
        })
public class ReviewMood extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "mood_id")
    private Mood mood;

}
