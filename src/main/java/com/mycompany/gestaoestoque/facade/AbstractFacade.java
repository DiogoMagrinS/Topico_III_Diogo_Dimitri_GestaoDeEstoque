package com.mycompany.gestaoestoque.facade;

import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void salvar(T entity) {
        getEntityManager().persist(entity);
    }

    public void editar(T entity) {
        getEntityManager().merge(entity);
    }

    public void excluir(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T buscarPorId(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> listarTodos() {
        return getEntityManager()
                .createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }
}
