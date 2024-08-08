package com.juliacardozo.sales_crud_application_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User: Júlia Cardozo Date: 01/08/2024
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Pedido pedido;
    @ManyToOne
    private Produto produto;
    private Integer quantidade;
}
