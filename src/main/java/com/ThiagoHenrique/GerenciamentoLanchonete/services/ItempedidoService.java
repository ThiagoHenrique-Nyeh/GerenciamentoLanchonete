package com.ThiagoHenrique.GerenciamentoLanchonete.services;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Itempedido;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.ItempedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItempedidoService {


    @Autowired
    private ItempedidoRepository  itempedidoRepository;

    public Itempedido salvar(Itempedido itempedido){
        if(itempedido.getQuantidade() == null || itempedido.getQuantidade()<=0 ){
            throw new RuntimeException("QUANTIDADE NAO PODE SER MENOR QUE 0");
        }
            if(itempedido.getProduto() != null){
              itempedido.setValorunidade(itempedido.getProduto().getPreco());
            }
        return itempedidoRepository.save(itempedido);
    }




    public List<Itempedido> listarTudo(){
        return itempedidoRepository.findAll();
    }




    public Itempedido buscaID(Long id){
        return itempedidoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("ITEM_PEDIDO DE ID:"+id+ "NAO FOI ENCONTRADO :( "));
    }




    public void deletar(Long id) {
        Itempedido itempedido = buscaID(id);
        itempedidoRepository.delete(itempedido);
    }

}