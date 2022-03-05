package dev.be.pongdang.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.be.pongdang.common.enums.response.ReturnCode;
import dev.be.pongdang.common.response.CommonResponse;
import dev.be.pongdang.domain.login.LoginDTO;
import dev.be.pongdang.domain.login.LoginDupNickNameDTO;
import dev.be.pongdang.domain.login.LoginDupNickNameResult.LoginDupNickNameRequest;
import dev.be.pongdang.domain.login.LoginDupNickNameResult.LoginDupNickNameResponse;
import dev.be.pongdang.domain.login.LoginResult.LoginResponse;
import dev.be.pongdang.domain.login.LoginResult.MemberRequest;
import dev.be.pongdang.domain.member.MemberDTO;
import dev.be.pongdang.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Operation(summary = "Login 시도")
    @PostMapping
    public CommonResponse login(@RequestBody MemberRequest request) {
        MemberDTO memberDTO = MemberDTO.builder()
                                       .nickName(request.getNickName())
                                       .password(request.getPassword())
                                       .build();
        LoginDTO loginDTO = loginService.login(memberDTO);
        LoginResponse loginResponse = getLoginResponse(loginDTO);

        if (loginResponse.isLogin() == false) {
            return new CommonResponse<>(ReturnCode.LOGIN_FAIL, loginResponse);
        }
        return new CommonResponse<>(loginResponse);
    }

    private LoginResponse getLoginResponse(LoginDTO loginDTO) {
        LoginResponse loginResponse = LoginResponse.builder()
                                                   .isLogin(loginDTO.isLogin())
                                                   .build();
        if (loginDTO.isLogin()) {
            loginResponse.setMid(loginDTO.getMid());
        }

        return loginResponse;
    }

    @Operation(summary = "닉네임 중복 체크")
    @PostMapping("/check/dup/nickname")
    public CommonResponse checkNickNameDuplicate(@RequestBody LoginDupNickNameRequest request) {
        LoginDupNickNameDTO dto = LoginDupNickNameDTO.builder()
                                                     .nickName(request.getNickName())
                                                     .build();
        return new CommonResponse<>(
                LoginDupNickNameResponse.builder()
                                        .isAlreadyExistNickName(loginService.checkNickNameDuplicate(dto))
                                        .build());
    }
}
