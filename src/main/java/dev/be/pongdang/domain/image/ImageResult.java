package dev.be.pongdang.domain.image;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ImageResult {

    @Builder
    @Setter
    @Getter
    @JsonInclude(Include.NON_NULL)
    public static class MemberResponse {
        private List<String> backgroundUrl;
    }
}
