/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstorespring.service;

import br.senac.tads4.dsw.tadsstorespring.SecurityConfig;
import br.senac.tads4.dsw.tadsstorespring.entidade.Papel;
import br.senac.tads4.dsw.tadsstorespring.entidade.UsuarioSistema;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author fernando.tsuda
 */
@Service
public class UsuarioService implements UserDetailsService {

  private static final Map<String, UsuarioSistema> USUARIOS;

  static {
    USUARIOS = new LinkedHashMap<>();

    UsuarioSistema u1 = new UsuarioSistema("fulano", "Fulano da Silva", 
            SecurityConfig.bcryptPasswordEncoder().encode("abcd1234"),
            new LinkedHashSet<>(Arrays.asList(new Papel("ROLE_RALE"))));
    USUARIOS.put(u1.getUsername(), u1);

    UsuarioSistema u2 = new UsuarioSistema("ciclana", "Ciclana de Souza", 
            SecurityConfig.bcryptPasswordEncoder().encode("abcd1234"),
            new LinkedHashSet<>(Arrays.asList(new Papel("ROLE_FODAO"))));
    USUARIOS.put(u2.getUsername(), u2);
    
    UsuarioSistema u3 = new UsuarioSistema("beltrano", "Beltrano Rocha", 
            SecurityConfig.bcryptPasswordEncoder().encode("abcd1234"),
            new LinkedHashSet<>(Arrays.asList(new Papel("ROLE_GOD"))));
    USUARIOS.put(u3.getUsername(), u3);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return USUARIOS.get(username);
  }

}
