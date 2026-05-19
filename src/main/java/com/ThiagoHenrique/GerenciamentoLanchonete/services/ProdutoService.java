package com.ThiagoHenrique.GerenciamentoLanchonete.services;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Produto;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.ProdutoRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new RuntimeException("NOME DO PRODUTO NAO PODE SER VAZIO");
        }
        if (produto.getDescricao() == null || produto.getDescricao().trim().isEmpty()) {
            throw new RuntimeException("DESCRICAO DO PRODUTO NAO PODE SER VAZIO");
        }
        if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("PRECO DO PRODUTO NAO PODE SER ZERO OU VAZIO");
        }
        return produtoRepository.save(produto);
    }


    public List<Produto> listarTudo(){
        return produtoRepository.findAll();
    }


    public Produto buscaId(Long Id){
        return produtoRepository.findById(Id)
                .orElseThrow(()-> new RuntimeException("PRODUTO DE ID:"+Id+ "NAO FOI ENCONTRADO :( "));
    }

    public void deletar(Long Id){
    Produto produto = buscaId(Id);
    produtoRepository.delete(produto);
    }
}