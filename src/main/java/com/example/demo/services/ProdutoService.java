package com.example.demo.services;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ProdutoRequestDto;
import com.example.demo.dtos.ProdutoResponseDto;
import com.example.demo.entities.Produto;
import com.example.demo.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public ProdutoResponseDto create(ProdutoRequestDto request) {
		
		var produto = new Produto();
		produto.setId(UUID.randomUUID());
		produto.setNome(request.getNome());
		produto.setPreco(BigDecimal.valueOf(request.getPreco()));
		produto.setQuantidade(request.getQuantidade());
		
		produtoRepository.save(produto);
		
		var response = new ProdutoResponseDto();
		response.setId(produto.getId());
		response.setNome(produto.getNome());
		response.setPreco(produto.getPreco().doubleValue());
		response.setQuantidade(produto.getQuantidade());
		
		
		return response;
	}
}
