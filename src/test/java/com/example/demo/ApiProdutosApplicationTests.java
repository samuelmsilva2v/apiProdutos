package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

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
import com.fasterxml.jackson.core.type.TypeReference;
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

	private static UUID id;

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

		// checking if the response data match those of the request
		assertNotNull(response.getId()); // checking if an ID was generated for the product
		assertEquals(response.getNome(), request.getNome()); // comparing the name
		assertEquals(response.getPreco(), request.getPreco()); // comparing the price
		assertEquals(response.getQuantidade(), request.getQuantidade()); // comparing the quantity

		id = response.getId();
	}

	@Test
	@Order(2)
	void atualizarProdutoTest() throws Exception {

		var faker = new Faker();

		var request = new ProdutoRequestDto();
		request.setNome(faker.commerce().productName());
		request.setPreco((double) faker.number().numberBetween(100, 5000));
		request.setQuantidade(faker.number().numberBetween(10, 500));

		var result = mockMvc.perform(put("/api/produtos/" + id).contentType("application/json")
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, ProdutoResponseDto.class);

		assertEquals(response.getId(), id);
		assertEquals(response.getNome(), request.getNome());
		assertEquals(response.getPreco(), request.getPreco());
		assertEquals(response.getQuantidade(), request.getQuantidade());
	}

	@Test
	@Order(3)
	void consultarProdutosTest() throws Exception {

		var result = mockMvc.perform(get("/api/produtos")).andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, new TypeReference<List<ProdutoResponseDto>>() {
		});

		response.stream().filter(produto -> produto.getId().equals(id)).findFirst()
				.orElseThrow(() -> new AssertionError("Produto com ID" + id + " não encontrado na lista"));
	}

	@Test
	@Order(4)
	void obterProdutoPorIdTest() throws Exception {
		
		var result = mockMvc.perform(get("/api/produtos/" + id)).andExpect(status().isOk()).andReturn();
		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		var response = objectMapper.readValue(content, ProdutoResponseDto.class);
		
		assertEquals(response.getId(), id);
		assertNotNull(response.getNome());
		assertNotNull(response.getPreco());
		assertNotNull(response.getQuantidade());
	}

	@Test
	@Order(5)
	void excluirProdutoTest() throws Exception {
		
		var result = mockMvc.perform(delete("/api/produtos/" + id)).andExpect(status().isOk()).andReturn();
		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		var response = objectMapper.readValue(content, ProdutoResponseDto.class);
		
		assertEquals(response.getId(), id);
		assertNotNull(response.getNome());
		assertNotNull(response.getPreco());
		assertNotNull(response.getQuantidade());
	}

}
