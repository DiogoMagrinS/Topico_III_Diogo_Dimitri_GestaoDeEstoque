/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.facade;

import com.mycompany.gestaoestoque.entity.FornecedorEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class FornecedorFacade extends AbstractFacade<FornecedorEntity> {

    @PersistenceContext(unitName = "GestaoEstoque")
    private EntityManager em;

    public FornecedorFacade() {
        super(FornecedorEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
