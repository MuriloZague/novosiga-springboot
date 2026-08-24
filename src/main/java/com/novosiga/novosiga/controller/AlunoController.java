package com.novosiga.novosiga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novosiga.novosiga.model.Aluno;
import com.novosiga.novosiga.service.AlunoService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    //Injeção de dependencias da service de alunos
    @Autowired
    private AlunoService alunoService;

    //Metodo para salvar um aluno
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Aluno aluno) {
        alunoService.save(aluno);
        return "redirect:/alunos/listar";
    }
    
    //Metodo para listar todos os alunos
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Aluno> alunos = alunoService.findAll();
        model.addAttribute("alunos", alunos);
        return "aluno/listaAlunos";
    }
    
    //Metodo para abrir o formulario para cadastro de aluno
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "aluno/formularioAluno";
    }
    

}
