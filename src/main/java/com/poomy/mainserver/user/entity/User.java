package com.poomy.mainserver.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.poomy.mainserver.user.type.UserRoleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 63)
    private String googleEmail;

    @Setter
    @Column(unique = true, length = 5)
    private String nickname;

    private String imgUrl;

    @Column(length = 127)
    private String description;

    @Enumerated(EnumType.STRING)
    private UserRoleType role;

    @Setter
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserAtmosphere> userAtmospheres;

    @Setter
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserHotPlace> userHotPlaces;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createAt;

    @UpdateTimestamp
    private Timestamp updateAt;

}
