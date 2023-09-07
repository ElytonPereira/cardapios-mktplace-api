package br.com.senai.cardapiosmktplaceapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OpcoesDeCardapioRepository {

	@Query(value = "SELECT Count(oc) "
			+ "FROM OpcaoDoCardapio oc "
			+ "WHERE oc.secao.id = :idDaOpcoesDeCardapio")
	public Long contarPor(Integer idDaOpcoesDeCardapio);
	
}
