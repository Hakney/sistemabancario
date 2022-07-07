package br.ce.fortaleza.hakney.sistemabancario.service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ce.fortaleza.hakney.sistemabancario.VO.ContaDTO;
import br.ce.fortaleza.hakney.sistemabancario.entity.Conta;
import br.ce.fortaleza.hakney.sistemabancario.entity.Transferencia;
import br.ce.fortaleza.hakney.sistemabancario.repository.ContaRepository;
import br.ce.fortaleza.hakney.sistemabancario.repository.TransferenciaRepository;
import lombok.SneakyThrows;

@Service
public class TransferenciaService {

	@Autowired
	TransferenciaRepository transferenciaRepository;
	
	@Autowired
	ContaRepository contaRepository;
	
	
	public ResponseEntity<String> transfere(String contaOrigem, String contaDestino, double valorParaTransferir, Integer quantidadeParcela, boolean isEstorno, boolean isParcelado, Transferencia transferencia) throws Exception {
		
		Conta contaOrigemTransferencia = contaRepository.findByNumeroConta(contaOrigem);
		Conta contaDestinoTransferencia = contaRepository.findByNumeroConta(contaDestino);
		
		if(contaOrigemTransferencia.equals(contaDestinoTransferencia)) {
			return ResponseEntity.ok("É necessário que a conta de origem seja diferente da conta de destino");
		}
		
		if(isParcelado && valorParaTransferir > 0) {
			return salvaTransferenciaFutura(valorParaTransferir, quantidadeParcela, isParcelado, contaOrigemTransferencia);
		} else {
		
		if (Objects.nonNull(contaOrigemTransferencia) && Objects.nonNull(contaDestinoTransferencia)) {
					
			boolean isSacarValor = contaOrigemTransferencia.sacarValor(valorParaTransferir);
		
			if (isSacarValor) {
				contaDestinoTransferencia.depositarValor(valorParaTransferir);	
				contaRepository.save(contaOrigemTransferencia);
				contaRepository.save(contaDestinoTransferencia);
				
				String comprovanteTransferencia = gerarComprovante();
				
				Transferencia transferenciaSave = Transferencia
						.builder()
						.valorTransferido(valorParaTransferir)
						.contaOrigem(contaOrigemTransferencia)
						.numeroContaOrigem(contaOrigemTransferencia.getNumeroConta())
						.numeroContaDestino(contaOrigemTransferencia.getNumeroConta())
						.comprovanteTransferencia(comprovanteTransferencia)
						.build();
								
				if(!isEstorno) {
					salvarTransferencia(transferenciaSave);
					return ResponseEntity.ok("Comprovante de Transferência: " + comprovanteTransferencia);				
				} else {
					transferencia.setEstornado(isEstorno);
					transferenciaRepository.save(transferencia);
					return ResponseEntity.ok("Estorno realizado com sucesso!");
				}
				
			}
			else {
				return ResponseEntity.ok("Saldo Insuficiente");
			}
			}  else if (Objects.isNull(contaDestino)) {
				return ResponseEntity.ok("Conta destino não encontrada!");
			} else {
				return ResponseEntity.ok("Conta Atual não encontrada!");
			}
		}
		
	}

	@SneakyThrows
	public ResponseEntity<String> transferir(ContaDTO dadosTransferencia) {
		return transfere(dadosTransferencia.getNumeroContaOrigem(), dadosTransferencia.getNumeroContaDestino(), dadosTransferencia.getValorParaTransferir(), null, false, false, null);				
	}
	
	private ResponseEntity<String> salvaTransferenciaFutura(double valorParaTransferir, Integer quantidadeParcela, boolean isParcelado,
			Conta contaOrigemTransferencia) {
		double valorParcela = (valorParaTransferir/quantidadeParcela);
		for (int numeroParcela = 0; numeroParcela < quantidadeParcela; numeroParcela++) {
			transferenciaRepository.save(
					Transferencia
					.builder()
					.valorParcela(valorParcela)
					.isParcelado(isParcelado)
					.numeroParcela(numeroParcela+1)
					.quantidadeParcela(quantidadeParcela)
					.contaOrigem(contaOrigemTransferencia)
					.numeroContaOrigem(contaOrigemTransferencia.getNumeroConta())
					.numeroContaDestino(contaOrigemTransferencia.getNumeroConta())
					.build()
					);
		}
		return ResponseEntity.ok("Transferências agendadas!");
	}

	public Transferencia salvarTransferencia(Transferencia transferencia) {
		return transferenciaRepository.save(transferencia);
	}

	public ResponseEntity<String> estornarTransferencia(String comprovanteTransferencia) throws Exception {
		Transferencia transferenciaEstorno = transferenciaRepository.findByComprovanteTransferencia(comprovanteTransferencia);
		
		if(!transferenciaEstorno.isEstornado()) {
			return transfere(transferenciaEstorno.getNumeroContaDestino(), transferenciaEstorno.getNumeroContaOrigem(), transferenciaEstorno.getValorTransferido(), null, true, false, transferenciaEstorno);				
		} else {
			return ResponseEntity.ok("Estorno já foi realizado anteriormente!");							
		}
	}
	
	private String gerarComprovante() {
		UUID uuid = UUID.randomUUID();
		String comprovante = uuid.toString();
		
		return comprovante.substring(0,20);
	}

	public ResponseEntity<String> programarTransferencia(ContaDTO conta) throws Exception {
		return transfere(conta.getNumeroContaOrigem(), conta.getNumeroContaDestino(), conta.getValorParaTransferir(), conta.getQuantidadeParcela(), false, true, null);
		
	}

	
	public ResponseEntity<String> transferirValor(ContaDTO dadosTransferencia) throws Exception {
		return transfere(dadosTransferencia.getNumeroContaOrigem(), dadosTransferencia.getNumeroContaDestino(), dadosTransferencia.getValorParaTransferir(), null, false, false, null);				
	}

	public List<Transferencia> buscarTransferenciaFutura(String numeroContaOrigem) {
		return transferenciaRepository.buscarTransferenciaFutura(numeroContaOrigem);
	}
}
