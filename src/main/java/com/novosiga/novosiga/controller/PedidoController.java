package com.novosiga.novosiga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novosiga.novosiga.model.Aluno;
import com.novosiga.novosiga.model.Pedido;
import com.novosiga.novosiga.model.Produto;
import com.novosiga.novosiga.service.AlunoService;
import com.novosiga.novosiga.service.PedidoService;
import com.novosiga.novosiga.service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired 
    private PedidoService pedidoService;

    @Autowired 
    private AlunoService alunoService;

    @Autowired 
    private ProdutoService produtoService;

    //Endpoint para salvar o pedido(JSON usado pelo fetch)
    @PostMapping 
    @ResponseBody
    public Pedido salvarPedido(@RequestBody Pedido pedido){
        return pedidoService.salvarPedido(pedido);
    }

    //Abrir a tela de cadastro
    @GetMapping("/criar")
    public String criarForm(Model model) {
        //objeto vazio pedido
        model.addAttribute("pedido", new Pedido());

        //listar alunos
        List<Aluno> alunos = alunoService.findAll();
        model.addAttribute("alunos", alunos);

        //listar produtos
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);

        return "pedido/formularioPedido";
    }
    

}
