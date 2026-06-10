    package com.ThiagoHenrique.GerenciamentoLanchonete.services;

    import com.ThiagoHenrique.GerenciamentoLanchonete.model.Pedido;
    import com.ThiagoHenrique.GerenciamentoLanchonete.model.Personalizacaoitem;
    import com.ThiagoHenrique.GerenciamentoLanchonete.repository.ItempedidoRepository;
    import com.ThiagoHenrique.GerenciamentoLanchonete.repository.PedidoRepository;
    import com.ThiagoHenrique.GerenciamentoLanchonete.repository.PersonalizacaoitemRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.math.BigDecimal;
    import java.util.List;

    @Service
    public class PersonalizacaoitemService {

        @Autowired
        private PersonalizacaoitemRepository personalizacaoitemRepository;

        @Autowired
        private PedidoRepository pedidoRepository;

        @Autowired
        private ItempedidoRepository itempedidoRepository;

        public Personalizacaoitem salvar(Personalizacaoitem personalizacaoitem){
            if(personalizacaoitem.getItempedido() == null || personalizacaoitem.getItempedido().getId() == null ){
                throw new RuntimeException("PERSONALIZACAO DE PEDIDO PRECISA ESTAR VINCULADO COM ALGUM PEDIDO");
            }
            if (personalizacaoitem.getDetalhe() == null || personalizacaoitem.getDetalhe().trim().isEmpty()){
                throw new RuntimeException("O ITEM DE PERSONALIZACAO DO PEDIDO É OBRIGATÓRIO");
            }
            if (personalizacaoitem.getQuantidade() == null || personalizacaoitem.getQuantidade()<=0){
                throw new RuntimeException("QUANTIDADE SER MAIOR QUE ZERO");
            }
            if (personalizacaoitem.getPreco_adicional()== null){
                personalizacaoitem.setPreco_adicional(BigDecimal.ZERO);
            }

            personalizacaoitem.setValorTotalAdicional(personalizacaoitem.getPreco_adicional()
                    .multiply(new BigDecimal(personalizacaoitem.getQuantidade())));

            Pedido pedido = pedidoRepository.findById(
                    itempedidoRepository.findById(personalizacaoitem.getItempedido().getId()).get().getPedido().getId()
            ).orElseThrow(() -> new RuntimeException("PEDIDO NAO ENCONTRADO"));

            pedido.setValorTotal(pedido.getValorTotal().add(personalizacaoitem.getValorTotalAdicional()));

            pedidoRepository.save(pedido);

            return personalizacaoitemRepository.save(personalizacaoitem);
        }


        public List<Personalizacaoitem> listarTudo(){
            return personalizacaoitemRepository.findAll();
        }



        public Personalizacaoitem buscaId(Long id){
            return personalizacaoitemRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("PERSONALIZACAO DE ID:" + id + "NAO ENCONTRADA"));
        }

        public void deletar(Long id){
            Personalizacaoitem personalizacaoitem = buscaId(id);
            personalizacaoitemRepository.delete(personalizacaoitem);
        }
    }
