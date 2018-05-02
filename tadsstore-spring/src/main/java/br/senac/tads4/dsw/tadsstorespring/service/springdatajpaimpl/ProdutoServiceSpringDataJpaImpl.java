/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstorespring.service.springdatajpaimpl;

import br.senac.tads4.dsw.tadsstorespring.entidade.Categoria;
import br.senac.tads4.dsw.tadsstorespring.entidade.Produto;
import br.senac.tads4.dsw.tadsstorespring.repository.ProdutoRepository;
import br.senac.tads4.dsw.tadsstorespring.service.ProdutoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author fernando.tsuda
 */
//@Service
public class ProdutoServiceSpringDataJpaImpl implements ProdutoService {

  @Autowired
  private ProdutoRepository repositorio;

  @Override
  public List<Produto> listar(int offset, int quantidade) {
//    Page<Produto> pagina
//            = repositorio.findAll(new PageRequest(0, quantidade));
//    return pagina.getContent();
    return repositorio.findByNomeStartingWith("Bolo%");
  }

  @Override
  public List<Produto> listarPorCategoria(Categoria categoria, int offset, int quantidade) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Produto obter(long idProduto) {
    Optional<Produto> resultado = repositorio.findById(idProduto);

    if (resultado.isPresent()) {
      return resultado.get();
    }
    return null;
  }

  @Override
  public void incluir(Produto p) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void alterar(Produto p) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void remover(long idProduto) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
