package dev.be.pongdang.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.be.pongdang.common.enums.response.ReturnCode;
import dev.be.pongdang.common.exception.CustomException;
import dev.be.pongdang.common.utils.PasswordUtil;
import dev.be.pongdang.domain.member.MemberDTO;
import dev.be.pongdang.entity.Member;
import dev.be.pongdang.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordUtil passwordUtil;
    private final MemberRepository memberRepository;

    public Member getMember(String mid) {
        Optional<Member> memberOpt = memberRepository.findByMid(mid);
        validateExistMember(memberOpt);
        return memberOpt.get();
    }

    private void validateExistMember(Optional<Member> memberOpt) {
        if (memberOpt.isPresent()) {
            return;
        }
        throw new CustomException(ReturnCode.NOT_EXIST);
    }

    public Member createMember(MemberDTO memberDTO) {
        validateExistNickName(memberDTO.getNickName());

        Member newMember = Member.builder()
                                 .mid(passwordUtil.getEncodedValue(memberDTO.getNickName()).substring(1, 31))
                                 .nickName(memberDTO.getNickName())
                                 .password(passwordUtil.getEncodedValue(memberDTO.getPassword()))
                                 .build();
        memberRepository.save(newMember);
        return newMember;
    }

    private void validateExistNickName(String nickName) {
        memberRepository.findByNickName(nickName)
                        .ifPresent(i -> {
                            throw new CustomException(ReturnCode.EXIST_NICKNAME);
                        });
    }

}
