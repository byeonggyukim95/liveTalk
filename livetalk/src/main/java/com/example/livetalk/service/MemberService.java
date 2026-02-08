package com.example.livetalk.service;

import com.example.livetalk.common.encryptor.SHA256Encryptor;
import com.example.livetalk.common.enums.ExceptionCode;
import com.example.livetalk.common.repo.MemberRepository;
import com.example.livetalk.model.LiveTalkException;
import com.example.livetalk.model.Member;
import com.example.livetalk.model.req.MemberLoginReq;
import com.example.livetalk.model.req.MemberSignUpReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void signUp(MemberSignUpReq memberSignUpReq) throws Exception {
        memberRepository.findByMemberId(memberSignUpReq.memberId())
                .ifPresent(data -> {
                    throw new LiveTalkException(ExceptionCode.DUPLICATE_MEMBER_ID);
                });

        memberRepository.save(new Member(memberSignUpReq.memberId(), SHA256Encryptor.encrypt(memberSignUpReq.password())));
    }

    public Member findMemberByMemberNameAndPassword(MemberLoginReq memberLoginReq) throws Exception {
        return memberRepository.findByMemberIdAndPassword(memberLoginReq.memberId(), SHA256Encryptor.encrypt(memberLoginReq.password()))
                .orElseThrow(() -> new LiveTalkException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public Member findMemberByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new LiveTalkException(ExceptionCode.MEMBER_NOT_FOUND));
    }

}
