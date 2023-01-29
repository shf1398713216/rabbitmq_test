package com.example.rabbitmq_demo;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.RabbitUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqDemoApplicationTests {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testworker() {




        for (int i = 1; i <= 15; i++) {
            rabbitTemplate.convertAndSend("qingfeng", "work模型==="+i);
        }

    }
}