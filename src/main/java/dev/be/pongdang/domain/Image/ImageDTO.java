package dev.be.pongdang.domain.Image;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ImageDTO {

    private List<String> backgroundUrlList;
}
