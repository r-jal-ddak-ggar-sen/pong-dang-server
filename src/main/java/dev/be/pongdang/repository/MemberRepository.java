package dev.be.pongdang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.be.pongdang.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMid(String mid);

    Optional<Member> findByNickName(String nickName);
}
