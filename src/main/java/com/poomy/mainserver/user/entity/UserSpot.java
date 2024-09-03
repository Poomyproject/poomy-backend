package com.poomy.mainserver.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.poomy.mainserver.spot.entity.Spot;
import com.poomy.mainserver.util.vo.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_spots",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"user_id", "spot_id"})
        })
public class UserSpot extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private Spot spot;

}
