package com.example.demo.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProdutoRequestDto {

	@NotBlank(message = "Por favor, informe o nome do produto.")
	@Length(min = 8, message = "Nome do produto inválido. Informe um nome com, pelo menos, 8 caracteres.")
	private String nome;
	
	@NotNull(message = "Por favor, informe o preço do produto.")
	@Positive(message = "Infome um preço válido.")
	private Double preco;
	
	@NotNull(message = "Por favor, informe a quantidade do produto.")
	@Min(value = 1, message = "Informe um número válido.")
	private Integer quantidade;
}
