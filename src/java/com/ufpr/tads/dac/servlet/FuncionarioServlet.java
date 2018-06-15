/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.CidadeBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.exceptions.EstadoException;
import com.ufpr.tads.dac.exceptions.FuncionarioException;
import com.ufpr.tads.dac.facade.EstadoFacade;
import com.ufpr.tads.dac.facade.FuncionarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author T-Gamer
 */
@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/FuncionarioServlet"})
public class FuncionarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean login = (UserBean) session.getAttribute("user");
        request.setCharacterEncoding("UTF-8");

        if (login == null) {
            //envia para fazer login
            request.setAttribute("msg", "É necessario esta logado para acessar essa pagina");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            String action = request.getParameter("action");
            if (action.equals("create")) {
                try {
                    request.setAttribute("estados", EstadoFacade.getAllEstados());
                    request.getRequestDispatcher("jsp/cadastrar-funcionario.jsp").forward(request, response);
                } catch (EstadoException ex) {
                    request.setAttribute("msg", ex);
                    request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                }
            } else if (action.equals("edit")) {
                try {
                    request.setAttribute("form", "alterar");
                    int funcId = Integer.parseInt(request.getParameter("idFuncionario"));
                    
                    request.setAttribute("estados", EstadoFacade.getAllEstados());
                    request.setAttribute("funcionario", FuncionarioFacade.getFuncionarioById(funcId));
                    request.getRequestDispatcher("jsp/cadastrar-funcionario.jsp").forward(request, response);
                } catch (EstadoException | FuncionarioException ex) {
                    request.setAttribute("msg", ex);
                    request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                }
            } else if (action.equals("salva")) {
                FuncionarioBean func = new FuncionarioBean();
                
                String email = request.getParameter("email");
                func.setNome(request.getParameter("nome"));
                func.setDataNasc(request.getParameter("dataNasc"));
                func.setCpf(request.getParameter("cpf"));
                func.setSalario(Double.parseDouble(request.getParameter("salario")));
                try {
                    FuncionarioFacade.novoFuncionario(func, email);
                    request.getRequestDispatcher("HomeServlet").forward(request, response);
                } catch (FuncionarioException ex) {
                    request.setAttribute("msg", ex);
                    request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                }
            } else if (action.equals("alterar")) {
                FuncionarioBean func = new FuncionarioBean();
                
                func.setIdFuncionario(request.getParameter("idFuncionario") == null ? 0 : Integer.parseInt(request.getParameter("idFuncionario")));
                func.setNome(request.getParameter("nome"));
                func.setDataNasc(request.getParameter("dataNasc"));
                func.setCpf(request.getParameter("cpf"));
                func.setSalario(Double.parseDouble(request.getParameter("salario")));
                try {
                    FuncionarioFacade.updateFuncionario(func);
                    request.getRequestDispatcher("HomeServlet").forward(request, response);
                } catch (FuncionarioException ex) {
                    request.setAttribute("msg", ex);
                    request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                }
            } else if (action.equals("list")) {
                try {
                    request.setAttribute("funcionarios", FuncionarioFacade.getAllFuncionario());
                    request.getRequestDispatcher("jsp/lista-funcionarios.jsp").forward(request, response);
                } catch (Exception ex) {
                    request.setAttribute("msg", ex);
                    request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                }
            } else if (action.equals("delete")) {
                try {
                    FuncionarioFacade.deleteFuncionario(Integer.parseInt(request.getParameter("idFuncionario")));
                    response.sendRedirect("FuncionarioServlet?action=list");
                } catch (FuncionarioException ex) {
                    request.setAttribute("msg", ex);
                    request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                }
            } else if (action.equals("form-new")) {
                request.getRequestDispatcher("jsp/cadastrar-funcionario.jsp").forward(request, response);
            }
            
        }
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
