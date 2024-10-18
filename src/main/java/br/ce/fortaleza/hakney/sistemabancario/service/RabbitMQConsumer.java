package br.ce.fortaleza.hakney.sistemabancario.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = "transactionQueue")
    public void receiveMessageFromTransactionQueue(String message) {
        System.out.println("Recebida mensagem da fila transactionQueue: " + message);
    }

    @RabbitListener(queues = "reportQueue")
    public void receiveMessageFromReportQueue(String message) {
        System.out.println("Recebida mensagem da fila reportQueue: " + message);
    }
}
