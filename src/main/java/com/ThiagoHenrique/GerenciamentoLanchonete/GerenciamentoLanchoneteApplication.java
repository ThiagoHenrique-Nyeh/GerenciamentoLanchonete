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

	@Bean
	public CommandLineRunner executarTeste(ClienteRepository clienteRepo,ProdutoRepository ProdutoRepo){

		return args -> {
		/*System.out.println("====== T-E-S-T-E ======");

			System.out.println("Teste de Cliente");
		Cliente cliente1 = new Cliente();
		cliente1.setNome("Thiago Henrique");
		cliente1.setEndereco("rua jose renato lopes medeiros 123");
		cliente1.setTelefone("18 40028922");
		clienteRepo.save(cliente1);

			System.out.println("Teste de Produto");
		Produto produto1 = new Produto();
		produto1.setNome("X-Bacon");
		produto1.setDescricao("Páo,hamburguer,bacon,catupiry,tomate,alface,ketchup,maionese");
		produto1.setPreco( new BigDecimal("25.00"));
		ProdutoRepo.save(produto1);

		System.out.println("Produto Salvo com Sucesso");
		System.out.println("====== T-E-S-T-E    F-I-N-A-L-I-Z-A-D-O ======");*/
		};
	}

}
