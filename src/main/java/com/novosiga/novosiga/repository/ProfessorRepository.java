package com.novosiga.novosiga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novosiga.novosiga.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    // Busca por texto (nome ou CPF) e filtro opcional por graduacao. q/graduacao = "" ignoram.
    @Query("SELECT p FROM Professor p WHERE " +
        "(:q = '' OR LOWER(p.nomeProfessor) LIKE LOWER(CONCAT('%', :q, '%')) " +
        "  OR p.cpfProfessor LIKE CONCAT('%', :q, '%')) AND " +
        "(:graduacao = '' OR p.graduacaoProfessor = :graduacao)")
    List<Professor> buscar(@Param("q") String q,
                           @Param("graduacao") String graduacao,
                           Sort sort);

    // Valores distintos de graduacao para o dropdown de filtro.
    @Query("SELECT DISTINCT p.graduacaoProfessor FROM Professor p " +
        "WHERE p.graduacaoProfessor IS NOT NULL AND p.graduacaoProfessor <> '' " +
        "ORDER BY p.graduacaoProfessor")
    List<String> findGraduacoes();
}
