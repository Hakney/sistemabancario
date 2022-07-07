package br.ce.fortaleza.hakney.sistemabancario.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ce.fortaleza.hakney.sistemabancario.VO.ContaDTO;
import br.ce.fortaleza.hakney.sistemabancario.entity.Conta;
import br.ce.fortaleza.hakney.sistemabancario.repository.ContaRepository;
import lombok.SneakyThrows;

@Service
public class ContaService {

	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	TransferenciaService transferenciaService;
	
	public ResponseEntity<String> consultarSaldo(String numeroConta) {
		return contaRepository.consultarSaldo(numeroConta);
		
	}

	public ResponseEntity<Conta> criarConta(Conta conta) {
		return new ResponseEntity<Conta>(contaRepository.save(conta), HttpStatus.CREATED);
	}

	public List<Conta> listarContas() {
		return contaRepository.findAll();
	}

	public ResponseEntity<String> depositar(ContaDTO conta) {
		Conta contaAtual = contaRepository.findByNumeroConta(conta.getNumeroContaOrigem());
		if (Objects.nonNull(contaAtual)) {
			contaAtual.depositarValor(conta.getValorDeposito());	
			contaRepository.save(contaAtual);
			
			return ResponseEntity.ok("Depósito realizado! -> Novo saldo: "  + contaAtual.getSaldo()); 			
		}

		return ResponseEntity.ok("Conta não encontrada!");
	}


}
