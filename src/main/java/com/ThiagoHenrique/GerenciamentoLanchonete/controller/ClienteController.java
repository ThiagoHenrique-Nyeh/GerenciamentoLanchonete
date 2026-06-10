package com.ThiagoHenrique.GerenciamentoLanchonete.controller;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Cliente;
import com.ThiagoHenrique.GerenciamentoLanchonete.services.ClienteService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:5173")

public class ClienteController {

    @Autowired
    ClienteService clienteService;


    @PostMapping
    public ResponseEntity<Cliente>salvar(@RequestBody Cliente cliente){
    return new ResponseEntity<>(clienteService.salvar(cliente), HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<List<Cliente>>listarTudo(){
    return new ResponseEntity<>(clienteService.listarTudo(),HttpStatus.OK);
    }



    @GetMapping("{id}")
    public ResponseEntity<Cliente>buscaId(@PathVariable Long id){
        return new ResponseEntity<>(clienteService.buscaId(id),HttpStatus.OK);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        clienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
