package br.ce.fortaleza.hakney.sistemabancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ce.fortaleza.hakney.sistemabancario.DTO.ContaDTO;
import br.ce.fortaleza.hakney.sistemabancario.entity.Transferencia;
import br.ce.fortaleza.hakney.sistemabancario.service.TransferenciaService;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	TransferenciaService transferenciaService;	
	
	@PutMapping("/transferir")
	private ResponseEntity<String> transferir(@RequestBody ContaDTO dadosTransferencia) throws Exception {
		return transferenciaService.transferirValor(dadosTransferencia);
	}
	
	@PutMapping("/estornar")
	private ResponseEntity<String> estornarTransferencia(@RequestBody Transferencia transferencia) throws Exception {
		return transferenciaService.estornarTransferencia(transferencia.getComprovanteTransferencia());
	}
	
	@PostMapping("/programartransferencia")
	private ResponseEntity<String> programarTransferencia(@RequestBody ContaDTO conta) throws Exception {
		return transferenciaService.programarTransferencia(conta);
	}
	
	@PostMapping("/listartransferenciasfuturas")
	private List<Transferencia> listarTransferenciasFuturas(@RequestBody ContaDTO conta){
		return transferenciaService.buscarTransferenciaFutura(conta.getNumeroContaOrigem());
	}
}
