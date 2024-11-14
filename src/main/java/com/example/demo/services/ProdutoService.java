package com.example.demo.services;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ProdutoRequestDto;
import com.example.demo.entities.Produto;
import com.example.demo.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public String create(ProdutoRequestDto dto) {
		
		var produto = new Produto();
		
		produto.setId(UUID.randomUUID());
		produto.setNome(dto.getNome());
		produto.setPreco(BigDecimal.valueOf(dto.getPreco()));
		produto.setQuantidade(dto.getQuantidade());
		
		produtoRepository.save(produto);
		
		return "Produto cadastrado com sucesso!";
	}
}
