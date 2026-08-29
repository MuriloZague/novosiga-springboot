package com.novosiga.novosiga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novosiga.novosiga.model.Curso;
import com.novosiga.novosiga.repository.CursoRepository;

@Service
public class CursoService {

    //injeção de dependencia do repositorio de Cursos
    @Autowired
    private CursoRepository CursoRepository;

    //metodo para salvar um Curso
    public Curso save(Curso Curso){
        return CursoRepository.save(Curso);
    }

    //metodo para listar todos os Cursos
    public List<Curso> findAll(){
        return CursoRepository.findAll();
    }

    //metodo para excluir um Curso
    public void deleteById(Integer id){
        CursoRepository.deleteById(id);
    }

    //metodo para buscar o Curso pelo ID
    public Curso findById(Integer id){
        return CursoRepository.findById(id).orElse(null);
    }
}
