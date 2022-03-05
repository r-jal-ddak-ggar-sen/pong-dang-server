package dev.be.pongdang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.be.pongdang.domain.Image.ImageDTO;
import dev.be.pongdang.repository.ImageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageDTO getBackgroundImageList() {
        List<String> backgroundUrlList = new ArrayList<>();

        imageRepository.findAll().stream().forEach(i -> backgroundUrlList.add(i.getImageUrl()));

        return ImageDTO.builder()
                       .backgroundUrlList(backgroundUrlList)
                       .build();
    }
}
