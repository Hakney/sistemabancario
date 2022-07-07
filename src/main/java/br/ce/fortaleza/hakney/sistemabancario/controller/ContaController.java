package br.ce.fortaleza.hakney.sistemabancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ce.fortaleza.hakney.sistemabancario.VO.ContaDTO;
import br.ce.fortaleza.hakney.sistemabancario.entity.Conta;
import br.ce.fortaleza.hakney.sistemabancario.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	ContaService contaService;
	
	@PostMapping("/criarconta")
	private ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
		return contaService.criarConta(conta);
	}
	
	@GetMapping("/listarcontas")
	private List<Conta> listarContas(){
		return contaService.listarContas();
	}
	
	@PutMapping("/depositar/{numeroConta}")
	private ResponseEntity<String>  depositar(@RequestBody ContaDTO conta) {
		return contaService.depositar(conta);
	}
	
	@PostMapping("/consultarsaldo")
	private ResponseEntity<String>  consultarSaldo(@RequestBody ContaDTO conta) {
		return contaService.consultarSaldo(conta.getNumeroContaOrigem());
	}
}
