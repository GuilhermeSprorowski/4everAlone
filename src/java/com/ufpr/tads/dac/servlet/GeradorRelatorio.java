/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author MURILO
 */
@WebServlet(name = "GeradorRelatorio", urlPatterns = {"/GeradorRelatorio"})
public class GeradorRelatorio extends HttpServlet {

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

        HttpSession session = request.getSession();
        UserBean login = (UserBean) session.getAttribute("user");
        if (login == null) {
            //envia para fazer login
            request.setAttribute("msg", "É necessario esta logado para acessar essa pagina");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            Connection con = null;
            Date dataC = null, dataF = null;
            int idUsuario = 0, acao = 0;
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String jasper = request.getContextPath() + "/jasper/Teste.jasper";
            HashMap params = new HashMap();
            String host = "http://" + request.getServerName()
                    + ":" + request.getServerPort();
            
            // MUDAR quando existir jsp
            String dataComeco = request.getParameter("dataComeco") == null ? "":(String)request.getParameter("dataComeco");
            String dataFinal = request.getParameter("dataFinal") == null ? "":(String)request.getParameter("dataFinal");       
            idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            acao = Integer.parseInt(request.getParameter("acao"));
            //

            try {
                dataC = dateFormat.parse(dataComeco);
                dataF = dateFormat.parse(dataFinal);
            } catch (ParseException ex) {
                System.out.println(ex);
            }
            if (acao == 1 || acao == 0) {
                //url do report
                jasper = request.getContextPath() + "/jasper/GerencialEncontro.jasper";
                // parametros de escolha do funcionario
                params.put("frm_date", dataC);
                params.put("to_date", dataF);
            } else if (acao == 2) {
                //url do report
                jasper = request.getContextPath() + "/jasper/GerencialEncontroPessoa.jasper";
                //url parametro dos subreport
                String subreport = request.getContextPath() + "/jasper/nomereport.jasper";
                params.put("nomereport", host + subreport);
                // parametros de escolha do funcionario
                params.put("frm_date", dataC);
                params.put("to_date", dataF);
                params.put("idPessoa", idUsuario);
            } else if (acao == 3) {
                //url do report
                jasper = request.getContextPath() + "/jasper/GerencialFesta.jasper";
                // parametros de escolha do funcionario
                params.put("frm_date", dataC);
                params.put("to_date", dataF);
            } else if (acao == 4) {
                //url do report
                jasper = request.getContextPath() + "/jasper/GerencialFestaPessoa.jasper";
                // parametros de escolha do funcionario
                params.put("frm_date", dataC);
                params.put("to_date", dataF);
                params.put("idPessoa", idUsuario);
                //url parametro dos subreport
                String subreport = request.getContextPath() + "/jasper/nomereport.jasper";
                params.put("nomereport", host + subreport);
                subreport = request.getContextPath() + "/jasper/somareport.jasper";
                params.put("somareport", host + subreport);
            } else if (acao == 5) {
                //url do report
                jasper = request.getContextPath() + "/jasper/GerencialProdutividade.jasper";
                // parametros de escolha do funcionario
                params.put("frm_date", dataC);
                params.put("to_date", dataF);
                params.put("idPessoa", idUsuario);
                //url parametro dos subreport
                String subreport = request.getContextPath() + "/jasper/casamentoanual.jasper";
                params.put("casamentoanual", host + subreport);
                subreport = request.getContextPath() + "/jasper/casamentomes.jasper";
                params.put("casamentomes", host + subreport);
            }
            try {
                con = new ConnectionFactory().getConnection();
                // URL para acesso ao relatório
                URL jasperURL = new URL(host + jasper);
                byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);

                if (bytes != null) {
// A página será mostrada em PDF
                    response.setContentType("application/pdf");
// Envia o PDF para o Cliente
                    OutputStream ops = response.getOutputStream();
                    ops.write(bytes);
                }
            } catch (SQLException e) {
                System.out.println(e);
                request.setAttribute("mensagem", "Erro de conexão ou query: "
                        + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } catch (JRException e) {
                System.out.println(e);
                request.setAttribute("mensagem", "Erro no Jasper : "
                        + e.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                }

            }
        } // Fechamento do processRequest
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
        processRequest(request, response);
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
