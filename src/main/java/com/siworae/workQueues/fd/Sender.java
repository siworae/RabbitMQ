package com.siworae.workQueues.fd;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.siworae.util.ConnectionUtil;

public class Sender {
    public static final String QUEUE_NAME="work_02";
    public static void main(String[] args) throws  Exception{

        /**
         * 获取连接
         */
        Connection connection= ConnectionUtil.getConnection();

        /**
         * 创建通道
         */
        Channel channel=connection.createChannel();
        channel.basicQos(1);
        /**
         * 创建队列
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for(int i=0;i<30;i++){
            String msg="hello rabbitmq"+i;
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes("utf-8"));
            Thread.sleep(1000);
            System.out.println("消息发送成功-->"+msg);
        }

        channel.close();
        connection.close();
    }
}
