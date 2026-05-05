package com.ThiagoHenrique.GerenciamentoLanchonete.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Itempedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn (name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal valorunidade;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorunidade() {
        return valorunidade;
    }

    public void setValorunidade(BigDecimal valorunidade) {
        this.valorunidade = valorunidade;
    }
}
