package com.ThiagoHenrique.GerenciamentoLanchonete.controller;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Produto;
import com.ThiagoHenrique.GerenciamentoLanchonete.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")

public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto>salvar(@RequestBody Produto produto){
        return new ResponseEntity<>(produtoService.salvar(produto), HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<List<Produto>>listarTudo(){
        return new ResponseEntity<>(produtoService.listarTudo(),HttpStatus.OK);
    }



    @GetMapping("{id}")
    public ResponseEntity<Produto>buscaId(@PathVariable Long id){
        return new ResponseEntity<>(produtoService.buscaId(id),HttpStatus.OK);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        produtoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
