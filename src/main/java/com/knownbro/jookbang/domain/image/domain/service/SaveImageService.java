package com.knownbro.jookbang.domain.image.domain.service;

import com.knownbro.jookbang.domain.image.domain.Image;
import com.knownbro.jookbang.domain.image.domain.presentation.dto.request.ImageRequest;
import com.knownbro.jookbang.domain.image.domain.repository.ImageRepository;
import com.knownbro.jookbang.domain.user.UserFacade;
import com.knownbro.jookbang.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveImageService {

    private final UserFacade userFacade;
    private final ImageRepository imageRepository;

    @Transactional
    public void execute(ImageRequest request) {
        User user = userFacade.getCurrentUser();

        imageRepository.save(
                Image.builder()
                        .name(request.getName())
                        .originImgUrl(request.getOriginUrl())
                        .newImgUrl1(request.getNewUrl1())
                        .newImgUrl2(request.getNewUrl2())
                        .newImgUrl3(request.getNewUrl3())
                        .newImgUrl4(request.getNewUrl4())
                        .user(user)
                        .build()
        );
    }
}
