package com.xiaojian.clientserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketAutoConfig implements WebSocketMessageBrokerConfigurer {

   //这个方法的作用是添加一个服务端点，来接收客户端的连接。
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/client-bullet")     //开启/client-bullet端点
                .setAllowedOrigins("*")             //允许跨域访问
                .withSockJS();                      //使用sockJS
    }

    //这个方法的作用是定义消息代理，通俗一点讲就是设置消息连接请求的各种规范信息。
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //订阅Broker名称
        //registry.enableSimpleBroker("/topic","/user");
        registry.enableSimpleBroker("/toAll");  //设置服务器广播消息的基础路径
        //全局使用的订阅前缀（客户端订阅路径上会体现出来）
        registry.setApplicationDestinationPrefixes("/app");  //设置客户端订阅消息的基础路径
        //点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        //registry.setUserDestinationPrefix("/user/");
        //registry.setPathMatcher(new AntPathMatcher("."));    //可以以“.”来分割路径，看看类级别的@messageMapping和方法级别的@messageMapping
    }
}