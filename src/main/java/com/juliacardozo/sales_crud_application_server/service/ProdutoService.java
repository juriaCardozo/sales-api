package com.juliacardozo.sales_crud_application_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliacardozo.sales_crud_application_server.model.Produto;
import com.juliacardozo.sales_crud_application_server.repository.ProdutoRepository;

/**
 * User: Júlia Cardozo Date: 01/08/2024
 */

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> buscar() {
        return produtoRepository.findAll();
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void deletar(Long id) {
        if(produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Produto não encontrado");
        }
    }

    public Produto atualizar(Long id, Produto produtoNovo) {
        if (produtoRepository.existsById(id)) {
            produtoNovo.setId(id);
            return produtoRepository.save(produtoNovo);
        } else {
            throw new RuntimeException("Produto não encontrado");
        }
    }
}
