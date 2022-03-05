package dev.be.pongdang.domain.pond;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import dev.be.pongdang.common.enums.MoodEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PondSearchDiaryResult {

    @Getter // 해당 객체에 있는 값을 개발자가 읽기 위해 필요
    @Setter // Spring이 해당 객체 필드에 값을 넣어주기 위해 필요
    @NoArgsConstructor
    @JsonInclude(Include.NON_NULL)
    public static class PondSearchDiaryRequest {
    }

    @Builder
    @Setter
    @Getter
    @JsonInclude(Include.NON_NULL)
    public static class PondSearchDiaryResponse {
        private List<Diarys> diaryList;
    }

    // @Getter가 없으면 다음과 같은 에러가 발생한다.
    // No serializer found for class dev.be.pongdang.domain.pond.PondSearchResult$Ponds
    // and no properties discovered to create BeanSerializer
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Diarys {
        private MoodEnum mood;
        private String content;
        private String createdDate;
        private String writerMid;
    }
}
