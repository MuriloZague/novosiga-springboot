package com.novosiga.novosiga.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novosiga.novosiga.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    // Busca por texto (nome, CPF ou cidade) e filtro opcional por curso.
    // q vem sempre como string ("" = sem busca) para o Postgres inferir o tipo.
    @Query("SELECT a FROM Aluno a WHERE " +
        "(:q = '' OR LOWER(a.nomeAluno) LIKE LOWER(CONCAT('%', :q, '%')) " +
        "  OR a.cpfAluno LIKE CONCAT('%', :q, '%') " +
        "  OR LOWER(a.cidadeAluno) LIKE LOWER(CONCAT('%', :q, '%'))) AND " +
        "(:cursoId IS NULL OR a.curso.idCurso = :cursoId)")
    List<Aluno> buscar(@Param("q") String q,
                       @Param("cursoId") Integer cursoId,
                       Sort sort);
}
