package com.hitdavid.service.event;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by David on 2017/6/12.
 */
@Component
@EnableRabbit
@RabbitListener(
    containerFactory = "rabbitListenerContainerFactory",
    bindings = @QueueBinding(
        value = @Queue(value = Receiver.QueueName, durable = "false"),
        exchange = @Exchange(value = Receiver.ExchangeName, type = ExchangeTypes.TOPIC)
    )
)

public class Receiver {

    public final static String QueueName = "zuul-config-queue";
    public final static String ExchangeName = "zuul-config-exchange";

    @Autowired RefreshRouteService refreshRouteService;

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
        refreshRouteService.refreshRoute();
    }

}