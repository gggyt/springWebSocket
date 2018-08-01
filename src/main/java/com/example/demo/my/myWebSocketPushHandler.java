package com.example.demo.my;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class myWebSocketPushHandler extends TextWebSocketHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final List<WebSocketSession> userList= new ArrayList<>();

    /*
    用户进入系统监听
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        logger.info("XXX用户进入系统");
        logger.info("用户信息:"+session.getAttributes());

        Map<String, Object> map = session.getAttributes();

        for (String key: map.keySet()){
            logger.info("key: "+key+"  and value:"+map.get(key));
        }
        userList.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
        sendMessageToUsers(message);
        logger.info("系统处理XXX用户的请求");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
        if (session.isOpen()) {
            session.close();
        }
        userList.remove(session);
        logger.info("XXX用户退出系统");
    }

    /*
    自定义函数
    给所有在线用户发送信息
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user:userList) {
            try{
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getLocalizedMessage());
            }
        }
    }

    public void sendMessageToUser(String userId, TextMessage message) {
        for (WebSocketSession user:userList) {
            if (user.getAttributes().get("userId").equals(userId)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getLocalizedMessage());
                }
            }
        }
    }

}
