package br.com.sppvc.testes;

import br.com.sppvc.dao.CategoriaDAO;
import br.com.sppvc.dao.ProdutoDAO;
import br.com.sppvc.modelo.Categoria;
import br.com.sppvc.modelo.CategoriaId;
import br.com.sppvc.modelo.Produto;
import br.com.sppvc.util.JPAUtil;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {

        cadastrarProduto();
        EntityManager entManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entManager);

        Produto produto = produtoDAO.buscarPorId(1l);
        System.out.println("O preço e' "+ produto.getPreco());

        List<Produto> produtos = produtoDAO.buscarTodos();
        produtos.forEach(prod -> System.out.println(prod.getNome()));

        produtos = produtoDAO.buscarPorNome("Xiaomi");
        produtos.forEach(prod -> System.out.println("Busca por nome: " + prod.getNome()));

        produtos = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
        produtos.forEach(prod -> System.out.println("Busca por nome da categoria: " + prod.getNome()));

        BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoComNome("Xiaomi");
        System.out.println("Buscar por preço: " + precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");

        Produto celular = new Produto("Xiaomi","Mi 10", new BigDecimal("2000"), celulares);

        EntityManager entManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entManager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(entManager);

        entManager.getTransaction().begin();
        entManager.persist(celulares);

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);

        entManager.getTransaction().commit();

        entManager.find(Categoria.class, new CategoriaId("CELULARES", "XPTO"));

        entManager.close();
    }
}
