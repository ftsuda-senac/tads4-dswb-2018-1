/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.tadsstorespring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fernando.tsuda
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

  @GetMapping
  public ModelAndView mostrarFormulario() {
    return new ModelAndView("upload-form");
  }

  @PostMapping
  public ModelAndView upload(
          @RequestParam("arquivo") MultipartFile arquivo) {

    if (arquivo.isEmpty()) {
      // ERRO
    }

    try {
      byte[] bytesArquivo = arquivo.getBytes();
      Path destino = Paths.get("C:/desenv/uploads/"
              + arquivo.getOriginalFilename());
      Files.write(destino, bytesArquivo);
    } catch (IOException e) {
      // ERRO
    }
    return new ModelAndView("redirect:/estaticos/uploads/"
            + arquivo.getOriginalFilename());
  }

}
