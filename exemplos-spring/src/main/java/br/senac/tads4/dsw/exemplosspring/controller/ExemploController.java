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

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/exemplo")
public class ExemploController {

  @GetMapping("/ex1")
  public String executar1() {
    return "exemplo1";
  }

  @GetMapping("/ex2")
  public String executar2(Model modelo) {
    Produto p1 = new Produto(1L, "Bolo de chocolate",
            "descrição do bolo de chocolate",
            new BigDecimal("30.0"));
    Produto p2 = new Produto(1L, "Bolo de cenoura",
            "descrição do bolo de cenoura", new BigDecimal("20.0"));
    List<Produto> lista = Arrays.asList(p1, p2);

    modelo.addAttribute("listaAtrib", lista);
    return "exemplo2";
  }
  
  @GetMapping("/ex3")
  public String executar3(@RequestParam(value = "nome", required = false) String nomeParam, 
          Model modelo) {
    modelo.addAttribute("nomeInformado", nomeParam);
    return "exemplo3";
  }
  
  @GetMapping("/ex4/{nome}")
  public String executar4(@PathVariable("nome") String nomeParam,
          Model modelo) {
    modelo.addAttribute("nomeInformado", nomeParam);
    return "exemplo4";
  }

}
