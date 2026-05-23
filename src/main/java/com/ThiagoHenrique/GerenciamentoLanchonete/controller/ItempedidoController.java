package com.ThiagoHenrique.GerenciamentoLanchonete.controller;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Itempedido;
import com.ThiagoHenrique.GerenciamentoLanchonete.services.ItempedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itempedido")

public class ItempedidoController {

    @Autowired
    ItempedidoService itempedidoService;

    @PostMapping
    public ResponseEntity<Itempedido>salvar(@RequestBody Itempedido itempedido){
        return new ResponseEntity<Itempedido>(itempedidoService.salvar(itempedido),HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Itempedido>>listarTudo(){
        return new ResponseEntity<>(itempedidoService.listarTudo(),HttpStatus.OK);
    }



    @GetMapping("{id}")
    public ResponseEntity<Itempedido>buscaId(@PathVariable Long id){
        return new ResponseEntity<>(itempedidoService.buscaId(id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        itempedidoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
