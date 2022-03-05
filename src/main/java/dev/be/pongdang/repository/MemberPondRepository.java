package dev.be.pongdang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.be.pongdang.entity.Member;
import dev.be.pongdang.entity.MemberPond;
import dev.be.pongdang.entity.Pond;

public interface MemberPondRepository extends JpaRepository<MemberPond, Long> {

    List<MemberPond> findByMember(Member member);

    List<MemberPond> findByPond(Pond pond);

}
