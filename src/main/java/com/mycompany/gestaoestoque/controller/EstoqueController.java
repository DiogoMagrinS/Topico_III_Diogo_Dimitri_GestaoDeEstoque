/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.controller;

import com.mycompany.gestaoestoque.entity.ProdutoEntity;
import com.mycompany.gestaoestoque.facade.ProdutoFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Named("estoqueController")
@RequestScoped
public class EstoqueController implements Serializable {

    @EJB
    private ProdutoFacade produtoFacade;

    public List<ProdutoEntity> getProdutosOrdenadosPorEstoque() {
        return produtoFacade.listarTodos()
                .stream()
                .sorted(Comparator.comparingInt(ProdutoEntity::getQuantidadeEstoque))
                .collect(Collectors.toList());
    }

    public String getClasseEstoque(Integer qtde) {
        if (qtde == null) return "estoque-desconhecido";
        if (qtde < 0) return "estoque-vermelho";
        if (qtde < 10) return "estoque-laranja";
        if (qtde < 30) return "estoque-amarelo";
        return "estoque-verde";
    }
}
