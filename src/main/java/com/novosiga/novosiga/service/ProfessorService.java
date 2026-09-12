package com.novosiga.novosiga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //metodo para buscar professores com busca por texto, filtro por graduacao e ordenacao
    //transacao readOnly necessaria para ler o LOB (fotoProfessor) no Postgres
    @Transactional(readOnly = true)
    public List<Professor> buscar(String q, String graduacao, Sort sort){
        q = (q == null) ? "" : q.trim();
        graduacao = (graduacao == null) ? "" : graduacao.trim();
        return ProfessorRepository.buscar(q, graduacao, sort);
    }

    //metodo para listar as graduacoes distintas (dropdown de filtro)
    public List<String> findGraduacoes(){
        return ProfessorRepository.findGraduacoes();
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
