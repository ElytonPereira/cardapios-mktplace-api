package br.com.senai.cardapiosmktplaceapi.dto;

import java.util.ArrayList;

import br.com.senai.cardapiosmktplaceapi.entity.Restaurante;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.AssertFalse.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NovoCardapio {
	
	@Size(max = 100, message = "O nome do cardapio não deve conter mais de 100 caracteries")
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "A descrição é obrigatória")
	private String descricao;
	
	@NotNull(message = "O restaurante é obrigatório")
	private Restaurante restaurante;
	
	
	@Size(min = 1, message = "O cardapio deve possuir ao menos 1 opção")
	private java.util.List<NovaOpcaoDoCardapio> opcoes;
	
	public NovoCardapio() {
		this.opcoes = new ArrayList<>();
	}
	
	
}
