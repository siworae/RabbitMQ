package com.siworae.subscrible.direct;

import com.rabbitmq.client.*;
import com.siworae.util.ConnectionUtil;

import java.io.IOException;

public class ErrorReceiver {
    public static final String EXCHANGE_NAME="ex_02";
    public static final String QUEUE_NAME="error";
    public static void main(String[] args) throws  Exception{
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        /**
         * 将队列绑定到交换机
         *    添加绑定key key 可以绑定多个
         */
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"error");
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body,"utf-8");
                System.out.println("error消费方收到消息-->"+msg);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
