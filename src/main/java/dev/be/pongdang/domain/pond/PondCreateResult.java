package dev.be.pongdang.domain.pond;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class PondCreateResult {

    @Getter // 해당 객체에 있는 값을 개발자가 읽기 위해 필요
    @Setter // Spring이 해당 객체 필드에 값을 넣어주기 위해 필요
    @NoArgsConstructor
    @JsonInclude(Include.NON_NULL)
    public static class PondRequest {
        private String mid;
        private String title;
        private List<String> friendList;
        private String backgroundUrl;
    }

    @Builder
    @Setter
    @Getter
    @JsonInclude(Include.NON_NULL)
    public static class PondResponse {
        private Long pondId;
    }
}
