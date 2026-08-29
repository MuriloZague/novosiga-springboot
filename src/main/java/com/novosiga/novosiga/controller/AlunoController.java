package com.novosiga.novosiga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novosiga.novosiga.model.Aluno;
import com.novosiga.novosiga.service.AlunoService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    // Injeção de dependencias da service de alunos
    @Autowired
    private AlunoService alunoService;

    // Metodo para salvar um aluno
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Aluno aluno, @RequestParam("foto") MultipartFile foto) {
        try {
            if (!foto.isEmpty()) {
                aluno.setFotoAluno(foto.getBytes());
                aluno.setTipoFoto(foto.getContentType());
            } else if(aluno.getIdAluno() != null) {
                Aluno alunoExistente = alunoService.findById(aluno.getIdAluno());
                if(alunoExistente != null){
                    aluno.setFotoAluno(alunoExistente.getFotoAluno());
                    aluno.setTipoFoto(alunoExistente.getTipoFoto());
                }
            }
            alunoService.save(aluno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/alunos/listar";
    }

    // Metodo para listar todos os alunos
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Aluno> alunos = alunoService.findAll();
        model.addAttribute("alunos", alunos);
        return "aluno/listarAlunos";
    }

    // Metodo para abrir o formulario para cadastro de aluno
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "aluno/formularioAluno";
    }

    // Método para excluir um aluno pelo ID
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        alunoService.deleteById(id);
        return "redirect:/alunos/listar";
    }

    // Método para abrir o formulário de edição de alunos
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Aluno aluno = alunoService.findById(id);
        model.addAttribute("aluno", aluno);
        return "aluno/formularioAluno";
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]> foto(@PathVariable Integer id) {
        Aluno aluno = alunoService.findById(id);
        if (aluno == null || aluno.getFotoAluno() == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(aluno.getTipoFoto())).body(aluno.getFotoAluno());
    }
    

}
