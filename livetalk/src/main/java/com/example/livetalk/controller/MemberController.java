package com.example.livetalk.controller;

import com.example.livetalk.common.jwt.JwtProvider;
import com.example.livetalk.model.Member;
import com.example.livetalk.model.req.MemberLoginReq;
import com.example.livetalk.model.req.MemberSignUpReq;
import com.example.livetalk.model.resp.MemberLoginResp;
import com.example.livetalk.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    public MemberController(
            MemberService memberService,
            JwtProvider jwtProvider) {
        this.memberService = memberService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody @Valid MemberSignUpReq memberSignUpReq) throws Exception {
        memberService.signUp(memberSignUpReq);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public MemberLoginResp login(@RequestBody @Valid MemberLoginReq memberLoginReq) throws Exception {
        Member member = memberService.findMemberByMemberNameAndPassword(memberLoginReq);
        String token = jwtProvider.createToken(member.getMemberId());
        return new MemberLoginResp(token, member.getMemberId());
    }

}