package com.novosiga.novosiga.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProduto;

    @Column(nullable = false, length = 67)
    private String descricaoProduto;

    @Column(nullable = false, length = 20)
    private String marcaProduto;

    @Column(nullable = false, length = 40)
    private String modeloProduto;

    @Column(nullable = false, length = 10)
    private Double valorProduto;

    @Lob
    private byte[] fotoProduto;

    @Column(length = 20)
    private String tipoFoto;

}
