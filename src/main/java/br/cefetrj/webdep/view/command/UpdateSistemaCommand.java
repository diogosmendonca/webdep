package br.cefetrj.webdep.view.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.webdep.model.entity.Sistema;
import br.cefetrj.webdep.services.ServidorServices;
import br.cefetrj.webdep.services.SistemaServices;

public class UpdateSistemaCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			PrintWriter pw = response.getWriter();
			
			Sistema s = new Sistema();
			String id = request.getParameter("id_sistema_update");
			s = SistemaServices.obterPorId(Long.parseLong(id));
			String nome = request.getParameter("nome");
			String server = request.getParameter("servidor");
			String ptLogs = request.getParameter("ptLogs");
			String pxLogs = request.getParameter("pxLogs");
			String ptLogs2 = request.getParameter("ptLogs2");
			String pxLogs2 = request.getParameter("pxLogs2");
			String nova = request.getParameter("novaData");
			LocalDate ld = LocalDate.parse(request.getParameter("data"));
			LocalTime lt = LocalTime.parse(request.getParameter("time"));
			LocalDateTime l = LocalDateTime.of(ld, lt);
			String mensagem = "";
		try{	
			s.setNome(nome);
			s.setServidor(ServidorServices.searchServidor(server).get(0));
			s.setPastaLogAcesso(ptLogs);
			s.setPrefixoLogAcesso(pxLogs);
			s.setPrefixoLogErro(pxLogs2);
			s.setPastaLogErro(ptLogs2);
			s.setPrimeiraLeitura(l);
			s.setPeriodicidadeLeitura(new SimpleDateFormat("hh:mm").parse(nova).getTime());
			SistemaServices.updateSistema(s);
			
			mensagem = "Sistema atualizado com sucesso!";
		} catch (Exception e) {
			mensagem = "Não foi possível cadastrar o sistema!";
			e.printStackTrace();
		} finally {
			String json = "{\"mensagem\": \"" + mensagem + "\"}";
			pw.write(json);
		}
	}
}