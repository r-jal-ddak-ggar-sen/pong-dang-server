package dev.be.pongdang.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.be.pongdang.common.enums.response.ReturnCode;
import dev.be.pongdang.common.exception.CustomException;
import dev.be.pongdang.domain.pond.PondCreateDTO;
import dev.be.pongdang.domain.pond.PondDTO;
import dev.be.pongdang.domain.pond.PondSearchDTO;
import dev.be.pongdang.domain.pond.PondSearchDiaryDTO;
import dev.be.pongdang.domain.pond.PondSearchDiaryResult.Diarys;
import dev.be.pongdang.domain.pond.PondSearchDiaryResult.PondSearchDiaryResponse;
import dev.be.pongdang.domain.pond.PondSearchResult.Ponds;
import dev.be.pongdang.entity.Diary;
import dev.be.pongdang.entity.Member;
import dev.be.pongdang.entity.MemberPond;
import dev.be.pongdang.entity.Pond;
import dev.be.pongdang.repository.MemberPondRepository;
import dev.be.pongdang.repository.PondRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PondService {

    private final MemberService memberService;
    private final MemberPondRepository memberPondRepository;
    private final PondRepository pondRepository;
    private final MailService mailService;

    public List<Ponds> searchMyPondList(PondSearchDTO pondSearchDTO) {
        List<Ponds> pondsList = new ArrayList<>();

        Member member = memberService.getMember(pondSearchDTO.getMid());

        List<Pond> myPondList = memberPondRepository.findByMember(member).stream()
                                                    .map(i -> i.getPond())
                                                    .collect(Collectors.toList());

        myPondList.stream().forEach(i -> {
            int pondMemerCount = memberPondRepository.findByPond(i).size();
            Ponds ponds = Ponds.builder()
                               .pondId(i.getId())
                               .title(i.getTitle())
                               .ownerMemberNickName(i.getMakerMember().getNickName())
                               .memberCount(pondMemerCount)
                               .backgroundUrl(i.getBackgroundUrl())
                               .build();
            pondsList.add(ponds);
        });

        return pondsList;
    }

    public PondSearchDiaryResponse searchDiaryListInPond(PondSearchDiaryDTO dto) {
        Pond pond = pondRepository.findById(dto.getPondId()).orElseThrow(() -> {
            throw new CustomException(ReturnCode.NOT_EXIST, "????????? ????????? ???????????? ????????????.");
        });

        List<Diary> diaryEntityList = pond.getDiaryList();
        List<Diarys> diaryList = new ArrayList<>();
        diaryEntityList.stream().forEach(i -> {
            diaryList.add(Diarys.builder()
                                .diaryId(i.getId())
                                .mood(i.getMood())
                                .content(i.getContent())
                                .writerMid(i.getMember().getMid())
                                .createdDate(i.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                                .build());
        });

        return PondSearchDiaryResponse.builder()
                                      .diaryList(diaryList)
                                      .build();
    }

    public PondCreateDTO createPond(PondDTO pondDTO) {
        String makerMid = pondDTO.getMid();
        Member makerMember = memberService.getMember(makerMid);

        List<String> friendList = pondDTO.getFriendList();
        List<Member> memberList = new ArrayList<>();
        friendList.stream().forEach(i -> memberList.add(memberService.getMemberByNickName(i)));

        Pond pond = Pond.builder()
                        .title(pondDTO.getTitle())
                        .backgroundUrl(pondDTO.getBackgroundUrl())
                        .makerMember(makerMember)
                        .build();
        Pond newPond = pondRepository.save(pond);
        memberList.add(makerMember);
        memberList.stream().forEach(i -> {
            MemberPond newMemberPond = MemberPond.builder()
                                                 .member(i)
                                                 .pond(newPond)
                                                 .build();
            memberPondRepository.save(newMemberPond);
        });

        mailService.sendMail();
        return PondCreateDTO.builder()
                            .pondId(pond.getId())
                            .build();
    }

}