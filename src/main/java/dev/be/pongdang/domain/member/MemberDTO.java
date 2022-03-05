package dev.be.pongdang.domain.member;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberDTO {
    private String nickName;
    private String password;
}
