package com.siworae.subscrible.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.siworae.util.ConnectionUtil;

public class Sender {
    public static final String EXCHANGE_NAME="ex_02";
    public static void main(String[] args)  throws  Exception{
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();
        /**
         * direct:绑定到交换机上的所有队列  交换机路由消息 通知指定具体的路由key进行有选择的路由
         */
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String msg="error 消息";
        channel.basicPublish(EXCHANGE_NAME,"error",null,msg.getBytes("utf-8"));
        channel.close();
        connection.close();
    }
}
