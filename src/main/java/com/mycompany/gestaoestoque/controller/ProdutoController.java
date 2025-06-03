package com.mycompany.gestaoestoque.controller;

import com.mycompany.gestaoestoque.entity.ProdutoEntity;
import com.mycompany.gestaoestoque.entity.FornecedorEntity;
import com.mycompany.gestaoestoque.facade.ProdutoFacade;
import com.mycompany.gestaoestoque.facade.FornecedorFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("produtoController")
@SessionScoped
public class ProdutoController implements Serializable {

    private ProdutoEntity produto = new ProdutoEntity();
    private ProdutoEntity selected;

    private Integer fornecedorIdSelecionado;

    @EJB
    private ProdutoFacade produtoFacade;

    @EJB
    private FornecedorFacade fornecedorFacade;
    
    private ProdutoEntity produtoAjuste = new ProdutoEntity();
    private Integer novaQuantidade;


    public void prepararAjusteEstoque(ProdutoEntity produto) {
        this.produtoAjuste = produto;
        this.novaQuantidade = produto.getQuantidadeEstoque(); 
    }

    public void ajustarEstoque() {
        produtoAjuste.setQuantidadeEstoque(novaQuantidade);
        produtoFacade.editar(produtoAjuste);
        produtoAjuste = new ProdutoEntity();
        novaQuantidade = null;
    }

    public void prepararNovoProduto() {
        produto = new ProdutoEntity();
        fornecedorIdSelecionado = null;
    }

    public void adicionarProduto() {
        if (fornecedorIdSelecionado != null) {
            FornecedorEntity fornecedor = fornecedorFacade.buscarPorId(fornecedorIdSelecionado);
            produto.setFornecedor(fornecedor);
        }

        if (produto.getId() == null) {
            produtoFacade.salvar(produto);
        } else {
            produtoFacade.editar(produto);
        }

        produto = new ProdutoEntity();
        fornecedorIdSelecionado = null;
    }

    public void deletarProduto() {
        if (selected != null) {
            produtoFacade.excluir(selected);
            selected = null;
        }
    }

    public List<ProdutoEntity> getProdutoList() {
        return produtoFacade.listarTodos();
    }

    public List<FornecedorEntity> getFornecedorList() {
        return fornecedorFacade.listarTodos();
    }

    // Getters e setters
    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    public ProdutoEntity getSelected() {
        return selected;
    }

    public void setSelected(ProdutoEntity selected) {
        this.selected = selected;
    }

    public Integer getFornecedorIdSelecionado() {
        return fornecedorIdSelecionado;
    }

    public void setFornecedorIdSelecionado(Integer fornecedorIdSelecionado) {
        this.fornecedorIdSelecionado = fornecedorIdSelecionado;
    }
    
    public ProdutoEntity getProdutoAjuste() {
    return produtoAjuste;
    }

    public void setProdutoAjuste(ProdutoEntity produtoAjuste) {
        this.produtoAjuste = produtoAjuste;
    }

    public Integer getNovaQuantidade() {
        return novaQuantidade;
    }

    public void setNovaQuantidade(Integer novaQuantidade) {
        this.novaQuantidade = novaQuantidade;
    }

   }
