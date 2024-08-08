package com.juliacardozo.sales_crud_application_server.service;

import java.math.BigDecimal;
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
import com.juliacardozo.sales_crud_application_server.repository.ClienteRepository;

/**
 * User: Júlia Cardozo Date: 05/08/2024
 */


public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void teste_salvar_cliente() {
        Cliente cliente = new Cliente(1L, "Marcos", BigDecimal.valueOf(500.0), 15);

        Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente clienteSalvo = clienteService.salvar(cliente);

        Assertions.assertEquals(cliente, clienteSalvo);
    }

    @Test
    public void teste_buscar_cliente() {
        Cliente cliente1 = new Cliente(1L, "Marcos", BigDecimal.valueOf(500.0), 15);
        Cliente cliente2 = new Cliente(2L, "João", BigDecimal.valueOf(200.0), 5);

        List<Cliente> clientesSalvos = new ArrayList<>();
        clientesSalvos.add(cliente1);
        clientesSalvos.add(cliente2);

        Mockito.when(clienteRepository.findAll()).thenReturn(clientesSalvos);

        List<Cliente> clientesEncontrados = clienteService.buscar();

        Assertions.assertEquals(clientesSalvos, clientesEncontrados);
    }

    @Test
    public void teste_buscar_cliente_por_id(){
        Cliente cliente = new Cliente(1L, "Marcos", BigDecimal.valueOf(500.0), 15);

        Mockito.when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));

        Cliente clienteBuscado = clienteService.buscarPorId(cliente.getId());

        Assertions.assertEquals(cliente, clienteBuscado);
    }

    @Test
    public void teste_deletar_cliente_nao_existente() {
        Long clienteId = 5L;

        Mockito.when(clienteRepository.existsById(clienteId)).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class, () -> clienteService.deletar(clienteId));
    }

    @Test
    public void teste_atualizar_cliente_existente() {
        Cliente clienteAtual = new Cliente(1L, "Marcos", BigDecimal.valueOf(500.0), 15);
        Cliente clienteNovo = new Cliente(null, "Marcos Atualizado", BigDecimal.valueOf(600.0), 20);

        Mockito.when(clienteRepository.existsById(clienteAtual.getId())).thenReturn(true);
        Mockito.when(clienteRepository.save(clienteNovo)).thenReturn(clienteAtual);

        Cliente clienteAtualizado = clienteService.atualizar(clienteAtual.getId(), clienteNovo);

        Assertions.assertEquals(clienteAtual, clienteAtualizado);
    }

    @Test
    public void teste_atualizar_cliente_nao_existente() {
        Cliente clienteNovo = new Cliente(1L, "Marcos Atualizado", BigDecimal.valueOf(600.0), 20);

        Mockito.when(clienteRepository.existsById(clienteNovo.getId())).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class, () -> clienteService.atualizar(clienteNovo.getId(), clienteNovo));
    }
}
