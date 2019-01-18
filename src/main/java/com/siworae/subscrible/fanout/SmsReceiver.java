package com.siworae.subscrible.fanout;

import com.rabbitmq.client.*;
import com.siworae.util.ConnectionUtil;

import java.io.IOException;

public class SmsReceiver {
    public static final String EXCHANGE_NAME="ex_01";
    public static final String QUEUE_NAME="sms";
    public static void main(String[] args) throws  Exception{
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        /**
         * 将队列绑定到交换机
         */
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body,"utf-8");
                System.out.println("短信消费方收到消息-->"+msg);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
