package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ProdutoRequestDto;
import com.example.demo.dtos.ProdutoResponseDto;
import com.example.demo.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping
	public ProdutoResponseDto post(@RequestBody ProdutoRequestDto request) {
		return produtoService.create(request);
	}
}
