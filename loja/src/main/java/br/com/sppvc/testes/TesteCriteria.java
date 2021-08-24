package br.com.sppvc.testes;

import br.com.sppvc.dao.CategoriaDAO;
import br.com.sppvc.dao.ProdutoDAO;
import br.com.sppvc.modelo.*;
import br.com.sppvc.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class TesteCriteria {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

        produtoDAO.buscarPorParametrosComCriteria("PS5", null, null);
    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogamees = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiaomi","Mi 10", new BigDecimal("2000"), celulares);
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("5000"), videogamees);
        Produto notebook = new Produto("Macbook", "Macbook Pro", new BigDecimal("7000"), informatica);

        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);

        entityManager.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        categoriaDAO.cadastrar(videogamees);
        categoriaDAO.cadastrar(informatica);

        produtoDAO.cadastrar(celular);
        produtoDAO.cadastrar(videogame);
        produtoDAO.cadastrar(notebook);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
