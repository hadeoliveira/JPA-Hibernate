package br.com.sppvc.testes;

import br.com.sppvc.dao.CategoriaDAO;
import br.com.sppvc.dao.ClienteDAO;
import br.com.sppvc.dao.PedidoDAO;
import br.com.sppvc.dao.ProdutoDAO;
import br.com.sppvc.modelo.*;
import br.com.sppvc.util.JPAUtil;
import br.com.sppvc.vo.RelatorioDeVendasVO;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {

    public static void main(String[] args) {
        popularBancoDeDados();

        EntityManager entityManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        ClienteDAO clienteDAO = new ClienteDAO(entityManager);

        Produto produto = produtoDAO.buscarPorId(1l);
        Produto produto2 = produtoDAO.buscarPorId(2l);
        Produto produto3 = produtoDAO.buscarPorId(3l);

        Cliente cliente = clienteDAO.buscarPorId(1l);
        entityManager.getTransaction().begin();


        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));
        pedido.adicionarItem(new ItemPedido(40, pedido, produto2));

     //   Pedido pedido2 = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(2, pedido, produto3));

        PedidoDAO pedidoDAO = new PedidoDAO(entityManager);
        pedidoDAO.cadastrar(pedido);

        entityManager.getTransaction().commit();

        BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
        System.out.println("O total vendido é: " + totalVendido);

        List<RelatorioDeVendasVO> relatorio = pedidoDAO.relatorioDeVendas();
        relatorio.forEach(System.out::println);

    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogamees = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiaomi","Mi 10", new BigDecimal("2000"), celulares);
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("5000"), videogamees);
        Produto notebook = new Produto("Macbook", "Macbook Pro", new BigDecimal("7000"), informatica);

        Cliente cliente = new Cliente("Henrique", "123456");

        EntityManager entManager = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(entManager);
        CategoriaDAO categoriaDAO = new CategoriaDAO(entManager);
        ClienteDAO clienteDAO = new ClienteDAO(entManager);

        entManager.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        categoriaDAO.cadastrar(videogamees);
        categoriaDAO.cadastrar(informatica);

        produtoDAO.cadastrar(celular);
        produtoDAO.cadastrar(videogame);
        produtoDAO.cadastrar(notebook);

        clienteDAO.cadastrar(cliente);

        entManager.getTransaction().commit();
        entManager.close();
    }
}
