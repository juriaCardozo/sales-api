package com.juliacardozo.sales_crud_application_server.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cliente cliente;
    private LocalDate data;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
    private Boolean isAtivo;
}
