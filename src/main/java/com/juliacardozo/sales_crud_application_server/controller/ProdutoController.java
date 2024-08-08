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

import com.juliacardozo.sales_crud_application_server.model.Produto;
import com.juliacardozo.sales_crud_application_server.service.ProdutoService;

/**
 * User: Júlia Cardozo Date: 01/08/2024
 */

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.buscar();
    }

    @GetMapping("/{id}")
    public Produto listarProdutoPorId(@PathVariable("id") Long id) {
        return produtoService.buscarProdutoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletarProdutoPorId(@PathVariable("id") Long id) {
        produtoService.deletar(id);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable("id") Long id, @RequestBody Produto produto) {
        return produtoService.atualizar(id, produto);
    }
}
