package br.ce.fortaleza.hakney.sistemabancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ce.fortaleza.hakney.sistemabancario.config.QueueSender;

@RestController
@RequestMapping("/rabbitmq")
public class EnviarMensagensController {

	@Autowired
    private QueueSender queueSender;

	@GetMapping("/teste")
    public String send(){
        queueSender.send("Testando RabbitMq");
        return "ok. done";
    }
    
}
