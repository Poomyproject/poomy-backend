package com.poomy.mainserver.mood.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "moods_prefix")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoodPrefix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "mood_id")
    private Mood mood;

}
