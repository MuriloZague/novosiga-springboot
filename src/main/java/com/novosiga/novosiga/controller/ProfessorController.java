package com.novosiga.novosiga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novosiga.novosiga.model.Professor;
import com.novosiga.novosiga.service.ProfessorService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    //Injeção de dependencias da service de professores
    @Autowired
    private ProfessorService professorService;

    //Metodo para salvar um professor
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Professor professor, @RequestParam("foto") MultipartFile foto) {
        try {
            if (!foto.isEmpty()) {
                professor.setFotoProfessor(foto.getBytes());
                professor.setTipoFoto(foto.getContentType());
            } else if(professor.getIdProfessor() != null) {
                Professor professorExistente = professorService.findById(professor.getIdProfessor());
                if(professorExistente != null){
                    professor.setFotoProfessor(professorExistente.getFotoProfessor());
                    professor.setTipoFoto(professorExistente.getTipoFoto());
                }
            }
            professorService.save(professor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/professores/listar";
    }

    //Metodo para listar todos os professores
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Professor> professores = professorService.findAll();
        model.addAttribute("professores", professores);
        return "professor/listarProfessores";
    }

    //Metodo para abrir o formulario para cadastro de professor
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor/formularioProfessor";
    }

    //Método para excluir um professor pelo ID
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        professorService.deleteById(id);
        return "redirect:/professores/listar";
    }

    //Método para abrir o formulário de edição de professor
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Professor professor = professorService.findById(id);
        model.addAttribute("professor", professor);
        return "professor/formularioProfessor";
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]> foto(@PathVariable Integer id) {
        Professor professor = professorService.findById(id);
        if (professor == null || professor.getFotoProfessor() == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(professor.getTipoFoto())).body(professor.getFotoProfessor());
    }

}
