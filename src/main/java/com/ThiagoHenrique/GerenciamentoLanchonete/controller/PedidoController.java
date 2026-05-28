package com.ThiagoHenrique.GerenciamentoLanchonete.controller;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Pedido;
import com.ThiagoHenrique.GerenciamentoLanchonete.services.PedidoService;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")

public class PedidoController {

    @Autowired
    PedidoService pedidoService;


    @PostMapping
    public ResponseEntity<Pedido>salvar(@RequestBody Pedido pedido){
        return new ResponseEntity<Pedido>(pedidoService.salvar(pedido), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>>listarTudo(){
        return new ResponseEntity<>(pedidoService.listarTudo(),HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<Pedido>buscaId(@PathVariable Long id){
        return new ResponseEntity<>(pedidoService.buscaId(id),HttpStatus.OK);
    }



    @PutMapping("/{id}/iniciarEntrega")
    public ResponseEntity<Pedido>iniciarEntrega(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.iniciarEntrega(id));
    }


    @PutMapping("/{id}/concluir")
    public ResponseEntity<Pedido>concluirPedido(@PathVariable Long id){
       return ResponseEntity.ok(pedidoService.concluirPedido(id));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        pedidoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

