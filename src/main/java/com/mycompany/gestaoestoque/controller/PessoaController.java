package com.mycompany.gestaoestoque.controller;

import com.mycompany.gestaoestoque.entity.PessoaEntity;
import com.mycompany.gestaoestoque.facade.PessoaFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("pessoaController")
@SessionScoped
public class PessoaController implements Serializable {

    private PessoaEntity pessoa = new PessoaEntity();
    private PessoaEntity selected;

    @EJB
    private PessoaFacade pessoaFacade;

    public void prepararNovaPessoa() {
        pessoa = new PessoaEntity();
    }

    public void adicionarPessoa() {
        try {
            if (pessoa.getId() == null) {
                pessoaFacade.salvar(pessoa);
            } else {
                pessoaFacade.editar(pessoa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pessoa = new PessoaEntity();
    }

    public void deletarPessoa() {
        try {
            if (selected != null && selected.getId() != null) {
                pessoaFacade.excluir(selected);
                selected = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PessoaEntity> getPessoaList() {
        return pessoaFacade.listarTodos();
    }

    public PessoaEntity getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaEntity pessoa) {
        if (pessoa != null) {
            this.pessoa = pessoa;
        }
    }

    public PessoaEntity getSelected() {
        return selected;
    }

    public void setSelected(PessoaEntity selected) {
        this.selected = selected;
    }

    public void limparPessoa() {
        this.pessoa = new PessoaEntity();
    }
}
