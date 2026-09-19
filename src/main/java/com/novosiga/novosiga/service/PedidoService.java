package com.novosiga.novosiga.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novosiga.novosiga.model.ItemDoPedido;
import com.novosiga.novosiga.model.Pedido;
import com.novosiga.novosiga.model.Produto;
import com.novosiga.novosiga.repository.PedidoRepository;
import com.novosiga.novosiga.repository.ProdutoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Método para salvar um pedido
    public Pedido salvarPedido(Pedido pedido){
        pedido.setDataPedido(LocalDate.now());

        //Para cada item do pedido
        for(ItemDoPedido item : pedido.getItens()){
            //Buscar o produto no BD
            Produto produto = produtoRepository.findById(item.getProduto().getIdProduto()).
            orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            //Define o produto completo no item
            item.setProduto(produto);

            //Define o preco atual do produto
            item.setPreco(produto.getValorProduto());

            //Calcula o subtotal
            item.atualizarSubtotal();

            //Liga o item ao pedido
            item.setPedido(pedido);
        }

        //Calcula total do pedido
        pedido.atualizarTotal();

        //Salva tudo
        return  pedidoRepository.save(pedido);
    }

}
