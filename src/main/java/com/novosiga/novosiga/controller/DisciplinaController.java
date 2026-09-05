package com.novosiga.novosiga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novosiga.novosiga.model.Curso;
import com.novosiga.novosiga.model.Disciplina;
import com.novosiga.novosiga.model.Professor;
import com.novosiga.novosiga.service.CursoService;
import com.novosiga.novosiga.service.DisciplinaService;
import com.novosiga.novosiga.service.ProfessorService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    // Injeção de dependencias da service de disciplinas
    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CursoService cursoService;

    // Metodo para salvar uma disciplina
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Disciplina disciplina) {
        disciplinaService.save(disciplina);
        return "redirect:/disciplinas/listar";
    }

    // Metodo para listar todas as disciplinas
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Disciplina> disciplinas = disciplinaService.findAll();
        model.addAttribute("disciplinas", disciplinas);
        return "disciplina/listarDisciplinas";
    }

    // Metodo para abrir o formulario para cadastro de disciplina
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        List<Professor> professores = professorService.findAll();
        model.addAttribute("professores", professores);
        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);
        return "disciplina/formularioDisciplina";
    }

    // Método para excluir uma disciplina pelo ID
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        disciplinaService.deleteById(id);
        return "redirect:/disciplinas/listar";
    }

    // Método para abrir o formulário de edição de disciplina
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Disciplina disciplina = disciplinaService.findById(id);
        model.addAttribute("disciplina", disciplina);
        List<Professor> professores = professorService.findAll();
        model.addAttribute("professores", professores);
        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);
        return "disciplina/formularioDisciplina";
    }

}
