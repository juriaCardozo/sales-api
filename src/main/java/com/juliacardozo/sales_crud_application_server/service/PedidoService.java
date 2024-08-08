package com.juliacardozo.sales_crud_application_server.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliacardozo.sales_crud_application_server.model.Pedido;
import com.juliacardozo.sales_crud_application_server.repository.PedidoRepository;

/**
 * User: Júlia Cardozo Date: 01/08/2024
 */

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> buscar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void deletar(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pedido não encontrado");
        }
    }

    public void inativar(Long id) {
        if (pedidoRepository.existsById(id)) {
            Pedido pedido = pedidoRepository.findById(id).get();
            Boolean pedidoIsAtivo = !pedido.getIsAtivo();
            pedidoRepository.inactivateById(id, pedidoIsAtivo);
        } else {
            throw new RuntimeException("Pedido não encontrado");
        }
    }

    public Pedido atualizar(Long id, Pedido pedidoNovo) {
        if (pedidoRepository.existsById(id)) {
            pedidoNovo.setId(id);
            return pedidoRepository.save(pedidoNovo);
        } else {
            throw new RuntimeException("Pedido não encontrado");
        }
    }

    public List<Pedido> buscarPedidosPorCliente(Long clienteId) {
        return pedidoRepository.findPedidosPorCliente(clienteId);
    }

    public List<Pedido> buscarPedidosPorProduto(Long produtoId) {
        return pedidoRepository.findPedidosPorProduto(produtoId);
    }

    public List<Pedido> filtrarPedidos(String dataInicio, String dataFim, Long clienteId, Long produtoId, Boolean isAtivo) {
        LocalDate startDate = (dataInicio != null) ? LocalDate.parse(dataInicio) : null;
        LocalDate endDate = (dataFim != null) ? LocalDate.parse(dataFim) : null;
        return pedidoRepository.findPorFiltros(startDate, endDate, clienteId, produtoId, isAtivo);
    }
}
