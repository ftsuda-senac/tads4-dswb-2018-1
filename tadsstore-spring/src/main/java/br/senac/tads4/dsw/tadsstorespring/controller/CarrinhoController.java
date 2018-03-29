/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstorespring.controller;

import java.io.Serializable;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.senac.tads4.dsw.tadsstorespring.entidade.Produto;
import br.senac.tads4.dsw.tadsstorespring.service.ProdutoService;
import br.senac.tads4.dsw.tadsstorespring.service.fakeimpl.ProdutoServiceFakeImpl;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/carrinho")
@Scope("session")
public class CarrinhoController implements Serializable {

  private List<Produto> produtosAdicionados = new ArrayList<Produto>();

  private ProdutoService servico = new ProdutoServiceFakeImpl();

  @PostMapping("/{id}")
  public ModelAndView adicionarItem(@PathVariable("id") Long idProduto,
          RedirectAttributes redirectAttributes) {

    Produto p = servico.obter(idProduto);
    produtosAdicionados.add(p);

    redirectAttributes.addFlashAttribute("produto", p);
    return new ModelAndView("redirect:/carrinho");
  }

  @GetMapping
  public ModelAndView visualizar() {
    return new ModelAndView("/compra/carrinho");
  }

  public List<Produto> getProdutosAdicionados() {
    return produtosAdicionados;
  }

}
