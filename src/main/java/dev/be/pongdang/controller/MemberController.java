package dev.be.pongdang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.be.pongdang.common.response.CommonResponse;
import dev.be.pongdang.domain.member.MemberResult.MemberRequest;
import dev.be.pongdang.domain.member.MemberResult.MemberResponse;
import dev.be.pongdang.domain.member.MemberDTO;
import dev.be.pongdang.entity.Member;
import dev.be.pongdang.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "Member 정보 조회")
    @GetMapping
    public CommonResponse getMember(@RequestParam("mid") String mid) {
        Member member = memberService.getMember(mid);
        MemberResponse response = getMemberResponse(member);
        return new CommonResponse<>(response);
    }

    @Operation(summary = "Member 생성")
    @PostMapping("/signup")
    public CommonResponse createMember(@RequestBody MemberRequest request) {
        MemberDTO memberDTO = MemberDTO.builder()
                                       .nickName(request.getNickName())
                                       .password(request.getPassword())
                                       .build();
        Member newMember = memberService.createMember(memberDTO);
        MemberResponse response = getMemberResponse(newMember);
        return new CommonResponse<>(response);
    }

    private MemberResponse getMemberResponse(Member member) {
        return MemberResponse.builder()
                             .mid(member.getMid())
                             .nickName(member.getNickName())
                             .build();
    }

}
