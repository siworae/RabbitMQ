package com.siworae.subscrible.topic;

import com.rabbitmq.client.*;
import com.siworae.util.ConnectionUtil;

import java.io.IOException;

public class UpdateReceiver {
    public static final String EXCHANGE_NAME="ex_03";
    public static final String QUEUE_NAME="update";
    public static void main(String[] args) throws  Exception{
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        /**
         * 将队列绑定到交换机
         *    添加绑定key key 可以绑定多个
         */
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"update.*.*");
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body,"utf-8");
                System.out.println("更新消费方收到消息-->"+msg);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
