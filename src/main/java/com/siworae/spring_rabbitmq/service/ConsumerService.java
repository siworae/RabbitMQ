package com.siworae.spring_rabbitmq.service;

import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    public  void test(Object obj){
        System.out.println("消息者01收到消息-->"+obj);
    }

    public  void test02(Object obj){
        System.out.println("消息者02收到消息-->"+obj);
    }
}