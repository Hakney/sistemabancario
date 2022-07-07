package br.ce.fortaleza.hakney.sistemabancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ce.fortaleza.hakney.sistemabancario.entity.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer>{

	Transferencia findByComprovanteTransferencia(String comprovanteTransferencia);

	@Query("select t from Transferencia t where t.numeroContaOrigem = :numeroContaOrigem and t.isEfetivada = false")
	List<Transferencia> buscarTransferenciaFutura(String numeroContaOrigem);
	
}
