package br.com.sppvc.dao;

import br.com.sppvc.modelo.Cliente;

import javax.persistence.EntityManager;

public class ClienteDAO {

    private EntityManager entityManager;

    public ClienteDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Cliente cliente){
        this.entityManager.persist(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return entityManager.find(Cliente.class, id);
    }
}
