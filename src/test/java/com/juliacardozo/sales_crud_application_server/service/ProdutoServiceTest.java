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

import com.juliacardozo.sales_crud_application_server.model.Produto;
import com.juliacardozo.sales_crud_application_server.repository.ProdutoRepository;

/**
 * User: Júlia Cardozo Date: 05/08/2024
 */

public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void teste_salvar_produto() {
        Produto produto = new Produto(1L, "Maçã", BigDecimal.valueOf(5.0));

        Mockito.when(produtoRepository.save(produto)).thenReturn(produto);

        Produto produtoSalvo = produtoService.salvar(produto);

        Assertions.assertEquals(produto, produtoSalvo);
    }

    @Test
    public void teste_buscar_produto() {
        Produto produto1 = new Produto(1L, "Maçã", BigDecimal.valueOf(5.0));
        Produto produto2 = new Produto(2L, "Banana", BigDecimal.valueOf(3.0));

        List<Produto> produtosSalvos = new ArrayList<>();
        produtosSalvos.add(produto1);
        produtosSalvos.add(produto2);

        Mockito.when(produtoRepository.findAll()).thenReturn(produtosSalvos);

        List<Produto> produtosEncontrados = produtoService.buscar();

        Assertions.assertEquals(produtosSalvos, produtosEncontrados);
    }

    @Test
    public void teste_buscar_produto_por_id() {
        Produto produto = new Produto(1L, "Maçã", BigDecimal.valueOf(5.0));

        Mockito.when(produtoRepository.findById(produto.getId())).thenReturn(Optional.of(produto));

        Produto produtoBuscado = produtoService.buscarProdutoPorId(produto.getId());

        Assertions.assertEquals(produto, produtoBuscado);
    }

    @Test
    public void teste_deletar_produto_nao_existente() {
        Long produtoId = 5L;

        Mockito.when(produtoRepository.existsById(produtoId)).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class, () -> produtoService.deletar(produtoId));
    }

    @Test
    public void teste_atualizar_produto_existente() {
        Produto produtoAtual = new Produto(1L, "Maçã", BigDecimal.valueOf(5.0));
        Produto produtoNovo = new Produto(null, "Maçã Atualizada", BigDecimal.valueOf(6.0));

        Mockito.when(produtoRepository.existsById(produtoAtual.getId())).thenReturn(true);
        Mockito.when(produtoRepository.save(produtoNovo)).thenReturn(produtoAtual);

        Produto produtoAtualizado = produtoService.atualizar(produtoAtual.getId(), produtoNovo);

        Assertions.assertEquals(produtoAtual, produtoAtualizado);
    }

    @Test
    public void teste_atualizar_produto_nao_existente() {
        Produto produtoNovo = new Produto(1L, "Maçã Atualizada", BigDecimal.valueOf(6.0));

        Mockito.when(produtoRepository.existsById(produtoNovo.getId())).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class, () -> produtoService.atualizar(produtoNovo.getId(), produtoNovo));
    }
}
