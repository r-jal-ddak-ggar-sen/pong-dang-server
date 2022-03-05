package dev.be.pongdang.domain.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LoginDupNickNameResult {

    @Getter // 해당 객체에 있는 값을 개발자가 읽기 위해 필요
    @Setter // Spring이 해당 객체 필드에 값을 넣어주기 위해 필요
    @NoArgsConstructor
    @JsonInclude(Include.NON_NULL)
    public static class LoginDupNickNameRequest {
        private String nickName;
    }

    @Builder
    @Setter
    @Getter
    @JsonInclude(Include.NON_NULL)
    public static class LoginDupNickNameResponse {
        private boolean isAlreadyExistNickName;
    }
}
