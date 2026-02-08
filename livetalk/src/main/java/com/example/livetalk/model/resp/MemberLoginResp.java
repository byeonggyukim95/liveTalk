package com.example.livetalk.model.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginResp {

    private String token;
    private String memberId;

    public MemberLoginResp(String token, String memberId) {
        this.token = token;
        this.memberId = memberId;
    }

}
