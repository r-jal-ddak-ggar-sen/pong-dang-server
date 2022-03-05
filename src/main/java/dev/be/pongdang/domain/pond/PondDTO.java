package dev.be.pongdang.domain.pond;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PondDTO {
    private String mid;
    private String title;
    private List<String> friendList;
    private String backgroundUrl;
}
