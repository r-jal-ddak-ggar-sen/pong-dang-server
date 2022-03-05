package dev.be.pongdang.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.be.pongdang.common.utils.PasswordUtil;
import dev.be.pongdang.domain.login.LoginDTO;
import dev.be.pongdang.domain.login.LoginDupNickNameDTO;
import dev.be.pongdang.domain.member.MemberDTO;
import dev.be.pongdang.entity.Member;
import dev.be.pongdang.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final PasswordUtil passwordUtil;
    private final MemberRepository memberRepository;

    public LoginDTO login(MemberDTO memberDTO) {

        Optional<Member> memberOpt = memberRepository.findByNickName(memberDTO.getNickName());

        if (!memberOpt.isPresent()) {
            return LoginDTO.builder()
                           .isLogin(false)
                           .build();
        }

        Member member = memberOpt.get();

        boolean isSameUser = passwordUtil.isPwEquals(memberDTO.getPassword(), member.getPassword());

        if (isSameUser == false) {
            return LoginDTO.builder()
                           .isLogin(false)
                           .build();
        }

        return LoginDTO.builder()
                       .isLogin(true)
                       .mid(member.getMid())
                       .build();
    }

    public boolean checkNickNameDuplicate(LoginDupNickNameDTO dto) {
        Optional<Member> memberOtp = memberRepository.findByNickName(dto.getNickName());

        if (memberOtp.isPresent()) {
            return true;
        }
        return false;
    }

}
