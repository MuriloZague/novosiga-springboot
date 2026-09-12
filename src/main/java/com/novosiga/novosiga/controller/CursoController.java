package com.novosiga.novosiga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novosiga.novosiga.model.Curso;
import com.novosiga.novosiga.service.CursoService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    //Injeção de dependencias da service de cursos
    @Autowired
    private CursoService cursoService;

    //Metodo para salvar um curso
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Curso curso) {
        cursoService.save(curso);
        return "redirect:/cursos/listar";
    }

    //Metodo para listar cursos com busca e ordenacao
    @GetMapping("/listar")
    public String listar(
            @RequestParam(required = false) String q,
            @SortDefault(sort = "nomeCurso") Sort sort,
            Model model) {
        List<Curso> cursos = cursoService.buscar(q, sort);
        model.addAttribute("cursos", cursos);
        model.addAttribute("q", q);
        return "curso/listarCursos";
    }

    //Metodo para abrir o formulario para cadastro de curso
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("curso", new Curso());
        return "curso/formularioCurso";
    }

    //Método para excluir um curso pelo ID
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        cursoService.deleteById(id);
        return "redirect:/cursos/listar";
    }

    //Método para abrir o formulário de edição de curso
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Curso curso = cursoService.findById(id);
        model.addAttribute("curso", curso);
        return "curso/formularioCurso";
    }

}
