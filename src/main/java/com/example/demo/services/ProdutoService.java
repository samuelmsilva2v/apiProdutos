package com.example.demo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
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
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProdutoResponseDto create(ProdutoRequestDto request) {
		
		var produto = modelMapper.map(request, Produto.class);
		produto.setId(UUID.randomUUID());
		
		produtoRepository.save(produto);
		
		var response = modelMapper.map(produto, ProdutoResponseDto.class);
		
		return response;
	}
	
	public ProdutoResponseDto update(UUID id, ProdutoRequestDto request) {
		
		var produto = produtoRepository.findById(id).get();
		produto.setNome(request.getNome());
		produto.setPreco(BigDecimal.valueOf(request.getPreco()));
		produto.setQuantidade(request.getQuantidade());
		
		produtoRepository.save(produto);
		
		var response = modelMapper.map(produto, ProdutoResponseDto.class);
		
		return response;
	}
	
	public ProdutoResponseDto delete(UUID id) {
		
		var produto = produtoRepository.findById(id).get();
		
		produtoRepository.delete(produto);
		
		var response = modelMapper.map(produto, ProdutoResponseDto.class);
		
		return response;
	}
	
	public List<ProdutoResponseDto> getAll() {
		
		var response = new ArrayList<ProdutoResponseDto>();
		
		for(var produto : produtoRepository.findAll()) {
			response.add(modelMapper.map(produto, ProdutoResponseDto.class));
		}
		
		return response;
	}
	
	public ProdutoResponseDto getById(UUID id) {
		
		var produto = produtoRepository.findById(id).get();
		
		var response = modelMapper.map(produto, ProdutoResponseDto.class);
		
		return response;
	}
	
	
}
