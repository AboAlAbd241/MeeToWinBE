//package com.meetowin.meetowin.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
//import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
//
//@Configuration
//public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
//
//
//    @Override
//    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
////        messages
//////                .nullDestMatcher().authenticated()
////                .simpSubscribeDestMatchers("/user/queue/errors").permitAll()
////                .simpDestMatchers("/app/**").permitAll()
////                .simpSubscribeDestMatchers("/user/**", "/topic/public/*").permitAll()
//////                .simpTypeMatchers(MESSAGE, SUBSCRIBE).denyAll()
////                .anyMessage().denyAll();
//        messages
//                .simpDestMatchers("/app/**","/ws/**","topic/**","/**").permitAll()
//                .anyMessage().authenticated();
//
//    }
//}
