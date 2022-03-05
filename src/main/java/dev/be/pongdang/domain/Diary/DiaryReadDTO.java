package dev.be.pongdang.domain.Diary;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DiaryReadDTO {
    private String mid;
    private Long diaryId;
}
