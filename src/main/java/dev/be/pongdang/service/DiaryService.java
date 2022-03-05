package dev.be.pongdang.service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.be.pongdang.common.enums.MoodEnum;
import dev.be.pongdang.common.enums.response.ReturnCode;
import dev.be.pongdang.common.exception.CustomException;
import dev.be.pongdang.domain.Diary.DiaryReadDTO;
import dev.be.pongdang.domain.Diary.DiaryReadResult.DiaryReadResponse;
import dev.be.pongdang.domain.Diary.DiaryWriteDTO;
import dev.be.pongdang.entity.Diary;
import dev.be.pongdang.entity.Member;
import dev.be.pongdang.entity.Pond;
import dev.be.pongdang.repository.DiaryRepository;
import dev.be.pongdang.repository.PondRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final PondRepository pondRepository;
    private final MemberService memberService;

    public void writeDiary(DiaryWriteDTO diaryWriteDTO) {
        Pond pond = pondRepository.findById(diaryWriteDTO.getPondId()).orElseThrow(() -> {
            throw new CustomException(ReturnCode.NOT_EXIST, "요청한 연못이 존재하지 않습니다.");
        });

        Member member = memberService.getMember(diaryWriteDTO.getMid());

        Diary newDiary = Diary.builder()
                              .mood(diaryWriteDTO.getMood())
                              .content(diaryWriteDTO.getContent())
                              .pond(pond)
                              .member(member)
                              .build();
        newDiary.addDiary(pond);
        diaryRepository.save(newDiary);
    }

    public DiaryReadResponse readDiary(DiaryReadDTO dto) {
        Diary diary = diaryRepository.findById(dto.getDiaryId()).orElseThrow(() -> {
            throw new CustomException(ReturnCode.NOT_EXIST, "요청한 일기가 존재하지 않습니다.");
        });

        return DiaryReadResponse.builder()
                                .diaryId(diary.getId())
                                .mood(diary.getMood())
                                .content(diary.getContent())
                                .createdDate(diary.getCreatedDate()
                                                  .format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                                .writerMid(diary.getMember().getMid())
                                .build();

    }
}
