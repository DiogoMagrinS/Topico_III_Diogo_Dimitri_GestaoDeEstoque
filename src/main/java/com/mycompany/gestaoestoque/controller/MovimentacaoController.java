package com.mycompany.gestaoestoque.controller;

import com.mycompany.gestaoestoque.entity.MovimentacaoEntity;
import com.mycompany.gestaoestoque.entity.ProdutoEntity;
import com.mycompany.gestaoestoque.facade.MovimentacaoFacade;
import com.mycompany.gestaoestoque.facade.ProdutoFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Named("movimentacaoController")
@SessionScoped
public class MovimentacaoController implements Serializable {

    private MovimentacaoEntity movimentacao = new MovimentacaoEntity();
    private Integer produtoIdSelecionado;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private List<MovimentacaoEntity> movimentacoesFiltradas;


    @EJB
    private MovimentacaoFacade movimentacaoFacade;

    @EJB
    private ProdutoFacade produtoFacade;

    public void prepararNova(String tipo) {
        movimentacao = new MovimentacaoEntity();
        movimentacao.setTipo(tipo);
        produtoIdSelecionado = null;
    }

    public void salvarMovimentacao() {
        ProdutoEntity produto = produtoFacade.buscarPorId(produtoIdSelecionado);
        movimentacao.setProduto(produto);
        movimentacao.setDataMovimentacao(LocalDateTime.now());

        int qtde = movimentacao.getQuantidade();
        if ("ENTRADA".equals(movimentacao.getTipo())) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + qtde);
        } else if ("SAIDA".equals(movimentacao.getTipo())) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - qtde);
        }

        produtoFacade.editar(produto);
        movimentacaoFacade.salvar(movimentacao);

        movimentacao = new MovimentacaoEntity();
        produtoIdSelecionado = null;
    }

    public List<MovimentacaoEntity> getMovimentacoes() {
    if (movimentacoesFiltradas != null) {
        return movimentacoesFiltradas;
    }
    return movimentacaoFacade.listarTodos();
}
    
    public List<ProdutoEntity> getProdutos() {
        return produtoFacade.listarTodos();
    }

    // Getters e setters
    public MovimentacaoEntity getMovimentacao() { return movimentacao; }
    public void setMovimentacao(MovimentacaoEntity movimentacao) { this.movimentacao = movimentacao; }

    public Integer getProdutoIdSelecionado() { return produtoIdSelecionado; }
    public void setProdutoIdSelecionado(Integer produtoIdSelecionado) { this.produtoIdSelecionado = produtoIdSelecionado; }
    
   public void filtrarPorData() {
    if (dataInicio != null && dataFim != null) {
        LocalDateTime fimAjustado = dataFim.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        movimentacoesFiltradas = movimentacaoFacade.buscarPorPeriodo(dataInicio, fimAjustado);
    } else {
        movimentacoesFiltradas = movimentacaoFacade.listarTodos();
    }
}

   public LocalDateTime getDataInicio() {
        return dataInicio;
}

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
}

    public LocalDateTime getDataFim() {
        return dataFim;
}

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
}
    
    public void limparFiltro() {
    movimentacoesFiltradas = null;
    dataInicio = null;
    dataFim = null;
}

    
}
