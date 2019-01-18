package com.siworae.subscrible.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.siworae.util.ConnectionUtil;

public class Sender {
    public static final String EXCHANGE_NAME="ex_03";
    public static void main(String[] args)  throws  Exception{
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();
        /**
         * topic:根据具体的模式匹配串进行key匹配操作
         */
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String msg="订单_更新";
        channel.basicPublish(EXCHANGE_NAME,"update.order",null,msg.getBytes("utf-8"));
        channel.close();
        connection.close();
    }
}
