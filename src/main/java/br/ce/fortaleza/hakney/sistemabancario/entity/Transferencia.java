package br.ce.fortaleza.hakney.sistemabancario.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Transferencia {
	
	public Transferencia() {	
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn
	@JsonBackReference
	private Conta contaOrigem;
	
	private double valorTransferido;
	private String numeroContaOrigem;
	private String numeroContaDestino;
	private boolean isParcelado;
	private Integer quantidadeParcela;
	private boolean isEstornado;
	private boolean isEfetivada;
	private String comprovanteTransferencia;
	private int numeroParcela;
	private double valorParcela;

}
