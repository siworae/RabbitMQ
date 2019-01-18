package com.siworae.workQueues.rr;

import com.rabbitmq.client.*;
import com.siworae.util.ConnectionUtil;

import java.io.IOException;

public class Receiver02 {
    public static final String QUEUE_NAME="work_01";
    public static void main(String[] args) throws  Exception{
        /**
         * 获取连接
         *
         */
        Connection connection= ConnectionUtil.getConnection();

        /**
         * 创建通道
         */
        Channel channel=connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);


        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body,"utf-8");
                System.out.println("消费者02接收方收到消息-->"+msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        /**
         * 将队列加入到监听
         */
        channel.basicConsume(QUEUE_NAME,true,consumer);


    }
}
