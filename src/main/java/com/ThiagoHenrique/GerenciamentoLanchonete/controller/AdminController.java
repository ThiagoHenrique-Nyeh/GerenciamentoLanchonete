package com.ThiagoHenrique.GerenciamentoLanchonete.controller;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Admin;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/cadastro")
    public ResponseEntity<Admin> cadastrar(
            @RequestBody Admin admin){

        return ResponseEntity.ok(
                adminRepository.save(admin)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Admin admin){

        Admin adminBanco =
                adminRepository.findByUsuario(
                        admin.getUsuario()
                );

        if(adminBanco == null){

            return ResponseEntity
                    .badRequest()
                    .body("Usuário inválido :(");
        }

        if(adminBanco.getSenha()
                .equals(admin.getSenha())){

            return ResponseEntity
                    .ok("Login realizado :D");
        }

        return ResponseEntity
                .badRequest()
                .body("Senha inválida :(");
    }
}