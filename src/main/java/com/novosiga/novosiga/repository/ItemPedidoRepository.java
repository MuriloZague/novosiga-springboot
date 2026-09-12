package com.novosiga.novosiga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novosiga.novosiga.model.ItemDoPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemDoPedido, Integer> {
    
}
