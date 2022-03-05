package dev.be.pongdang.domain.Diary;

import dev.be.pongdang.common.enums.MoodEnum;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DiaryWriteDTO {
    private Long pondId;
    private String mid;
    private MoodEnum mood;
    private String content;
}
