package com.ThiagoHenrique.GerenciamentoLanchonete.repository;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Itempedido;
import com.ThiagoHenrique.GerenciamentoLanchonete.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItempedidoRepository extends JpaRepository<Itempedido,Long> {

}
