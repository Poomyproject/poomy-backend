package com.poomy.mainserver.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poomy.mainserver.mood.entity.Mood;
import com.poomy.mainserver.util.vo.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_moods",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"user_id", "mood_id"})
        })
public class UserMood extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "mood_id")
    @JsonBackReference
    private Mood mood;

}
