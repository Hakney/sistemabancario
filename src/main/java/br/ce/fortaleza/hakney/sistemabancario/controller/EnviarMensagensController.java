package br.ce.fortaleza.hakney.sistemabancario.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class EnviarMensagensController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/testeTransaction")
    public String sendTransactionQueue() {
        rabbitTemplate.convertAndSend("transactionQueue", "Transaction finished!");
        return "Mensagem enviada para a fila transactionQueue.";
    }

    @GetMapping("/testeReport")
    public String sendReportQueue() {
        rabbitTemplate.convertAndSend("reportQueue", "Report Generated!");
        return "Mensagem enviada para a fila reportQueue.";
    }
}
