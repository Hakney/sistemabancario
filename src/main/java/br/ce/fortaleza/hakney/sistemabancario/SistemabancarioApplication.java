package br.ce.fortaleza.hakney.sistemabancario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.ce.fortaleza.hakney.sistemabancario")
public class SistemabancarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemabancarioApplication.class, args);
	}

}
