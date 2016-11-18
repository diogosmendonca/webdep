/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.cefetrj.webdep.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.cefetrj.webdep.model.dao.GenericDAO;
import br.cefetrj.webdep.model.dao.PersistenceManager;
import br.cefetrj.webdep.model.entity.PadraoURL;
import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.model.entity.Servidor;
import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.model.entity.Usuario;
import br.cefetrj.webdep.services.SistemaServices;

/**
 *
 * @author Rian
 */
@WebServlet("/CadastroSistema")
public class CadastroSistema extends HttpServlet {

	private static final long serialVersionUID = 1L;
	PersistenceManager pm = PersistenceManager.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();

		// indicando a tabela pra sistemas no banco
		GenericDAO<Sistema> sistemaDAO = pm.createGenericDAO(Sistema.class);
		GenericDAO<Servidor> servidorDAO = pm.createGenericDAO(Servidor.class);
		pm.beginTransaction();
		List<Sistema> systemlist = sistemaDAO.listAll();
		pm.commitTransaction();
		String update = request.getParameter("update");
		String nome = request.getParameter("nome");
		String server = request.getParameter("servidor");
		String ptLogs = request.getParameter("ptLogs");
		String pxLogs = request.getParameter("pxLogs");
		String ptLogs2 = request.getParameter("ptLogs2");
		String pxLogs2 = request.getParameter("pxLogs2");
		String data = request.getParameter("data");
		String hora = request.getParameter("time");
		String nova = request.getParameter("novaData");
		String dataPrimeiraLeitura = data + " " + hora;
		String mensagem = "";
		try {
			Sistema system = new Sistema();
			/*
			 * Essa parte está incompleta. Faltam alguns atributos na classe
			 * Sistema e é necessário criar um cálculo para mostrar a
			 * periodicidade
			 */
			system.setNome(nome);
			system.setServidor(servidorDAO.listAll().get(0));
			system.setPastaLogAcesso(ptLogs);
			system.setPrefixoLogAcesso(pxLogs);
			system.setPrefixoLogErro(pxLogs2);
			system.setPastaLogErro(ptLogs2);
			system.setPeriodicidadeLeitura(Long.parseLong(new SimpleDateFormat("hh:mm").parse(nova).toString()));
			LocalDateTime primeiraLeit = LocalDateTime.parse(dataPrimeiraLeitura, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
			system.setPrimeiraLeitura(primeiraLeit);
			// system.setNovaLeitura();
			SistemaServices.insertSistema(system);
			mensagem = "Sistema cadastrado com sucesso!";
		} catch (Exception e) {
			mensagem = "Não foi possível cadastrar o sistema!";
		} finally {
			String json = "{\"mensagem\": \"" + mensagem + "\"]}";
			response.setContentType("application/json");
			pw.write(json);
			
		}
	}

}
