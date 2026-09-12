package com.novosiga.novosiga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novosiga.novosiga.model.Aluno;
import com.novosiga.novosiga.repository.AlunoRepository;

@Service
public class AlunoService {

    //injeção de dependencia do repositorio de alunos
    @Autowired
    private AlunoRepository alunoRepository;

    //metodo para salvar um aluno
    public Aluno save(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    //metodo para listar todos os alunos
    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    //metodo para buscar alunos com busca por texto, filtro por curso e ordenacao
    //transacao readOnly necessaria para ler o LOB (fotoAluno) no Postgres
    @Transactional(readOnly = true)
    public List<Aluno> buscar(String q, Integer cursoId, Sort sort){
        q = (q == null) ? "" : q.trim();
        return alunoRepository.buscar(q, cursoId, sort);
    }

    //metodo para excluir um aluno
    public void deleteById(Integer id){
        alunoRepository.deleteById(id);
    }

    //metodo para buscar o aluno pelo ID
    public Aluno findById(Integer id){
        return alunoRepository.findById(id).orElse(null);
    }
}
