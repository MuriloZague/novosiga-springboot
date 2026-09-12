package com.novosiga.novosiga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novosiga.novosiga.model.Produto;
import com.novosiga.novosiga.repository.ProdutoRepository;

@Service
public class ProdutoService {

    //injeção de dependencia do repositorio de produtos
    @Autowired
    private ProdutoRepository produtoRepository;

    //metodo para salvar um produto
    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

    //metodo para listar todos os produtos
    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    //metodo para excluir um produto
    public void deleteById(Integer id){
        produtoRepository.deleteById(id);
    }

    //metodo para buscar o produto pelo ID
    public Produto findById(Integer id){
        return produtoRepository.findById(id).orElse(null);
    }
}
