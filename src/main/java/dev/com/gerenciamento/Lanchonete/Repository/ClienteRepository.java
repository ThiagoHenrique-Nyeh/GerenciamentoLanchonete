package dev.com.gerenciamento.Lanchonete.Repository;

import dev.com.gerenciamento.Lanchonete.Model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Integer>{

}

//{LEMBRETE!!!!!}
//O REPOSITORIO SEMPRE SERA VAZIO E SEMPRE SERA DO TIPO ``INTERFACE``