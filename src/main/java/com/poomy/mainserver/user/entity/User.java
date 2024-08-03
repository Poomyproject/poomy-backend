package com.poomy.mainserver.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poomy.mainserver.user.type.UserRoleType;
import com.poomy.mainserver.util.vo.BaseTime;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(name = "users")
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "google_email", unique = true, nullable = false, length = 63)
    private String googleEmail;

    @Setter
    @Column(unique = true, length = 5)
    private String nickname;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(length = 127)
    private String description;

    @Enumerated(EnumType.STRING)
    private UserRoleType role;

    @Setter
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserMood> userMoods;

    @Setter
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserSpot> userSpots;

}
