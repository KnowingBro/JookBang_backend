package com.knownbro.jookbang.domain.image.domain.service;

import com.knownbro.jookbang.domain.image.domain.Image;
import com.knownbro.jookbang.domain.image.domain.presentation.dto.response.ImageResponse;
import com.knownbro.jookbang.domain.image.domain.repository.ImageRepository;
import com.knownbro.jookbang.domain.user.UserFacade;
import com.knownbro.jookbang.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindImageInfoService {

    private final UserFacade userFacade;
    private final ImageRepository imageRepository;

    public ImageResponse execute(Long id) {
        User user = userFacade.getCurrentUser();

        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("이미지를 찾을 수 없는거 같기도?"));

        return new ImageResponse(image);
    }
}
