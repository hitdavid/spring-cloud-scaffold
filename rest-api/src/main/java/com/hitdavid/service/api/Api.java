package com.hitdavid.service.api;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by David on 2017/5/26.
 */
@RestController
@RequestMapping(value="/api")
@RabbitListener(
    containerFactory = "rabbitListenerContainerFactory",
    bindings = @QueueBinding(
        value = @Queue(value = Api.QueueName, durable = "false"),
        exchange = @Exchange(value = Api.ExchangeName, type = ExchangeTypes.TOPIC)
    )
)
public class Api {

    public final static String QueueName = "zuul-config-queue";
    public final static String ExchangeName = "zuul-config-exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RequestMapping("/sendMsg")
    public String send(
        HttpServletRequest request,
        HttpServletResponse response,
        HttpSession session) {

        String context = "hello " + new Date();
        System.out.println("Sending message..." + context);

        rabbitTemplate.convertAndSend( Api.QueueName, context);

        return "OK";
    }

}
