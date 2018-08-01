package com.example.demo.my;

import com.example.demo.MyWebSocketInterceptor;
import com.example.demo.WebSocketPushHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
@EnableWebSocket
public class myWebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(createWebSocketPushHandler(), "/myWebSocketServer").addInterceptors(createHhandshakeInterceptor()).setAllowedOrigins("*");
    }

    /**
     *
     * @Title: createHhandshakeInterceptor
     * @Description: 握手拦截器
     * @return
     */
    @Bean
    public HandshakeInterceptor createHhandshakeInterceptor() {
        return new MyWebSocketInterceptor();
    }

    /**
     *
     * @Title: createWebSocketPushHandler
     * @Description: 处理类
     * @return
     */
    @Bean
    public WebSocketHandler createWebSocketPushHandler() {
        return new WebSocketPushHandler();
    }


}
