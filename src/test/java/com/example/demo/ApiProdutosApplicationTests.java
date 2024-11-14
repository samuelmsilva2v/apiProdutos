package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dtos.ProdutoRequestDto;
import com.example.demo.dtos.ProdutoResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

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
		
		var faker = new Faker(Locale.forLanguageTag("pt-br"));	

		var request = new ProdutoRequestDto();
		request.setNome(faker.commerce().productName());
		request.setPreco((double) faker.number().numberBetween(100, 5000));
		request.setQuantidade(faker.number().numberBetween(10, 500));

		var result = mockMvc.perform(
				post("/api/produtos").contentType("application/json").content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn();
		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		var response = objectMapper.readValue(content, ProdutoResponseDto.class);
		
		//checking if the response data match those of the request
		assertNotNull(response.getId()); //checking if an ID was generated for the product
		assertEquals(response.getNome(), request.getNome()); //comparing the name
		assertEquals(response.getPreco(), request.getPreco()); //comparing the price
		assertEquals(response.getQuantidade(), request.getQuantidade()); //comparing the quantity
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
