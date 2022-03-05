package dev.be.pongdang.domain.login;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginDupNickNameDTO {
    private String nickName;
}
