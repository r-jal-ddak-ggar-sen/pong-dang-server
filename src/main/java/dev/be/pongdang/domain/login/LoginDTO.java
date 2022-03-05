package dev.be.pongdang.domain.login;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginDTO {
    private boolean isLogin;
    private String mid;
}
