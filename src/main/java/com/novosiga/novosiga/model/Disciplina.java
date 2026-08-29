package com.novosiga.novosiga.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idDisciplina;

    @Column(nullable = false, length = 50)
    private String nomeDisciplina;

    @Column(nullable = false, length = 11)
    private String siglaDisciplina;

    @Column(nullable = false, length = 9)
    private String cargHorariaDisciplina;


}
