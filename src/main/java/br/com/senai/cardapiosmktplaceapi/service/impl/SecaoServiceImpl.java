package br.com.senai.cardapiosmktplaceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.cardapiosmktplaceapi.entity.Restaurante;
import br.com.senai.cardapiosmktplaceapi.entity.Secao;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import br.com.senai.cardapiosmktplaceapi.repository.OpcoesDeCardapioRepository;
import br.com.senai.cardapiosmktplaceapi.repository.SecoesRepository;
import br.com.senai.cardapiosmktplaceapi.service.SecaoService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Service
public class SecaoServiceImpl implements SecaoService {
	
	@Autowired
	private SecoesRepository repository;
	
	private OpcoesDeCardapioRepository opcoesDeCardapioRepository;
	
	@Override
	public Secao salvar(Secao secao) {
		Secao outraSecao = repository.buscarPor(secao.getNome());
		if (outraSecao != null) {
			if (outraSecao.isPersistida()) {
				Preconditions.checkArgument(outraSecao.equals(secao), "O nome da secao ja esta em uso");
			}
		}
		
		Secao secaoSalva = repository.save(secao);
		
		return secaoSalva;
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		Secao secaoEncontrada = repository.buscarPor(id);
		Preconditions.checkNotNull(secaoEncontrada, "O id não esta vinculado a uma seção");
		Preconditions.checkArgument(secaoEncontrada.getStatus() != status, "O status ja esta salvo para a seção");
		
		this.repository.atualizarStatusPor(id, status);

	}

	@Override
	public Page<Secao> listarPor(String nome, Pageable paginacao) {
		
		return repository.listarPor( nome + "%", paginacao);
	}

	@Override
	public Secao buscarPor(Integer id) {
		Secao secaoEncontrado = repository.buscarPor(id);
		Preconditions.checkNotNull(secaoEncontrado, "Não foi encontrado seção para o id informado");
		Preconditions.checkArgument(secaoEncontrado.isAtiva(), "A secao esta inativa");
		
		return secaoEncontrado;	
		
	}

	@Override
	public Secao excluirPor(Integer id) {
		Secao secaoParaExclusao = repository.buscarPor(id);
		Long qtdOpcoesDeCardapio = opcoesDeCardapioRepository.contarPor(id);
		Preconditions.checkNotNull(secaoParaExclusao, "Não foi encontrado secão para o id informado");
		Preconditions.checkArgument(qtdOpcoesDeCardapio == 0, "Existem opções de cardapios vinculados a essa seção");
		this.repository.deleteById(id);
		return secaoParaExclusao;
	}

}
