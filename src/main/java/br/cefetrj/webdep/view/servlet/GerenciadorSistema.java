/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.webdep.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Sistema;

/**
 *
 * @author Luan
 */
@WebServlet("/GerenciadorSistema")
public class GerenciadorSistema extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gerenciadorsistema.jsp");
        dispatcher.forward(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String action = request.getParameter("action");
    	String filtro = request.getParameter("filtro");
    	PrintWriter pw = response.getWriter();
    	PersistenceManager pm = PersistenceManager.getInstance(); 
        GenericDAO<Sistema> sistemaDAO = pm.createGenericDAO(Sistema.class);
        
        
    	switch(action){
    	case "buscaSistema":
    		List<Sistema> sistemas = sistemaDAO.listAll();
            List<Sistema> sistemasFiltrados = new ArrayList<>();
            for (Sistema s: sistemas) {
            	if (s.getNome().contains(filtro)) {
            		sistemasFiltrados.add(s);
            	}
            }
            String json = "";
            if (sistemasFiltrados.size() != 0){
            	json = "{\"sistemas\": [";
            	for (Sistema s: sistemasFiltrados) {
            		json = "{";
            		json = "\"nome\":\"" + s.getNome() + "\",";
            		json = "\"servidor\":\"" + s.getServidor().getNome() + "\",";
            		json = "\"formatolog\":\"" + s.getServidor().getFormatoLog().getNome() + "\",";
            		json = "\"periodicidade\":\"" + s.getPeriodicidadeLeitura().toString() + "\",";
            		json = "\"proximaleitura\":\"" + "última leitura + periodicidade" + "\""; 
            		json = "},";
            	}
            	json = "]}";
            	json = json.replace("},]}", "}]}");
            } else {
            	json = "{\"Erro\": \"Nenhum resultado encontrado\"}";
            }
            pw.write(json);
    		break;
    	}
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
