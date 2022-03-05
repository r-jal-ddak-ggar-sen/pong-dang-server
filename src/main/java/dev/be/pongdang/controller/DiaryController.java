package dev.be.pongdang.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.be.pongdang.common.response.CommonResponse;
import dev.be.pongdang.domain.Diary.DiaryWriteDTO;
import dev.be.pongdang.domain.Diary.DiaryWriteResult.DiaryWriteRequest;
import dev.be.pongdang.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @Operation(summary = "일기 작성")
    @PostMapping
    public CommonResponse getBackgroundImageList(@RequestBody DiaryWriteRequest request) {
        DiaryWriteDTO diaryWriteDTO = DiaryWriteDTO.builder()
                                                   .pondId(request.getPondId())
                                                   .mid(request.getMid())
                                                   .mood(request.getMood())
                                                   .content(request.getContent())
                                                   .build();
        diaryService.writeDiary(diaryWriteDTO);
        return CommonResponse.Success();
    }

}
