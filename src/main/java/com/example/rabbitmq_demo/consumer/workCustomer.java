package com.example.rabbitmq_demo.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class workCustomer {

    //消费者1
    @RabbitListener(queuesToDeclare = @Queue("qingfeng"))
    public void receive1(String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {

        try {
            //模拟出现异常
//            int a = 1 / 0;
            log.info("队列1,标识{}-> msg:{}", deliveryTag, msg);
//            log.info("收到的消息标识-> deliveryTag:{}", deliveryTag);
//            log.info("收到的消息-> channel:{}", channel.toString());
            // 数值越大,权重越高,接收到的消息越多
            channel.basicQos(10);
            //手动签收  channel.basicAck(消息唯一标识,是否批量签收);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            e.printStackTrace();
            //channel.basicNack(deliveryTag:消息的唯一标识,multiple:是否批量处理,requeue:是否重新放入队列);
            //消息出现异常时，若requeue=false，则该消息会被放入死信队列，若没有配置死信队列则该消息会丢失。
            channel.basicNack(deliveryTag, false, false);

        }
    }

    //消费者2
    @RabbitListener(queuesToDeclare = @Queue("qingfeng"))
    public void receive2(String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        try {
            //模拟出现异常
//            int a = 1 / 0;
            log.info("队列2,标识{}-> msg:{}", deliveryTag, msg);
            Thread.sleep(3000);
//            log.info("收到的消息标识-> deliveryTag:{}", deliveryTag);
//            log.info("收到的消息-> channel:{}", channel.toString());
            //手动签收  channel.basicAck(消息唯一标识,是否批量签收);
            channel.basicQos(1);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            e.printStackTrace();
            //channel.basicNack(deliveryTag:消息的唯一标识,multiple:是否批量处理,requeue:是否重新放入队列);
            //消息出现异常时，若requeue=false，则该消息会被放入死信队列，若没有配置死信队列则该消息会丢失。
            channel.basicNack(deliveryTag, false, false);

        }
    }

    //消费者3
    @RabbitListener(queuesToDeclare = @Queue("qingfeng"))
    public void receive3(String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {

        try {
            //模拟出现异常
//            int a = 1 / 0;
            log.info("队列3,标识{}-> msg:{}", deliveryTag, msg);
//            log.info("收到的消息标识-> deliveryTag:{}", deliveryTag);
//            log.info("收到的消息-> channel:{}", channel.toString());
            // 数值越大,权重越高,接收到的消息越多
            channel.basicQos(1);
            //手动签收  channel.basicAck(消息唯一标识,是否批量签收);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            e.printStackTrace();
            //channel.basicNack(deliveryTag:消息的唯一标识,multiple:是否批量处理,requeue:是否重新放入队列);
            //消息出现异常时，若requeue=false，则该消息会被放入死信队列，若没有配置死信队列则该消息会丢失。
            channel.basicNack(deliveryTag, false, false);

        }
    }
}