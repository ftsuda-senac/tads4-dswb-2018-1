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
import javax.validation.Valid;
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
    
    redirectAttributes.addFlashAttribute("produtoCadastrado", produto);
    return new ModelAndView("redirect:/gerenciamento/produto/resultado");
  }
  
  @GetMapping("/resultado")
  public ModelAndView mostrarResultado() {
    return new ModelAndView("/produto/resultado");
  }
}
