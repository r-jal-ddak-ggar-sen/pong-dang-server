package dev.be.pongdang.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.be.pongdang.common.utils.PasswordUtil;
import dev.be.pongdang.domain.member.MemberDTO;
import dev.be.pongdang.entity.Member;
import dev.be.pongdang.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final PasswordUtil passwordUtil;
    private final MemberRepository memberRepository;

    public boolean login(MemberDTO memberDTO) {

        Optional<Member> memberOpt = memberRepository.findByNickName(memberDTO.getNickName());

        if (!memberOpt.isPresent()) {
            return false;
        }

        Member member = memberOpt.get();
        return passwordUtil.isPwEquals(memberDTO.getPassword(), member.getPassword());

    }

}
