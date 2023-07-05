package com.knownbro.jookbang.domain.image.domain.repository;

import com.knownbro.jookbang.domain.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByUserId(Long id);
}
