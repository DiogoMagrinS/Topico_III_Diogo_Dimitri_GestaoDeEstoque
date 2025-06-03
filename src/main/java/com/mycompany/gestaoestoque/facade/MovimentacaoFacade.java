package com.mycompany.gestaoestoque.facade;

import com.mycompany.gestaoestoque.entity.MovimentacaoEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class MovimentacaoFacade extends AbstractFacade<MovimentacaoEntity> {

    @PersistenceContext(unitName = "GestaoEstoque")
    private EntityManager em;

    public MovimentacaoFacade() {
        super(MovimentacaoEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    // 🔍 Novo método: buscar movimentações por intervalo de datas
    public List<MovimentacaoEntity> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return em.createQuery(
                "SELECT m FROM MovimentacaoEntity m " +
                "WHERE m.dataMovimentacao BETWEEN :inicio AND :fim " +
                "ORDER BY m.dataMovimentacao DESC", MovimentacaoEntity.class)
            .setParameter("inicio", inicio)
            .setParameter("fim", fim)
            .getResultList();
    }
}
