package com.juliacardozo.sales_crud_application_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliacardozo.sales_crud_application_server.model.Cliente;
import com.juliacardozo.sales_crud_application_server.repository.ClienteRepository;

/**
 * User: Júlia Cardozo Date: 01/08/2024
 */

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> buscar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public void deletar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    public Cliente atualizar(Long id, Cliente clienteNovo) {
        if (clienteRepository.existsById(id)) {
            clienteNovo.setId(id);
            return clienteRepository.save(clienteNovo);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }
}
