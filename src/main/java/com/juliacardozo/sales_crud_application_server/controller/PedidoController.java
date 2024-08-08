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
import org.springframework.web.bind.annotation.RequestParam;

import com.juliacardozo.sales_crud_application_server.model.Pedido;
import com.juliacardozo.sales_crud_application_server.service.PedidoService;

/**
 * User: Júlia Cardozo Date: 01/08/2024
 */

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        return pedidoService.salvar(pedido);
    }

    @GetMapping()
    public List<Pedido> listarPedidos() {
        return pedidoService.buscar();
    }

    @GetMapping("/{id}")
    public Pedido listarPedidoPorId(@PathVariable("id") Long id) {
        return pedidoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void excluirPedido(@PathVariable("id") Long id) {
        pedidoService.deletar(id);
    }

    @PutMapping("/inativar/{id}")
    public void inativarPedido(@PathVariable("id") Long id) {
        pedidoService.inativar(id);
    }

    @PutMapping("/{id}")
    public Pedido atualizarPedido(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
        return pedidoService.atualizar(id, pedido);
    }

    @GetMapping("/clientes/{clienteId}")
    public List<Pedido> listarPedidosPorCliente(@PathVariable("clienteId") Long clienteId) {

        return pedidoService.buscarPedidosPorCliente(clienteId);
    }

    @GetMapping("/produtos/{produtoId}")
    public List<Pedido> listarPedidosPorProduto(@PathVariable("produtoId") Long produtoId) {

        return pedidoService.buscarPedidosPorProduto(produtoId);
    }

    @GetMapping("/filtrar")
    public List<Pedido> filtrarPedidos(
            @RequestParam(required = false) String dataInicio,
            @RequestParam(required = false) String dataFim,
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) Long produtoId,
            @RequestParam(required = false) Boolean isAtivo) {

        return pedidoService.filtrarPedidos(dataInicio, dataFim, clienteId, produtoId, isAtivo);
    }
}
