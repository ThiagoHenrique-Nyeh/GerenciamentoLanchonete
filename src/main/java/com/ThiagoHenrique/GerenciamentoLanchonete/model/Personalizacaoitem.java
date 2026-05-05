package com.ThiagoHenrique.GerenciamentoLanchonete.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Personalizacaoitem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "itempedido_id", nullable = false)
    private Itempedido itempedido;

    private String acao;

    private String detalhe;

    private BigDecimal preco_adicional;




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

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
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
}
