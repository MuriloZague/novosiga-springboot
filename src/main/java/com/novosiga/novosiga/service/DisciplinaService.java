package com.novosiga.novosiga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novosiga.novosiga.model.Disciplina;
import com.novosiga.novosiga.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

    //injeção de dependencia do repositorio de Disciplinas
    @Autowired
    private DisciplinaRepository DisciplinaRepository;

    //metodo para salvar um Disciplina
    public Disciplina save(Disciplina Disciplina){
        return DisciplinaRepository.save(Disciplina);
    }

    //metodo para listar todos os Disciplinas
    public List<Disciplina> findAll(){
        return DisciplinaRepository.findAll();
    }

    //metodo para excluir um Disciplina
    public void deleteById(Integer id){
        DisciplinaRepository.deleteById(id);
    }

    //metodo para buscar o Disciplina pelo ID
    public Disciplina findById(Integer id){
        return DisciplinaRepository.findById(id).orElse(null);
    }
}
