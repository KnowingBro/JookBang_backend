package com.knownbro.jookbang.domain.image.domain.presentation.dto.response;

import com.knownbro.jookbang.domain.image.domain.Image;
import lombok.Getter;

import java.util.List;

@Getter
public class ImageResponse {

    private String name;
    private String originUrl;
    private String newUrl1;
    private String newUrl2;
    private String newUrl3;
    private String newUrl4;

    public ImageResponse(Image image) {
        this.name = image.getName();
        this.originUrl = image.getOriginImgUrl();
        this.newUrl1 = image.getNewImgUrl1();
        this.newUrl2 = image.getNewImgUrl2();
        this.newUrl3 = image.getNewImgUrl3();
        this.newUrl4 = image.getNewImgUrl4();
    }
}
