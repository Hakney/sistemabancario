package br.ce.fortaleza.hakney.sistemabancario;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication(scanBasePackages = "br.ce.fortaleza.hakney.sistemabancario")
public class SistemabancarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemabancarioApplication.class, args);
	}

}
