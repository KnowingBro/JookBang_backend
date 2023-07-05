package com.knownbro.jookbang.domain.user.domain;

import com.knownbro.jookbang.domain.user.domain.authority.Authority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public User(String email, String name, Authority authority) {
        this.email = email;
        this.name = name;
        this.authority = authority;
    }
}
