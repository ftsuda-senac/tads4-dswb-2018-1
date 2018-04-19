/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstorespring.controller;

import br.senac.tads4.dsw.tadsstorespring.entidade.Categoria;
import br.senac.tads4.dsw.tadsstorespring.entidade.ImagemProduto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import  br.senac.tads4.dsw.tadsstorespring.entidade.Produto;
import br.senac.tads4.dsw.tadsstorespring.service.ProdutoService;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/gerenciamento/produto")
public class GerenciadorProdutoController {
  
  @Autowired
  private ProdutoService service;
  
  @GetMapping("/formulario")
  public ModelAndView mostrarFormulario() {
    return new ModelAndView("/produto/formulario")
            .addObject("produto", new Produto());
  }
  
  @PostMapping("/salvar")
  public ModelAndView salvar(@ModelAttribute("produto") @Valid Produto produto,
          BindingResult bindingResult,
          RedirectAttributes redirectAttributes) {
    // @ModelAttribute - indica para o Spring que as informacoes preenchidas
    // no form HTML serao populadas no objeto produto
    // @Valid - indica que o objeto será validado de acordo com as configurações
    // feitas na classe Produto
    if (bindingResult.hasErrors()) {
      return new ModelAndView("/produto/formulario");
    }
    produto.setDtCadastro(new Date());
    
    // Setando categorias e imagens hardcoded
    Set<Categoria> categorias = new LinkedHashSet<>();
    Categoria c1 = new Categoria("Chocolate");
    c1.setProdutos(new LinkedHashSet<>(Arrays.asList(produto)));
    categorias.add(c1);
    
    Categoria c2 = new Categoria("Light");
    c2.setProdutos(new LinkedHashSet<>(Arrays.asList(produto)));
    categorias.add(c2);
    
    Categoria c3 = new Categoria("Crocante");
    c3.setProdutos(new LinkedHashSet<>(Arrays.asList(produto)));
    categorias.add(c3);
    produto.setCategorias(categorias);
    
    Set<ImagemProduto> imagens = new LinkedHashSet<>();
    ImagemProduto img1 = new ImagemProduto("bolo01.jpg", "Descrição 1");
    img1.setProduto(produto);
    imagens.add(img1);
    
    ImagemProduto img2 = new ImagemProduto("bolo02.jpg", "Descrição 2");
    img2.setProduto(produto);
    imagens.add(img2);
    
    ImagemProduto img3 = new ImagemProduto("bolo03.jpg", "Descrição 3");
    img3.setProduto(produto);
    imagens.add(img3);
    produto.setImagens(imagens);
    
    service.incluir(produto);
    redirectAttributes.addFlashAttribute("produtoCadastrado", produto);
    return new ModelAndView("redirect:/gerenciamento/produto/resultado");
  }
  
  @GetMapping("/resultado")
  public ModelAndView mostrarResultado() {
    return new ModelAndView("/produto/resultado");
  }
}
