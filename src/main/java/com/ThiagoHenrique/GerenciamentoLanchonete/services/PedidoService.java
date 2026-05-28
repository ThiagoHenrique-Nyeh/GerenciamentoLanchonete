package com.ThiagoHenrique.GerenciamentoLanchonete.services;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Pedido;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvar(Pedido pedido){
        if( pedido.getValorTotal()== null){
            pedido.setValorTotal(BigDecimal.ZERO); // para comecar com zero
        }

         if (pedido.getStatusEntrega()==null){
            pedido.setStatusEntrega("EM PREPARO");
        }
      pedido.setData_hora_pedido(LocalDateTime.now()); //<-Para salvar a data e hora automaticamente

        return pedidoRepository.save(pedido);
    }



    public List<Pedido> listarTudo(){
        return pedidoRepository.findAll();
    }



    public Pedido buscaId(Long id){
        return pedidoRepository.findById(id)
           .orElseThrow(() ->  new RuntimeException("Pedido com o id" + id + "NAO foi encontrado :("));
    }



    public Pedido iniciarEntrega(Long id){
        Pedido pedido=buscaId(id);

        if ("RETIRADA".equalsIgnoreCase(pedido.getTipoEntrega())) {
            throw new RuntimeException("PEDIDO SERA RETIRADO NO LOCAL, NAO É POSSIVEL SAIR PARA ENTREGA");
        }
        if (!"EM PREPARO".equalsIgnoreCase(pedido.getStatusEntrega())){
            throw new RuntimeException("PEDIDO PRECISA ESTAR EM PREPARO PARA SAIR PARA A ENTREGA");
        }
        pedido.setStatusEntrega("SAIU PARA ENTREGA");
        return pedidoRepository.save(pedido);
    }



    public Pedido concluirPedido(Long id){
        Pedido pedido = buscaId(id);

        if (!"SAIU PARA ENTREGA".equalsIgnoreCase(pedido.getStatusEntrega()) && !"EM PREPARO".equalsIgnoreCase(pedido.getStatusEntrega())){
            throw new RuntimeException("PEDIDO PRECISA ESTAR EM PREPARO OU TER SAIDO PARA ENTREGA PARA SER CONCLUIDO");
        }
        pedido.setStatusEntrega("CONCLUIDO");
        return pedidoRepository.save(pedido);
    }



    public void deletar (Long id){
        Pedido pedido = buscaId(id);
        pedidoRepository.delete(pedido);
    }
}
