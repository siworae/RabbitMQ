package com.siworae.spring_rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringRabbitmq {
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-rabbitmq.xml");
        AmqpTemplate amqpTemplate= (AmqpTemplate) ac.getBean("amqpTemplate");
        //amqpTemplate.convertAndSend("hello spring rabbitmq");
        amqpTemplate.convertAndSend("springExchange02","query.queryById","hello spring rabbitmq");
    }
}