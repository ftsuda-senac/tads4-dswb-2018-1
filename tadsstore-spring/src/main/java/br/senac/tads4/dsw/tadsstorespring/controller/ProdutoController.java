/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstorespring.controller;

import br.senac.tads4.dsw.tadsstorespring.entidade.Categoria;
import br.senac.tads4.dsw.tadsstorespring.entidade.Produto;
import br.senac.tads4.dsw.tadsstorespring.service.ProdutoService;
import br.senac.tads4.dsw.tadsstorespring.service.fakeimpl.ProdutoServiceFakeImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping
public class ProdutoController {

  @Autowired
  private ProdutoService servico;

  @GetMapping
  public ModelAndView listar() {
    List<Produto> produtos = servico.listar(0, 100);
    return new ModelAndView("produto/lista2").addObject("produtos", produtos);
  }

  @GetMapping("/cat/{nome}")
  public ModelAndView listarPorCategoria(
          @PathVariable("nome") String nomeCategoria,
          @RequestParam(value = "offset", defaultValue = "0") int offset,
          @RequestParam(value = "qt", defaultValue = "100") int quantidade) {
    Categoria cat = new Categoria(nomeCategoria);
    List<Produto> produtos = 
            servico.listarPorCategoria(cat, offset, quantidade);
    return new ModelAndView("produto/lista2").addObject("produtos", produtos);
  }

  @GetMapping("/{id}")
  public ModelAndView detalhe(@PathVariable("id") long id) {
    Produto produto = servico.obter(id);
    return new ModelAndView("produto/detalhe").addObject("produto", produto);
  }

}
