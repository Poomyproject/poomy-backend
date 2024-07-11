package com.poomy.mainserver.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poomy.mainserver.category.entity.HotPlace;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Builder
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_hot_places",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"user_id", "hot_place_id"})
        })
public class UserHotPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "hot_place_id")
    @JsonBackReference
    private HotPlace hotPlace;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createAt;

    @UpdateTimestamp
    private Timestamp updateAt;

}
