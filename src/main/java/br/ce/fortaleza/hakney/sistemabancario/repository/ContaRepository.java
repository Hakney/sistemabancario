package br.ce.fortaleza.hakney.sistemabancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import br.ce.fortaleza.hakney.sistemabancario.VO.ContaDTO;
import br.ce.fortaleza.hakney.sistemabancario.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{

	@Query("Select c.saldo from Conta c where c.numeroConta = :numeroConta")
	ResponseEntity<String> consultarSaldo(@PathVariable String numeroConta);

	Conta findByNumeroConta(String numeroConta);

}
