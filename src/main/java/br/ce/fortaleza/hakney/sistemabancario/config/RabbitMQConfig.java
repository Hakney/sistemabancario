package br.ce.fortaleza.hakney.sistemabancario.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue transactionQueue() {
        return new Queue("transactionQueue", false);
    }

    @Bean
    public Queue reportQueue() {
        return new Queue("reportQueue", false);
    }
}