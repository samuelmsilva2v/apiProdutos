package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dtos.ProdutoRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiProdutosApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	void cadastrarProdutoTest() throws Exception {

		var request = new ProdutoRequestDto();
		request.setNome("Produto Nome");
		request.setPreco(1000.0);
		request.setQuantidade(10);

		var result = mockMvc.perform(
				post("/api/produtos").contentType("application/json").content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn();
		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		assertTrue(content.contains("Produto cadastrado com sucesso!"));
	}

	@Test
	@Order(2)
	void atualizarProdutoTest() throws Exception {
		fail("Não implementado.");
	}

	@Test
	@Order(3)
	void excluirProdutoTest() throws Exception {
		fail("Não implementado.");
	}

	@Test
	@Order(4)
	void consultarProdutosTest() throws Exception {
		fail("Não implementado.");
	}

	@Test
	@Order(5)
	void obterProdutoPorIdTest() throws Exception {
		fail("Não implementado.");
	}

}
