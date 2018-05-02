/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstorespring.repository;

import br.senac.tads4.dsw.tadsstorespring.entidade.Produto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fernando.tsuda
 */
//@Repository
public interface ProdutoRepository
        extends PagingAndSortingRepository<Produto, Long> {
  
  List<Produto> findByNomeStartingWith(String nome);

}
