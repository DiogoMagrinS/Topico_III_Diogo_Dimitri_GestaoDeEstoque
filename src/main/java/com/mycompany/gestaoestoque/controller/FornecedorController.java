/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.controller;

import com.mycompany.gestaoestoque.entity.FornecedorEntity;
import com.mycompany.gestaoestoque.facade.FornecedorFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("fornecedorController")
@SessionScoped
public class FornecedorController implements Serializable {

    private FornecedorEntity fornecedor = new FornecedorEntity();
    private FornecedorEntity selected;

    @EJB
    private FornecedorFacade fornecedorFacade;

    public void prepararNovoFornecedor() {
        fornecedor = new FornecedorEntity();
    }

    public void adicionarFornecedor() {
        if (fornecedor.getId() == null) {
            fornecedorFacade.salvar(fornecedor);
        } else {
            fornecedorFacade.editar(fornecedor);
        }
        fornecedor = new FornecedorEntity();
    }

    public void deletarFornecedor() {
        if (selected != null && selected.getId() != null) {
            fornecedorFacade.excluir(selected);
            selected = null;
        }
    }

    public List<FornecedorEntity> getFornecedorList() {
        return fornecedorFacade.listarTodos();
    }

    public FornecedorEntity getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorEntity fornecedor) {
        this.fornecedor = fornecedor;
    }

    public FornecedorEntity getSelected() {
        return selected;
    }

    public void setSelected(FornecedorEntity selected) {
        this.selected = selected;
    }
}
