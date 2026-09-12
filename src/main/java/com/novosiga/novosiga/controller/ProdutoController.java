package com.novosiga.novosiga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novosiga.novosiga.model.Produto;
import com.novosiga.novosiga.service.ProdutoService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    // Injeção de dependencias da service de alunos
    @Autowired
    private ProdutoService produtoService;

    // Metodo para salvar um aluno
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto, @RequestParam("foto") MultipartFile foto) {
        try {
            if (!foto.isEmpty()) {
                produto.setFotoProduto(foto.getBytes());
                produto.setTipoFoto(foto.getContentType());
            } else if(produto.getIdProduto() != null) {
                Produto produtoExistente = produtoService.findById(produto.getIdProduto());
                if(produtoExistente != null){
                    produto.setFotoProduto(produtoExistente.getFotoProduto());
                    produto.setTipoFoto(produtoExistente.getTipoFoto());
                }
            }
            produtoService.save(produto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/produtos/listar";
    }

    // Metodo para listar produtos com busca, filtro por marca e ordenacao
    @GetMapping("/listar")
    public String listar(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String marca,
            @SortDefault(sort = "descricaoProduto") Sort sort,
            Model model) {
        List<Produto> produtos = produtoService.buscar(q, marca, sort);
        model.addAttribute("produtos", produtos);
        model.addAttribute("marcas", produtoService.findMarcas());
        model.addAttribute("q", q);
        model.addAttribute("marca", marca);
        return "produto/listarProdutos";
    }

    // Metodo para abrir o formulario para cadastro de aluno
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "produto/formularioProduto";
    }

    // Método para excluir um aluno pelo ID
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        produtoService.deleteById(id);
        return "redirect:/produtos/listar";
    }

    // Método para abrir o formulário de edição de alunos
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        Produto produto = produtoService.findById(id);
        model.addAttribute("produto", produto);
        return "produto/formularioProduto";
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]> foto(@PathVariable Integer id) {
        Produto produto = produtoService.findById(id);
        if (produto == null || produto.getFotoProduto() == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(produto.getTipoFoto())).body(produto.getFotoProduto());
    }
    

}
