package com.novosiga.novosiga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novosiga.novosiga.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    // Busca por texto (descricao ou modelo) e filtro opcional por marca. q/marca = "" ignoram.
    @Query("SELECT p FROM Produto p WHERE " +
        "(:q = '' OR LOWER(p.descricaoProduto) LIKE LOWER(CONCAT('%', :q, '%')) " +
        "  OR LOWER(p.modeloProduto) LIKE LOWER(CONCAT('%', :q, '%'))) AND " +
        "(:marca = '' OR p.marcaProduto = :marca)")
    List<Produto> buscar(@Param("q") String q,
                         @Param("marca") String marca,
                         Sort sort);

    // Valores distintos de marca para o dropdown de filtro.
    @Query("SELECT DISTINCT p.marcaProduto FROM Produto p " +
        "WHERE p.marcaProduto IS NOT NULL AND p.marcaProduto <> '' " +
        "ORDER BY p.marcaProduto")
    List<String> findMarcas();
}
