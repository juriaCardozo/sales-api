package com.juliacardozo.sales_crud_application_server.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.juliacardozo.sales_crud_application_server.model.Cliente;
import com.juliacardozo.sales_crud_application_server.model.ItemPedido;
import com.juliacardozo.sales_crud_application_server.model.Pedido;
import com.juliacardozo.sales_crud_application_server.model.Produto;
import com.juliacardozo.sales_crud_application_server.repository.PedidoRepository;

/**
 * User: Júlia Cardozo Date: 05/08/2024
 */

public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void teste_salvar_pedido() {
        Pedido pedido = generatePedido(1L, LocalDate.now(), true);

        Mockito.when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido pedidoSalvo = pedidoService.salvar(pedido);

        Assertions.assertEquals(pedido, pedidoSalvo);
    }

    @Test
    public void teste_buscar_pedido() {
        Pedido pedido1 = generatePedido(1L, LocalDate.now(), true);
        Pedido pedido2 = generatePedido(2L, LocalDate.now().plusDays(1), false);

        List<Pedido> pedidosSalvos = new ArrayList<>();
        pedidosSalvos.add(pedido1);
        pedidosSalvos.add(pedido2);

        Mockito.when(pedidoRepository.findAll()).thenReturn(pedidosSalvos);

        List<Pedido> pedidosEncontrados = pedidoService.buscar();

        Assertions.assertEquals(pedidosSalvos, pedidosEncontrados);
    }

    @Test
    public void teste_buscar_pedido_por_id() {
        Pedido pedido = generatePedido(1L, LocalDate.now(), true);

        Mockito.when(pedidoRepository.findById(pedido.getId())).thenReturn(Optional.of(pedido));

        Pedido pedidoBuscado = pedidoService.buscarPorId(pedido.getId());

        Assertions.assertEquals(pedido, pedidoBuscado);
    }

    @Test
    public void teste_deletar_pedido_nao_existente() {
        Long pedidoId = 5L;

        Mockito.when(pedidoRepository.existsById(pedidoId)).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class, () -> pedidoService.deletar(pedidoId));
    }

    @Test
    public void teste_atualizar_pedido_existente() {
        Pedido pedidoAtual = generatePedido(1L, LocalDate.now(), true);
        Pedido pedidoNovo = generatePedido(1L, LocalDate.now().plusDays(1), false);

        Mockito.when(pedidoRepository.existsById(pedidoAtual.getId())).thenReturn(true);
        Mockito.when(pedidoRepository.save(pedidoNovo)).thenReturn(pedidoAtual);

        Pedido pedidoAtualizado = pedidoService.atualizar(pedidoAtual.getId(), pedidoNovo);

        Assertions.assertEquals(pedidoAtual, pedidoAtualizado);
    }

    @Test
    public void teste_atualizar_pedido_nao_existente() {
        Pedido pedidoNovo = generatePedido(1L, LocalDate.now().plusDays(1), false);

        Mockito.when(pedidoRepository.existsById(pedidoNovo.getId())).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class, () -> pedidoService.atualizar(pedidoNovo.getId(), pedidoNovo));
    }

    @Test
    public void teste_inativar_pedido_existente() {
        Pedido pedido = generatePedido(1L, LocalDate.now(), true);

        Mockito.when(pedidoRepository.existsById(pedido.getId())).thenReturn(true);
        Mockito.when(pedidoRepository.findById(pedido.getId())).thenReturn(Optional.of(pedido));
        Mockito.doNothing().when(pedidoRepository).inactivateById(pedido.getId(), false);

        pedidoService.inativar(pedido.getId());

        Mockito.verify(pedidoRepository).inactivateById(pedido.getId(), false);
    }

    @Test
    public void teste_inativar_pedido_nao_existente() {
        Long pedidoId = 5L;

        Mockito.when(pedidoRepository.existsById(pedidoId)).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class, () -> pedidoService.inativar(pedidoId));
    }

    @Test
    public void teste_buscar_pedidos_por_cliente() {
        Pedido pedido1 = generatePedido(1L, LocalDate.now(), true);
        Pedido pedido2 = generatePedido(2L, LocalDate.now().plusDays(1), false);

        List<Pedido> pedidosSalvos = new ArrayList<>();
        pedidosSalvos.add(pedido1);
        pedidosSalvos.add(pedido2);

        Mockito.when(pedidoRepository.findPedidosPorCliente(1L)).thenReturn(pedidosSalvos);

        List<Pedido> pedidosEncontrados = pedidoService.buscarPedidosPorCliente(1L);

        Assertions.assertEquals(pedidosSalvos, pedidosEncontrados);
    }

    @Test
    public void teste_buscar_pedidos_por_produto() {
        Pedido pedido1 = generatePedido(1L, LocalDate.now(), true);
        Pedido pedido2 = generatePedido(2L, LocalDate.now().plusDays(1), false);

        List<Pedido> pedidosSalvos = new ArrayList<>();
        pedidosSalvos.add(pedido1);
        pedidosSalvos.add(pedido2);

        Mockito.when(pedidoRepository.findPedidosPorProduto(1L)).thenReturn(pedidosSalvos);

        List<Pedido> pedidosEncontrados = pedidoService.buscarPedidosPorProduto(1L);

        Assertions.assertEquals(pedidosSalvos, pedidosEncontrados);
    }

    @Test
    public void teste_filtrar_pedidos() {
        Pedido pedido1 = generatePedido(1L, LocalDate.now(), true);
        Pedido pedido2 = generatePedido(2L, LocalDate.now().plusDays(1), false);

        List<Pedido> pedidosSalvos = new ArrayList<>();
        pedidosSalvos.add(pedido1);
        pedidosSalvos.add(pedido2);

        Mockito.when(pedidoRepository.findPorFiltros(LocalDate.now(), LocalDate.now().plusDays(1), 1L, 1L, true))
                .thenReturn(pedidosSalvos);

        List<Pedido> pedidosEncontrados = pedidoService.filtrarPedidos(
                LocalDate.now().toString(),
                LocalDate.now().plusDays(1).toString(),
                1L,
                1L,
                true
        );

        Assertions.assertEquals(pedidosSalvos, pedidosEncontrados);
    }

    private Pedido generatePedido(Long id, LocalDate data, Boolean isAtivo) {
        Cliente cliente = new Cliente(1L, "Marcos", BigDecimal.valueOf(500.0), 15);
        Produto produto = new Produto(1L, "Nutella 1kg", BigDecimal.valueOf(50.0));

        List<ItemPedido> itens = new ArrayList<>();
        ItemPedido itemPedido1 = new ItemPedido(1L, null, produto, 5);
        ItemPedido itemPedido2 = new ItemPedido(2L, null, produto, 3);
        itens.add(itemPedido1);
        itens.add(itemPedido2);

        return new Pedido(id, cliente, data, itens, isAtivo);
    }
}
