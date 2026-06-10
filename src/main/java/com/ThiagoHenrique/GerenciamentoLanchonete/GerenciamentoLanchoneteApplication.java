package com.ThiagoHenrique.GerenciamentoLanchonete;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Cliente;
import com.ThiagoHenrique.GerenciamentoLanchonete.model.Produto;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.ClienteRepository;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class GerenciamentoLanchoneteApplication {

	public static void main(String[] args) {

		SpringApplication.run(GerenciamentoLanchoneteApplication.class, args);
	}

}
