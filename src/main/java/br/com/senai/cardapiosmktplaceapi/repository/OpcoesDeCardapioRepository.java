package br.com.senai.cardapiosmktplaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.cardapiosmktplaceapi.entity.Opcao;
import br.com.senai.cardapiosmktplaceapi.entity.OpcaoDoCardapio;

@Repository
public interface OpcoesDeCardapioRepository extends JpaRepository<OpcaoDoCardapio, Integer>{

	@Query(value = "SELECT Count(oc) "
			+ "FROM OpcaoDoCardapio oc "
			+ "WHERE oc.secao.id = :idDaSecaoDoCardapio")
	public Long contarPor(Integer idDaSecaoDoCardapio);
	
}
