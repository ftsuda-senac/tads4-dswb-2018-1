/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstorespring.restcontroller;

import br.senac.tads4.dsw.tadsstorespring.entidade.Categoria;
import br.senac.tads4.dsw.tadsstorespring.entidade.Produto;
import br.senac.tads4.dsw.tadsstorespring.service.CategoriaService;
import br.senac.tads4.dsw.tadsstorespring.service.ProdutoService;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fernando.tsuda
 */
@RestController
@RequestMapping("/api/produto")
public class ProdutoRestController {

  @Autowired
  private ProdutoService servico;

  @Autowired
  private CategoriaService categoriaService;

  @GetMapping("/all")
  @CrossOrigin(origins = "*")
  public List<Produto> listar() {
    return servico.listar(0, 100);
  }

  @GetMapping(value = "/{id}")
  public Produto detalhe(@PathVariable("id") long id) {
    return servico.obter(id);
  }

  @PostMapping(consumes = {"application/json"})
  public Produto incluir(@RequestBody Produto produto) {
    produto.setDtCadastro(new Date());
    Set<Produto> listaProdutos = new LinkedHashSet<>();
    listaProdutos.add(produto);

    Set<Categoria> listaCategorias = new LinkedHashSet<>();

    if (produto.getIdsCategorias() != null && !produto.getIdsCategorias().isEmpty()) {
      for (Integer id : produto.getIdsCategorias()) {
        Categoria c = categoriaService.obter(id);
        c.setProdutos(listaProdutos);
        listaCategorias.add(c);
      }
    }
    produto.setCategorias(listaCategorias);
    servico.incluir(produto);
    return produto;
  }

}
