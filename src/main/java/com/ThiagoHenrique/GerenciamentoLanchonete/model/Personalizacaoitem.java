package com.ThiagoHenrique.GerenciamentoLanchonete.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Personalizacaoitem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "itempedido_id", nullable = false)
    private Itempedido itempedido;

    private Integer quantidade;

    private String detalhe;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco_adicional;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotalAdicional;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Itempedido getItempedido() {
        return itempedido;
    }

    public void setItempedido(Itempedido itempedido) {
        this.itempedido = itempedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public BigDecimal getPreco_adicional() {
        return preco_adicional;
    }

    public void setPreco_adicional(BigDecimal preco_adicional) {
        this.preco_adicional = preco_adicional;
    }

    public BigDecimal getValorTotalAdicional() {
        return valorTotalAdicional;
    }

    public void setValorTotalAdicional(BigDecimal valorTotalAdicional) {
        this.valorTotalAdicional = valorTotalAdicional;
    }
}
