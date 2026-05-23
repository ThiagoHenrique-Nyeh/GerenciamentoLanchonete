package com.ThiagoHenrique.GerenciamentoLanchonete.controller;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Personalizacaoitem;
import com.ThiagoHenrique.GerenciamentoLanchonete.services.PersonalizacaoitemService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personalizacaoitem")

public class PersonalizacaoitemController {

    @Autowired
    PersonalizacaoitemService personalizacaoitemService;

    @PostMapping
    public ResponseEntity<Personalizacaoitem>salvar(@RequestBody Personalizacaoitem personalizacaoitem){
        return new ResponseEntity<Personalizacaoitem>(personalizacaoitemService.salvar(personalizacaoitem), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Personalizacaoitem>>listarTudo(){
        return new ResponseEntity<>(personalizacaoitemService.listarTudo(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Personalizacaoitem>buscaId(@PathVariable Long id){
        return new ResponseEntity<>(personalizacaoitemService.buscaId(id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        personalizacaoitemService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
