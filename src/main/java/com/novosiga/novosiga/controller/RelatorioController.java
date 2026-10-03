package com.novosiga.novosiga.controller;

import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.novosiga.novosiga.model.Aluno;
import com.novosiga.novosiga.service.AlunoService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private SpringTemplateEngine templateEngine;

    //Tela HTML
    @GetMapping("/alunos")
    public String relatorioAlunos(Model model) {
        List<Aluno> alunos = alunoService.findAll();
        model.addAttribute("alunos", alunos);
        return "relatorio/relatorioAlunos";
    }

    @GetMapping("/alunos/pdf")
    public void gerarPdf(HttpServletResponse response) throws Exception {
        List<Aluno> alunos = alunoService.findAll();
        Context context = new Context();
        context.setVariable("alunos", alunos);

        String html = templateEngine.process("relatorio/relatorioAlunosPdf", context);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio.pdf");

        OutputStream os = response.getOutputStream();

        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(html, html);
        builder.toStream(os);
        builder.run();
        os.close();
    }


}
