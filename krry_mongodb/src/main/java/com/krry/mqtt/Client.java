package com.krry.mqtt;

import org.fusesource.mqtt.client.*;

import java.net.URISyntaxException;

/**
 * 客户端订阅消息
 * date: 2020/1/17 9:54 <br>
 * author: Administrator <br>
 */
public class Client {
    private final static String CONNECTION_STRING = "tcp://127.0.0.1:1883";
    private final static boolean CLEAN_START = true;
    private final static short KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s
    private final static String CLIENT_ID = "client";
    public static Topic[] topics = {
            new Topic("mqtt/aaa",QoS.EXACTLY_ONCE),//2 只有一次
            new Topic("mqtt/bbb",QoS.AT_LEAST_ONCE),//1 至少一次
            new Topic("mqtt/ccc",QoS.AT_MOST_ONCE)//0 最多一次
    };
    public final static long RECONNECTION_ATTEMPT_MAX = 6;
    public final static long RECONNECTION_DELAY = 2000;

    public final static int SEND_BUFFER_SIZE = 64;//发送最大缓存为2M
    public static void main(String[] args){
        MQTT mqtt = new MQTT();
        try {
            // 设置mqtt broker的ip和端口
            mqtt.setHost(CONNECTION_STRING);
            // 连接前清空会话信息
            mqtt.setCleanSession(CLEAN_START);
            // 设置重新连接的次数
            mqtt.setConnectAttemptsMax(RECONNECTION_ATTEMPT_MAX);
            // 设置重连的间隔时间
            mqtt.setReconnectDelay(RECONNECTION_DELAY);
            // 设置心跳时间
            mqtt.setKeepAlive(KEEP_ALIVE);
            // 设置缓冲的大小
            mqtt.setSendBufferSize(SEND_BUFFER_SIZE);
            // 设置客户端id
            mqtt.setClientId(CLIENT_ID);
            // 获取mqtt的连接对象BlockingConnection  ,采用Future模式 订阅主题
            final FutureConnection connection = mqtt.futureConnection();
            connection.connect();
            connection.subscribe(topics);
            while (true){
                Future<Message> futureMessage = connection.receive();
                try {
                    Message message = futureMessage.await();
                    System.out.println("MQTTFutureClient.Receive Message " + "Topic Title :" + message.getTopic() + " context :"
                            + String.valueOf(message.getPayloadBuffer()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
