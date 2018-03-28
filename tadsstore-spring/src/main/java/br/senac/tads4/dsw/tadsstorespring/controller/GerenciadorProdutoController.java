/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstorespring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import  br.senac.tads4.dsw.tadsstorespring.entidade.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/gerenciamento/produto")
public class GerenciadorProdutoController {
  
  @GetMapping("/formulario")
  public ModelAndView mostrarFormulario() {
    return new ModelAndView("/produto/formulario")
            .addObject("produto", new Produto());
  }
  
  @PostMapping("/salvar")
  public ModelAndView salvar(@ModelAttribute Produto produto) {
    // @ModelAttribute - indica para o Spring que as informacoes preenchidas
    // no form HTML serao populadas no objeto produto
    return new ModelAndView("/produto/resultado")
            .addObject("produtoCadastrado", produto);
  }
}
