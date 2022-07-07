package br.ce.fortaleza.hakney.sistemabancario.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Conta {

	public Conta() {	
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String agencia;
	private String numeroConta;
	private double saldo;
	
	@OneToMany(mappedBy = "contaOrigem", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Transferencia> transferencia;
	
	public boolean sacarValor(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }
	
    public void depositarValor(double valor) {
        this.saldo += valor;
    }
    
    
	public Integer getId() {
		return id;
	}

	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public double getSaldo() {
		return saldo;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	
	
}
