package com.novosiga.novosiga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor
public class ItemDoPedido {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idItem;

    private Integer quantidade;

    private Double preco;

    private Double subtotal;

    //Relacionamento com Pedido
    @ManyToOne 
    @JoinColumn(name = "idPedido_fk")
    private Pedido pedido;

    //Relacionamento com Produto
    @ManyToOne
    @JoinColumn(name = "idProduto_fk")
    private Produto produto;

    //Método para calcular o subtotal
    public Double calcularSubtotal(){
        return quantidade * preco;
    }

    //Método para atualizar o subtotal
    public void atualizarSubtotal(){
        this.subtotal = calcularSubtotal();
    }
}
