package dev.be.pongdang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.be.pongdang.common.response.CommonResponse;
import dev.be.pongdang.domain.Image.ImageDTO;
import dev.be.pongdang.domain.Image.ImageResult.MemberResponse;
import dev.be.pongdang.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "배경 리스트 조회")
    @GetMapping
    public CommonResponse getBackgroundImageList() {
        ImageDTO imageDTO = imageService.getBackgroundImageList();
        return new CommonResponse<>(MemberResponse.builder()
                                                  .backgroundUrl(imageDTO.getBackgroundUrlList())
                                                  .build());
    }

}
