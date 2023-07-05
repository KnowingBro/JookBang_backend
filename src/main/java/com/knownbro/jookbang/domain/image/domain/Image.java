package com.knownbro.jookbang.domain.image.domain;

import com.knownbro.jookbang.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String originImgUrl;

    private String newImgUrl1;
    private String newImgUrl2;
    private String newImgUrl3;
    private String newImgUrl4;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Image(String name, String originImgUrl, String newImgUrl1, String newImgUrl2, String newImgUrl3, String newImgUrl4, User user) {
        this.name = name;
        this.originImgUrl = originImgUrl;
        this.newImgUrl1 = newImgUrl1;
        this.newImgUrl2 = newImgUrl2;
        this.newImgUrl3 = newImgUrl3;
        this.newImgUrl4 = newImgUrl4;
        this.user = user;
    }
}
