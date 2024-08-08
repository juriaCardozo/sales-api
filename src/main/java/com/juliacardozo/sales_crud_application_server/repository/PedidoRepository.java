package com.juliacardozo.sales_crud_application_server.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.juliacardozo.sales_crud_application_server.model.Pedido;

/**
 * User: Júlia Cardozo Date: 05/08/2024
 */

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("UPDATE Pedido p SET p.isAtivo = :pedidoIsAtivo WHERE p.id = :id")
    void inactivateById(@Param("id") Long id, @Param("pedidoIsAtivo") Boolean pedidoIsAtivo);

    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId")
    List<Pedido> findPedidosPorCliente(@Param("clienteId") Long clienteId);

    @Query("SELECT p FROM Pedido p JOIN p.itens i WHERE i.produto.id = :produtoId")
    List<Pedido> findPedidosPorProduto(@Param("produtoId") Long produtoId);

    @Query("SELECT p FROM Pedido p " +
            "JOIN p.itens i " +
            "WHERE (:startDate IS NULL OR p.data >= :startDate) " +
            "AND (:endDate IS NULL OR p.data <= :endDate) " +
            "AND (:clienteId IS NULL OR p.cliente.id = :clienteId) " +
            "AND (:produtoId IS NULL OR i.produto.id = :produtoId) " +
            "AND (:isAtivo IS NULL OR p.isAtivo = :isAtivo)")
    List<Pedido> findPorFiltros(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("clienteId") Long clienteId,
            @Param("produtoId") Long produtoId,
            @Param("isAtivo") Boolean isAtivo
    );

}
