package com.ThiagoHenrique.GerenciamentoLanchonete.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalpagar_bruto;

@OneToOne
@JoinColumn (name = "pedido_id", nullable = true )
    private Pedido pedido;

    @Column(nullable = false)
    private String tipoPagamento;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal troco;

    @Column(nullable = false)
    private boolean pagamentoEntrega;

    @Column(nullable = false)
    private boolean pagamentoLocal;

    @Column(nullable = false)
    private LocalDateTime dataHoraPedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalpagar_bruto() {
        return totalpagar_bruto;
    }

    public void setTotalpagar_bruto(BigDecimal totalpagar_bruto) {
        this.totalpagar_bruto = totalpagar_bruto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTroco() {
        return troco;
    }

    public void setTroco(BigDecimal troco) {
        this.troco = troco;
    }

    public boolean isPagamentoEntrega() {
        return pagamentoEntrega;
    }

    public void setPagamentoEntrega(boolean pagamentoEntrega) {
        this.pagamentoEntrega = pagamentoEntrega;
    }

    public boolean isPagamentoLocal() {
        return pagamentoLocal;
    }

    public void setPagamentoLocal(boolean pagamentoLocal) {
        this.pagamentoLocal = pagamentoLocal;
    }

    public LocalDateTime getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(LocalDateTime dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }
}
