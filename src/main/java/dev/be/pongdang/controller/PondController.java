package dev.be.pongdang.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.be.pongdang.common.response.CommonResponse;
import dev.be.pongdang.domain.pond.PondCreateResult.PondRequest;
import dev.be.pongdang.domain.pond.PondDTO;
import dev.be.pongdang.domain.pond.PondSearchDTO;
import dev.be.pongdang.domain.pond.PondSearchResult.Ponds;
import dev.be.pongdang.service.PondService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pond")
public class PondController {

    private final PondService pondService;

    @Operation(summary = "연못 생성")
    @PostMapping
    public CommonResponse createPond(@RequestBody PondRequest request) {
        PondDTO pondDTO = PondDTO.builder()
                                 .mid(request.getMid())
                                 .title(request.getTitle())
                                 .friendList(request.getFriendList())
                                 .backgroundUrl(request.getBackgroundUrl())
                                 .build();
        pondService.createPond(pondDTO);
        return CommonResponse.Success();
    }

    @Operation(summary = "본인의 연못 리스트 조회")
    @GetMapping
    public CommonResponse searchMyPondList(@RequestParam(name = "mid") String mid) {
        PondSearchDTO pondSearchDTO = PondSearchDTO.builder()
                                                   .mid(mid)
                                                   .build();
        List<Ponds> myPondList = pondService.searchMyPondList(pondSearchDTO);
        return new CommonResponse<>(myPondList);
    }
}
