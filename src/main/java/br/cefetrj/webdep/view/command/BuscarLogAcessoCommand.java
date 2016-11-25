package br.cefetrj.webdep.view.command;

import br.cefetrj.webdep.model.entity.RegistroLogAcesso;
import br.cefetrj.webdep.services.LogAcessoServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by renatoor on 11/25/16.
 */
public class BuscarLogAcessoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean erro = false;

        LocalDate dataI, dataF;
        LocalTime horaI, horaF;
        LocalDateTime ldtInicial, ldtFinal;

        String dataInicial = request.getParameter("dataInicial");
        String dataFinal = request.getParameter("dataFinal");
        String horaInicial = request.getParameter("horaInicial");
        String horaFinal = request.getParameter("horaFinal");
        String buscarLog = request.getParameter("buscarLog");

        request.setAttribute("dataInicial", dataInicial);
        request.setAttribute("dataFinal", dataFinal);
        request.setAttribute("horaInicial", horaInicial);
        request.setAttribute("horaFinal", horaFinal);

        try {
            dataI = LocalDate.parse(dataInicial);
            dataF = LocalDate.parse(dataFinal);
        } catch (Exception e) {
            dataI = null;
            dataF = null;
            request.setAttribute("dataInvalida", "1");
            erro = true;
        }

        try{
            horaI = LocalTime.parse(horaInicial);
            horaF = LocalTime.parse(horaFinal);
        } catch (Exception e) {
            horaI = null;
            horaF = null;
            request.setAttribute("horaInvalida", "1");
            erro = true;
        }

        if(erro) {
            request.getRequestDispatcher("/excluirLogAcesso.jsp").forward(request, response);
        }

        ldtInicial = LocalDateTime.of(dataI, horaI);
        ldtFinal = LocalDateTime.of(dataF, horaF);

        List<RegistroLogAcesso> bdLog = LogAcessoServices.buscarLog(ldtInicial, ldtFinal, buscarLog);

        if (bdLog.isEmpty()) {
            request.setAttribute("naoEncontrado", "1");
            request.getRequestDispatcher("/excluirLogAcesso.jsp").forward(request, response);
        }
        else {
            request.setAttribute("listaLog", bdLog);
            request.getRequestDispatcher("/excluirLogAcesso.jsp").forward(request, response);
        }
    }
}