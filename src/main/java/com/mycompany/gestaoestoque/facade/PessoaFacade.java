/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.facade;

import com.mycompany.gestaoestoque.entity.PessoaEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PessoaFacade extends AbstractFacade<PessoaEntity> {

    @PersistenceContext(unitName = "GestaoEstoque")
    private EntityManager em;

    public PessoaFacade() {
        super(PessoaEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

