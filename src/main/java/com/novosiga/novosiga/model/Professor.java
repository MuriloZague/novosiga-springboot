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
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProfessor;

    @Column(nullable = false, length = 50)
    private String nomeProfessor;

    @Column(nullable = false, length = 11)
    private String cpfProfessor;

    @Column(nullable = false, length = 9)
    private String rgProfessor;

    @Column(nullable = false, length = 11)
    private String telefoneProfessor;

    @Column(nullable = false, length = 40)
    private String enderecoProfessor;

    @Column(nullable = false, length = 20)
    private String graduacaoProfessor;

}
