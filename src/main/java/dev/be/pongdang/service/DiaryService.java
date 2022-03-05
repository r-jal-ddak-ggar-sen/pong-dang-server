package dev.be.pongdang.service;

import org.springframework.stereotype.Service;

import dev.be.pongdang.common.enums.response.ReturnCode;
import dev.be.pongdang.common.exception.CustomException;
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
        diaryRepository.save(newDiary);
    }
}
