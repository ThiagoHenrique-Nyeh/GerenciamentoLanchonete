package com.ThiagoHenrique.GerenciamentoLanchonete.controller;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Pagamento;
import com.ThiagoHenrique.GerenciamentoLanchonete.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.PageFormat;
import java.util.List;

@RestController
@RequestMapping("/pagamento")
@CrossOrigin(origins = "http://localhost:5173")

public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<Pagamento>salvar(@RequestBody Pagamento pagamento){
        return new ResponseEntity<Pagamento>(pagamentoService.salvar(pagamento), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Pagamento>>listarTudo(){
        return new ResponseEntity<>(pagamentoService.listarTudo(),HttpStatus.OK);
    }



    @GetMapping("{id}")
    public ResponseEntity<Pagamento>buscaId(@PathVariable Long id){
        return new ResponseEntity<>(pagamentoService.buscaId(id),HttpStatus.OK);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        pagamentoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
