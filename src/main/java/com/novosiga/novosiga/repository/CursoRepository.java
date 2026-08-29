package com.novosiga.novosiga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novosiga.novosiga.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    
}
