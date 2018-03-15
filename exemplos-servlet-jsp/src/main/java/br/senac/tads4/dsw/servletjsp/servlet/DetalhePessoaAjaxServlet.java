/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.dsw.servletjsp.servlet;

import br.senac.tads4.dsw.servletjsp.modelo.Pessoa;
import br.senac.tads4.dsw.servletjsp.service.PessoaService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Fernando
 */
@WebServlet(name = "DetalhePessoaAjaxServlet", urlPatterns = {"/detalhe-pessoa-ajax"})
public class DetalhePessoaAjaxServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {

    PessoaService service = new PessoaService();

    String idStr = request.getParameter("id");
    if (idStr != null && idStr.trim().length() > 0) {
      long id = Long.parseLong(idStr);
      Pessoa pessoa = service.obter(id);
      if (pessoa != null) {
	// Preparar resposta
	JSONObject json = new JSONObject(pessoa);
	response.setContentType("application/json");
	response.setCharacterEncoding("utf-8");
	//response.addHeader("Access-Control-Allow-Origin", "*");
	try (PrintWriter out = response.getWriter()) {
	  out.print(json.toString());
	}
      } else {
	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      }
    }
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }

}
