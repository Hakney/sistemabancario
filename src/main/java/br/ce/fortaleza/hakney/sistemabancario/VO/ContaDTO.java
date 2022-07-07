package br.ce.fortaleza.hakney.sistemabancario.VO;

import lombok.Data;

@Data
public class ContaDTO {

	private String numeroContaOrigem;
	private String numeroContaDestino;

	private double valorParaTransferir;
	private double valorDeposito;
	
	private Integer quantidadeParcela;
	
	
}
