/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstorespring.entidade;

import java.util.Set;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author fernando.tsuda
 */
public class UsuarioSistema implements UserDetails {
  
  private String username;
  
  private String nomeCompleto;
  
  private String senha;
  
  private Set<Papel> papeis;

  public UsuarioSistema() {
  }

  public UsuarioSistema(String username, String nomeCompleto, String senha, Set<Papel> papeis) {
    this.username = username;
    this.nomeCompleto = nomeCompleto;
    this.senha = senha;
    this.papeis = papeis;
  }
  
  

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getNomeCompleto() {
    return nomeCompleto;
  }

  public void setNomeCompleto(String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
  }

  public String getSenha() {
    return senha;
  }

  public Set<Papel> getPapeis() {
    return papeis;
  }

  public void setPapeis(Set<Papel> papeis) {
    this.papeis = papeis;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  @Override
  //public Collection<? extends GrantedAuthority> getAuthorities() {
  public Set<Papel> getAuthorities() {
    return papeis;
  }

  @Override
  public String getPassword() {
    return senha;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
   return true;
  }
  
}
