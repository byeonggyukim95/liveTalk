package com.example.livetalk.common.repo;

import com.example.livetalk.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByMemberIdAndPassword(String memberId, String password);

}
