package com.ThiagoHenrique.GerenciamentoLanchonete.repository;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Pagamento;
import com.ThiagoHenrique.GerenciamentoLanchonete.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {
}
