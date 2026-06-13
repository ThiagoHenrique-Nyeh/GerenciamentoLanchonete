package com.ThiagoHenrique.GerenciamentoLanchonete.services;

import com.ThiagoHenrique.GerenciamentoLanchonete.model.Itempedido;
import com.ThiagoHenrique.GerenciamentoLanchonete.model.Pedido;
import com.ThiagoHenrique.GerenciamentoLanchonete.model.Cliente;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.PedidoRepository;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.ProdutoRepository;
import com.ThiagoHenrique.GerenciamentoLanchonete.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Pedido salvar(Pedido pedido){
        if (pedido.getStatusEntrega() == null){
            pedido.setStatusEntrega("PENDENTE");
        }

        pedido.setData_hora_pedido(LocalDateTime.now());

        if (pedido.getCliente() != null) {
            Cliente clienteSalvo = clienteRepository.save(pedido.getCliente());
            pedido.setCliente(clienteSalvo);
        } else {
            throw new RuntimeException("Não é possível salvar um pedido sem um cliente vinculado.");
        }

        pedido.setValorTotal(BigDecimal.ZERO);
        if (pedido.getItempedido() != null){
            for(Itempedido itempedido : pedido.getItempedido()){
                itempedido.setPedido(pedido);

                BigDecimal precoDoBanco = produtoRepository.findById(itempedido.getProduto().getId())
                        .orElseThrow(()-> new RuntimeException("PRODUTO NAO ENCONTRADO :(")).getPreco();

                if (itempedido.getValorunidade() == null || itempedido.getValorunidade().compareTo(precoDoBanco) < 0) {
                    itempedido.setValorunidade(precoDoBanco);
                }

                pedido.setValorTotal(pedido.getValorTotal()
                        .add(itempedido.getValorunidade()
                                .multiply(BigDecimal.valueOf(itempedido.getQuantidade()))));
            }
        }

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTudo(){
        return pedidoRepository.findAll();
    }

    public Pedido buscaId(Long id){
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido com o id " + id + " NÃO foi encontrado :("));
    }

    public Pedido iniciarEntrega(Long id){
        Pedido pedido = buscaId(id);

        if ("RETIRADA".equalsIgnoreCase(pedido.getTipoEntrega())) {
            throw new RuntimeException("PEDIDO SERÁ RETIRADO NO LOCAL, NÃO É POSSÍVEL SAIR PARA ENTREGA");
        }
        if (!"PENDENTE".equalsIgnoreCase(pedido.getStatusEntrega()) && !"EM PREPARO".equalsIgnoreCase(pedido.getStatusEntrega())){
            throw new RuntimeException("PEDIDO PRECISA ESTAR PENDENTE OU EM PREPARO PARA SAIR PARA A ENTREGA");
        }
        pedido.setStatusEntrega("SAIU PARA ENTREGA");
        return pedidoRepository.save(pedido);
    }

    public Pedido concluirPedido(Long id){
        Pedido pedido = buscaId(id);

        if (!"SAIU PARA ENTREGA".equalsIgnoreCase(pedido.getStatusEntrega()) &&
                !"EM PREPARO".equalsIgnoreCase(pedido.getStatusEntrega()) &&
                !"PENDENTE".equalsIgnoreCase(pedido.getStatusEntrega())){
            throw new RuntimeException("PEDIDO INVÁLIDO PARA CONCLUSÃO");
        }
        pedido.setStatusEntrega("CONCLUIDO");
        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id){
        Pedido pedido = buscaId(id);
        pedidoRepository.delete(pedido);
    }
}