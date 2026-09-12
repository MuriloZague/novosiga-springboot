package com.novosiga.novosiga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novosiga.novosiga.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
