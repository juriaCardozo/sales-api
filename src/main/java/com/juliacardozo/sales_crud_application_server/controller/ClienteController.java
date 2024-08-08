package com.juliacardozo.sales_crud_application_server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juliacardozo.sales_crud_application_server.model.Cliente;
import com.juliacardozo.sales_crud_application_server.service.ClienteService;

/**
 * User: Júlia Cardozo Date: 01/08/2024
 */

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping()
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @GetMapping()
    public List<Cliente> listarClientes() {
        return clienteService.buscar();
    }

    @GetMapping("/{id}")
    public Cliente listarClientePorId(@PathVariable("id") Long id) {
        return clienteService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluirCliente(@PathVariable("id") Long id) {
        clienteService.deletar(id);
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        return clienteService.atualizar(id, cliente);
    }
}
