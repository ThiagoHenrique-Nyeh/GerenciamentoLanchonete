package com.ThiagoHenrique.GerenciamentoLanchonete.services;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Pagamento;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.PagamentoRepository;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;


    public Pagamento salvar (Pagamento pagamento){
        if(pagamento.getPedido() == null || pagamento.getPedido().getId() == null){
            throw new RuntimeException("PAGAMENTO PRECISA ESTAR INTERLIGADO COM ALGUM PEDIDO");
        }
        if(pagamento.getTipoPagamento() == null || pagamento.getTipoPagamento().trim().isEmpty()){
            throw new RuntimeException("TIPO DO PAGAMENTO É OBRIGÁTORIO");
        }

        if (pagamento.getStatus() == null || pagamento.getStatus().trim().isEmpty()) {
            pagamento.setStatus("PENDENTE");
        }

        pagamento.setPedido(pedidoRepository.findById(pagamento.getPedido().getId()) //faz uma busca por id para encontrar o pedido
                .orElseThrow(()-> new RuntimeException("PEDIDO NAO FOI ENCONTRADO NO BANCO DE DADOS :(")));
        pagamento.setDataHoraPedido(pagamento.getPedido().getData_hora_pedido());


        if (pagamento.getPedido().getValorTotal() == null){
            throw new RuntimeException("PEDIDO NAO POSSUI UM VALOR TOTAL NO MOMENTO");
        }

        if (pagamento.getTipoPagamento().equalsIgnoreCase("DINHEIRO")) {

            if(pagamento.getTotalpagar_bruto() == null ||
            pagamento.getTotalpagar_bruto().compareTo(pagamento.getPedido().getValorTotal()) < 0){
                throw new RuntimeException("VALOR INSUFICIENTE");
            }
            pagamento.setTroco(pagamento.getTotalpagar_bruto().subtract(pagamento.getPedido().getValorTotal()));
        } else {
            pagamento.setTroco(BigDecimal.ZERO);
            pagamento.setTotalpagar_bruto(pagamento.getPedido().getValorTotal());
        }
        return pagamentoRepository.save(pagamento);
    }



    public List<Pagamento> listarTudo(){
        return pagamentoRepository.findAll();
    }



    public Pagamento buscaId(Long id){
        return pagamentoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("PAGAMENTO DE ID:" + id + "NAO FOI ENCONTRADO :("));
    }



    public void deletar (Long id){
        Pagamento pagamento = buscaId(id);
        pagamentoRepository.delete(pagamento);
    }
}
