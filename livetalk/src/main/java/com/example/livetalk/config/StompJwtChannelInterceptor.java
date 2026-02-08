package com.example.livetalk.config;

import com.example.livetalk.common.jwt.JwtProvider;
import com.example.livetalk.model.StompPrincipal;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

public class StompJwtChannelInterceptor implements ChannelInterceptor {

    private final JwtProvider jwtProvider;

    public StompJwtChannelInterceptor(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor == null) return message;

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String auth = accessor.getFirstNativeHeader("Authorization");
            if (auth == null || !auth.startsWith("Bearer ")) {
                return message;
            }

            String token = auth.substring(7);
            String memberId = jwtProvider.getMemberId(token);

            accessor.setUser(new StompPrincipal(memberId));
        }

        return message;
    }

}
