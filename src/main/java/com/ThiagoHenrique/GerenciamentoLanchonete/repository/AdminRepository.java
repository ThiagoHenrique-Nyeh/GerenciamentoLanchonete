package com.ThiagoHenrique.GerenciamentoLanchonete.repository;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository
        extends JpaRepository<Admin, Long> {

    Admin findByUsuario(String usuario);

}