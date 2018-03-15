/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.exemplosspring.controller;

import br.senac.tads4.dsw.exemplosspring.modelo.Produto;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/exemplomv")
public class ExemploModelAndViewController {

  @GetMapping("/ex1")
  public ModelAndView executar1() {
    return new ModelAndView("exemplo1");
  }

  @GetMapping("/ex2")
  public ModelAndView executar2() {
    Produto p1 = new Produto(1L, "Bolo de chocolate",
            "descrição do bolo de chocolate",
            new BigDecimal("30.0"));
    Produto p2 = new Produto(1L, "Bolo de cenoura",
            "descrição do bolo de cenoura", new BigDecimal("20.0"));
    List<Produto> lista = Arrays.asList(p1, p2);

    return new ModelAndView("exemplo2")
            .addObject("listaAtrib", lista);
  }

  @GetMapping("/ex3")
  public ModelAndView executar3(@RequestParam(value = "nome", required = false) String nomeParam) {
    return new ModelAndView("exemplo3")
            .addObject("nomeInformado", nomeParam);
  }

  @GetMapping("/ex4/{nome}")
  public ModelAndView executar4(@PathVariable("nome") String nomeParam) {
    return new ModelAndView("exemplo4")
            .addObject("nomeInformado", nomeParam);
  }
}
