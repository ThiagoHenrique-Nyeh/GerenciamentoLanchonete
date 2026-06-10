package com.ThiagoHenrique.GerenciamentoLanchonete.services;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Itempedido;
import com.ThiagoHenrique.GerenciamentoLanchonete.model.Pedido;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.ItempedidoRepository;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.PedidoRepository;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItempedidoService {


    @Autowired
    private ItempedidoRepository  itempedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;


    public Itempedido salvar(Itempedido itempedido){

        if(itempedido.getQuantidade() == null || itempedido.getQuantidade()<=0 ){
            throw new RuntimeException("QUANTIDADE NAO PODE SER MENOR QUE 0");
        }
        if(itempedido.getProduto() == null || itempedido.getProduto().getId()== null) {
            throw new RuntimeException("ITEM PRECISA ESTAR VINCULADO COM ALGUM PRODUTO");
        }

        itempedido.setValorunidade(produtoRepository.findById(itempedido.getProduto().getId()).get().getPreco()); //VERIFICACAO DO PRECO UNITARIO DP PRODUTO

        if(itempedido.getValorunidade() == null){
            throw new RuntimeException("VALOR DA UNIDADE DO PRODUTO NAO PODE SER 0");
        }

        Pedido pedido = pedidoRepository.findById(itempedido.getPedido().getId())
                .orElseThrow(()-> new RuntimeException("PEDIDO NAO ENCONTRADO"));

        pedido.setValorTotal(pedido.getValorTotal().add(
                itempedido.getValorunidade().multiply(new BigDecimal(itempedido.getQuantidade())))
        );
        pedidoRepository.save(pedido);

        return itempedidoRepository.save(itempedido);
    }


    public List<Itempedido> listarTudo(){
        return itempedidoRepository.findAll();
    }



    public Itempedido buscaId(Long id){
        return itempedidoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("ITEM_PEDIDO DE ID:"+id+ "NAO FOI ENCONTRADO :( "));
    }



    public void deletar(Long id) {
        Itempedido itempedido = buscaId(id);
        itempedidoRepository.delete(itempedido);
    }

}