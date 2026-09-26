package com.novosiga.novosiga.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Usuario {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;
    
    @Column(nullable = false, length = 40)
    private String nomeUsuario;

    @Column(nullable = false, length = 11)
    private String cpfUsuario;

    @Column(nullable = false, length = 30)
    private String loginUsuario;

    @Column(nullable = false, length = 150)
    private String senhaUsuario;

    private String role = "ROLE_USER";
    
}
