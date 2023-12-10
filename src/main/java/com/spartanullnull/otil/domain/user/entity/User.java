package com.spartanullnull.otil.domain.user.entity;

import com.spartanullnull.otil.domain.user.dto.*;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountId;

    @Column
    @NotNull
    private String password;

    @Column(nullable = false, unique = true)
    @NotNull
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    private Long kakaoId;

    public User(String accountId, String password, String email, String nickname,
        UserRoleEnum role) {
        this.accountId = accountId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }

    public User(Long id, String encodedPassword, String email, String nickname, UserRoleEnum role) {
        this.kakaoId = id;
        this.password = encodedPassword;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }

    public void modifyByRequest(UserProfileModifyRequestDto userProfileModifyRequestDto,
        String password, UserRoleEnum role) {
        this.accountId = userProfileModifyRequestDto.accountId();
        this.password = password;
        this.nickname = userProfileModifyRequestDto.nickname();
        this.email = userProfileModifyRequestDto.email();
        this.role = role;
    }
}
