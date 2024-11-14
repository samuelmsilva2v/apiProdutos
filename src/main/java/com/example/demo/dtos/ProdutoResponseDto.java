package com.example.demo.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ProdutoResponseDto {

	private UUID id;
	private String nome;
	private Double preco;
	private Integer quantidade;
}
