package dev.be.pongdang.domain.pond;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PondSearchDiaryDTO {
    private String mid;
    private Long pondId;
}
