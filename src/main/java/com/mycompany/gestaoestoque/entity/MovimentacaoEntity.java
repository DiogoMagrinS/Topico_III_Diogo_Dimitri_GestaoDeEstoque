package com.mycompany.gestaoestoque.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao")
public class MovimentacaoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao;

    @Column(nullable = false, length = 10)
    private String tipo; // ENTRADA ou SAIDA

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public ProdutoEntity getProduto() { return produto; }
    public void setProduto(ProdutoEntity produto) { this.produto = produto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
