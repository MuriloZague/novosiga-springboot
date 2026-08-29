package com.novosiga.novosiga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novosiga.novosiga.model.Professor;
import com.novosiga.novosiga.repository.ProfessorRepository;

@Service
public class ProfessorService {

    //injeção de dependencia do repositorio de Professors
    @Autowired
    private ProfessorRepository ProfessorRepository;

    //metodo para salvar um Professor
    public Professor save(Professor Professor){
        return ProfessorRepository.save(Professor);
    }

    //metodo para listar todos os Professors
    public List<Professor> findAll(){
        return ProfessorRepository.findAll();
    }

    //metodo para excluir um Professor
    public void deleteById(Integer id){
        ProfessorRepository.deleteById(id);
    }

    //metodo para buscar o Professor pelo ID
    public Professor findById(Integer id){
        return ProfessorRepository.findById(id).orElse(null);
    }
}
