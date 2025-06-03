/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.facade;

import com.mycompany.gestaoestoque.entity.ProdutoEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProdutoFacade extends AbstractFacade<ProdutoEntity> {

    @PersistenceContext(unitName = "GestaoEstoque")
    private EntityManager em;

    public ProdutoFacade() {
        super(ProdutoEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void atualizarEstoque(ProdutoEntity produto, int quantidadeAdicional) {
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidadeAdicional);
        editar(produto);
    }
}
