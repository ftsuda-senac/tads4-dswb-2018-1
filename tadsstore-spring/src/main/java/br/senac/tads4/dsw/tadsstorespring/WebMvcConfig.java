/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstorespring;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 *
 * @author fernando.tsuda
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter 
        implements WebMvcConfigurer {
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/estaticos/**")
            .addResourceLocations("file:C:/desenv/uploads");
  }

  @Bean(name = "localeResolver")
  public CookieLocaleResolver localeResolver() {
    CookieLocaleResolver clr = new CookieLocaleResolver();
    clr.setDefaultLocale(new Locale("pt", "BR"));
    return clr;
  }
  
  @Bean(name = "localeChangeInterceptor")
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }
  
  @Bean(name = "messageSource")
  public ReloadableResourceBundleMessageSource messageSource() {
    ReloadableResourceBundleMessageSource source = 
            new ReloadableResourceBundleMessageSource();
    source.setBasename("classpath:i18n/mensagens");
    source.setDefaultEncoding("UTF-8");
    source.setCacheSeconds(0);
    return source;
  }
  
  @Bean(name = "validator")
  public LocalValidatorFactoryBean validator() {
    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource());
    return bean;
  }

}
