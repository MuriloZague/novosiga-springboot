package com.novosiga.novosiga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.novosiga.novosiga.model.Usuario;
import com.novosiga.novosiga.repository.UsuarioRepository;

@Service 
public class UsuarioService {
    
    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    public Usuario save(Usuario usuario){
        //Criptografar a senha antes de salvar o usuario
        usuario.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }
}
