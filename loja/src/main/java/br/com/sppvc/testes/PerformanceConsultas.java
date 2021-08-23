package br.com.sppvc.testes;

import br.com.sppvc.dao.CategoriaDAO;
import br.com.sppvc.dao.ClienteDAO;
import br.com.sppvc.dao.PedidoDAO;
import br.com.sppvc.dao.ProdutoDAO;
import br.com.sppvc.modelo.*;
import br.com.sppvc.util.JPAUtil;
import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformanceConsultas {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager entityManager = JPAUtil.getEntityManager();
        PedidoDAO pedidoDAO = new PedidoDAO(entityManager);
        Pedido pedido = pedidoDAO.buscarPedidoComCliente(1l);
        entityManager.close();
        System.out.println(pedido.getCliente().getNome());
    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogamees = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiaomi","Mi 10", new BigDecimal("2000"), celulares);
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("5000"), videogamees);
        Produto notebook = new Produto("Macbook", "Macbook Pro", new BigDecimal("7000"), informatica);

        Cliente cliente = new Cliente("Henrique", "123456");

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, celular));
        pedido.adicionarItem(new ItemPedido(40, pedido, videogame));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionarItem(new ItemPedido(2, pedido, notebook));


        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
        ClienteDAO clienteDAO = new ClienteDAO(entityManager);
        PedidoDAO pedidoDAO = new PedidoDAO(entityManager);

        entityManager.getTransaction().begin();

        clienteDAO.cadastrar(cliente);
        categoriaDAO.cadastrar(celulares);
        categoriaDAO.cadastrar(videogamees);
        categoriaDAO.cadastrar(informatica);

        produtoDAO.cadastrar(celular);
        produtoDAO.cadastrar(videogame);
        produtoDAO.cadastrar(notebook);

        pedidoDAO.cadastrar(pedido);
        pedidoDAO.cadastrar(pedido2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
