package com.poomy.mainserver.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poomy.mainserver.category.entity.Atmosphere;
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
@Table(name = "user_atmospheres",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"user_id", "atmosphere_id"})
        })
public class UserAtmosphere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "atmosphere_id")
    @JsonBackReference
    private Atmosphere atmosphere;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createAt;

    @UpdateTimestamp
    private Timestamp updateAt;

}
