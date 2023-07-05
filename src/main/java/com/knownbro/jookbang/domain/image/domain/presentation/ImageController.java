package com.knownbro.jookbang.domain.image.domain.presentation;

import com.knownbro.jookbang.domain.image.domain.presentation.dto.request.ImageRequest;
import com.knownbro.jookbang.domain.image.domain.presentation.dto.response.ImageResponse;
import com.knownbro.jookbang.domain.image.domain.service.FindImageInfoService;
import com.knownbro.jookbang.domain.image.domain.service.FindImageService;
import com.knownbro.jookbang.domain.image.domain.service.SaveImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final SaveImageService saveImageService;
    private final FindImageService findImageService;
    private final FindImageInfoService findImageInfoService;

    @PostMapping()
    public void save(@RequestBody ImageRequest request) {
        saveImageService.execute(request);
    }

    @GetMapping()
    public List<ImageResponse> findAll() {
        return findImageService.execute();
    }

    @GetMapping("/{id}")
    public ImageResponse findOne(@PathVariable Long id) {
        return findImageInfoService.execute(id);
    }
}
