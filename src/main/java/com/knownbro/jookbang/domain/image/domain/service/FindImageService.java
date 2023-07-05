package com.knownbro.jookbang.domain.image.domain.service;

import com.knownbro.jookbang.domain.image.domain.Image;
import com.knownbro.jookbang.domain.image.domain.presentation.dto.response.ImageResponse;
import com.knownbro.jookbang.domain.image.domain.repository.ImageRepository;
import com.knownbro.jookbang.domain.user.UserFacade;
import com.knownbro.jookbang.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindImageService {

    private final UserFacade userFacade;
    private final ImageRepository imageRepository;

    public List<ImageResponse> execute() {
        User user = userFacade.getCurrentUser();

        List<Image> images = imageRepository.findByUserId(user.getId());

        return images.stream()
                .map(ImageResponse::new)
                .collect(Collectors.toList());
    }
}
