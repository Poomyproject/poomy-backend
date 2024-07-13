package com.poomy.mainserver.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poomy.mainserver.category.entity.Atmosphere;
import com.poomy.mainserver.util.vo.BaseTime;
import jakarta.persistence.*;
import lombok.*;

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
public class UserAtmosphere extends BaseTime {

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

}
