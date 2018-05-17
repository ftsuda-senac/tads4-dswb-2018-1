/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstorespring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author fernando.tsuda
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  
  private static PasswordEncoder basicPasswordEncoder() {
    return new PasswordEncoder() {
      @Override
      public String encode(CharSequence cs) {
        return cs.toString();
      }

      @Override
      public boolean matches(CharSequence cs, String string) {
        return string.equals(cs.toString());
      }
    };
  }
  
  public static PasswordEncoder bcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return bcryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
          .antMatchers("/css/**", "/js/**", "/img/**", "/api/**").permitAll()
          .antMatchers("/xpto/**").hasRole("FODAO")
          .antMatchers("/**").authenticated()
        .and()
          .formLogin()
            .loginPage("/login")
            .usernameParameter("username")
            .passwordParameter("senha")
            .defaultSuccessUrl("/").permitAll()
        .and()
          .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .invalidateHttpSession(true).deleteCookies("JSESSIONID");

  }

}
