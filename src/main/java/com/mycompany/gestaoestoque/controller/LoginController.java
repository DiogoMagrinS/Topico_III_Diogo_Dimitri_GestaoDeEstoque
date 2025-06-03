/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoestoque.controller;

import com.mycompany.gestaoestoque.entity.PessoaEntity;
import com.mycompany.gestaoestoque.facade.PessoaFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String email;
    private String senha;
    private PessoaEntity usuarioLogado;

    @EJB
    private PessoaFacade pessoaFacade;

    public void login() {
        for (PessoaEntity p : pessoaFacade.listarTodos()) {
            if (p.getEmail().equalsIgnoreCase(email) && p.getSenha().equals(senha)) {
                usuarioLogado = p;

                // Redireciona conforme o tipo
                try {
                    if ("GERENTE".equalsIgnoreCase(p.getTipoUsuario())) {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("pessoa.xhtml");
                    } else {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("recebimento.xhtml");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Email ou senha inválidos"));
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters e setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PessoaEntity getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(PessoaEntity usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
