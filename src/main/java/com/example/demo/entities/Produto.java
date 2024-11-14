package com.example.demo.entities;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_produto")
@Data
public class Produto {

	@Id
	@Column()
	private UUID id;
	
	@Column(length = 150, nullable = false)
	private String nome;
	
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal preco;
	
	@Column(nullable = false)
	private Integer quantidade;
}
