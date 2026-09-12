package com.novosiga.novosiga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novosiga.novosiga.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
}
