package com.example.livetalk.common.jwt;

import com.example.livetalk.model.properties.JwtProperties;
import com.example.livetalk.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;


@Component
public class JwtProvider {

    private final SecretKey secretKey;
    private final JwtProperties jwtProperties;
    private final MemberService memberService;

    public JwtProvider(
            JwtProperties jwtProperties,
            MemberService memberService) {
        this.secretKey = Keys.hmacShaKeyFor(jwtProperties.secret().getBytes(StandardCharsets.UTF_8));
        this.jwtProperties = jwtProperties;
        this.memberService = memberService;
    }

    public String createToken(String memberId) {
        return createToken(memberId, jwtProperties.tokenExpiration());
    }

    public String getMemberId(String token) {
        String memberId = getSubject(token);
        memberService.findMemberByMemberId(memberId);

        return memberId;
    }

    private String createToken(String memberId, Long expiration) {
        return Jwts.builder()
                .issuer(jwtProperties.issuer())
                .subject(memberId)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(expiration)))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    private String getSubject(String token) {
        Jws<Claims> claimsJws = parse(token);
        return claimsJws.getPayload().getSubject();
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parser().verifyWith(secretKey).requireIssuer(jwtProperties.issuer()).build().parseSignedClaims(token);
    }

}
