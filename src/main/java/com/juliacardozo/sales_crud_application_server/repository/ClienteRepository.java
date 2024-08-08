package com.juliacardozo.sales_crud_application_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juliacardozo.sales_crud_application_server.model.Cliente;

/**
 * User: Júlia Cardozo Date: 05/08/2024
 */

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
