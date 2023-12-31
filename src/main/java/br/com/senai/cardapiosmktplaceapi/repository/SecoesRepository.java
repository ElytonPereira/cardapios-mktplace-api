package br.com.senai.cardapiosmktplaceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.cardapiosmktplaceapi.entity.Secao;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;

@Repository
public interface SecoesRepository extends JpaRepository<Secao, Integer>{

	@Query(value = "SELECT s "
			+ "FROM Secao s "
			+ "WHERE Upper(s.nome) Like Upper(:nome) "
			+ "ORDER BY s.nome", countQuery = "SELECT Count(s) "
					+ "FROM Secao s "
					+ "WHERE Upper(s.nome) Like Upper(:nome) ")
	public Page<Secao> listarPor(String nome, Pageable paginacao);
	
	@Query(value = "SELECT s "
			+ "FROM Secao s "
			+ "WHERE s.id = :id")
	public Secao buscarPor(Integer id);
	
	@Query(value = "SELECT s "
			+ "FROM Secao s "
			+ "WHERE Upper(s.nome) = Upper(:nome)")
	public Secao buscarPor(String nome);
	
	@Modifying
	@Query(value = "UPDATE Secao s SET s.status = :status "
			+ "WHERE s.id = :id ")
	public void atualizarStatusPor(Integer id, Status status);
	
}
