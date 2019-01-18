package com.siworae.workQueues.fd;

import com.rabbitmq.client.*;
import com.siworae.util.ConnectionUtil;

import java.io.IOException;

public class Receiver01 {
    public static final String QUEUE_NAME="work_02";
    public static void main(String[] args) throws  Exception{
        Connection connection= ConnectionUtil.getConnection();

        /**
         * 创建通道
         */
        Channel channel=connection.createChannel();
        channel.basicQos(1);

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);


        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body,"utf-8");
                System.out.println("消费者01接收方收到消息-->"+msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 收动回执消息给Mq 服务器
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        /**
         * 将队列加入到监听
         */

        channel.basicConsume(QUEUE_NAME,false,consumer);


    }
}
