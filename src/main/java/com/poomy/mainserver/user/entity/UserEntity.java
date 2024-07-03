package com.poomy.mainserver.user.entity;

import com.poomy.mainserver.user.type.UserRoleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 63)
    private String googleEmail;

    @Setter
    @Column(unique = true, length = 5)
    private String nickName;

    private String imgUrl;

    @Column(length = 127)
    private String description;

    @Enumerated(EnumType.STRING)
    private UserRoleType role;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createAt;

    @UpdateTimestamp
    private Timestamp updateAt;

}
