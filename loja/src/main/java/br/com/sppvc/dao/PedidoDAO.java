package br.com.sppvc.dao;

import br.com.sppvc.modelo.Pedido;
import br.com.sppvc.vo.RelatorioDeVendasVO;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDAO {

    private EntityManager entityManager;

    public PedidoDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Pedido pedido){
        this.entityManager.persist(pedido);
    }

    public BigDecimal valorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p ";

        return entityManager.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    /**
     * Select new = JPA identifica que precisa criar um objeto através de uma classe existente
      * para cada registro, a JPA vai jogar em uma lista
     */
    public List<RelatorioDeVendasVO> relatorioDeVendas() { //VO = Value Objects
        String jpql = "SELECT new br.com.sppvc.vo.RelatorioDeVendasVO(" +
                "produto.nome, " +
                "SUM(item.quantidade)," +
                "MAX(pedido.data)) FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome " +
                "ORDER BY item.quantidade DESC";
        return entityManager.createQuery(jpql, RelatorioDeVendasVO.class).getResultList(); // cada linha é um objeto

    }

    public Pedido buscarPedidoComCliente(Long id) {
        return entityManager.createQuery("SELECT p FROM Pedido p " +
                "JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
                            .setParameter("id", id)
                            . getSingleResult();

        // JOIN FETCH esta falando que nessa consulta já é para ser carregado o cliente e
        // nessa consulta é comportado como EAGER. Nessa consulta o cliente vem com o Pedido
    }
}
