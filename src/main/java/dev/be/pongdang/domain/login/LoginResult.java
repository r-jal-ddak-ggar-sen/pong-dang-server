package dev.be.pongdang.domain.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
public class LoginResult {

    @Getter // 해당 객체에 있는 값을 개발자가 읽기 위해 필요
    @Setter // Spring이 해당 객체 필드에 값을 넣어주기 위해 필요
    @NoArgsConstructor
    public static class MemberRequest {
        private String nickName;
        private String password;
    }

    @Builder
    @Setter
    @Getter
    public static class LoginResponse {
        private boolean isLogin;
    }
}
