package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.ConviteBean;
import com.ufpr.tads.dac.beans.EncontroBean;
import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.exceptions.ConviteException;
import com.ufpr.tads.dac.exceptions.EncontroException;
import com.ufpr.tads.dac.exceptions.FestaException;
import com.ufpr.tads.dac.exceptions.FuncionarioException;
import com.ufpr.tads.dac.facade.ConviteFacade;
import com.ufpr.tads.dac.facade.EncontroFacade;
import com.ufpr.tads.dac.facade.FestaFacade;
import com.ufpr.tads.dac.facade.FuncionarioFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Essa servlet passa duas listas para a home page como os nomes encontroList & conviteList

        HttpSession session = request.getSession();
        UserBean login = (UserBean) session.getAttribute("user");
        if (login == null) {
            //envia para fazer login
            request.setAttribute("msg", "É necessario esta logado para acessar essa pagina");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            //usuario logado
            if (login.isCliente()) {
                // usuarioCliente
                EncontroFacade ef = new EncontroFacade();
                try {
                    ArrayList<EncontroBean> encontroList = ef.getEncontrosPendentes(login.getClienteId());
                    request.setAttribute("encontroList", encontroList);
                } catch (EncontroException ex) {
                    request.setAttribute("msg", ex);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
                ConviteFacade cf = new ConviteFacade();
                try {
                    ArrayList<ConviteBean> conviteList = cf.getAllConvites(login.getClienteId());
                    request.setAttribute("conviteList", conviteList);
                } catch (ConviteException ex) {
                    request.setAttribute("msg", ex);
                    request.getRequestDispatcher("erro.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
            } else {
                //usuarioFuncionario
                if (login.isAdm()) {
                    //usuarioAdm
                    System.out.println("admin");
                    try {
                        request.setAttribute("funcionarioList", FuncionarioFacade.getAllFuncionario());
                        request.getRequestDispatcher("/jsp/admin-home.jsp").forward(request, response);
                    } catch (FuncionarioException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("erro.jsp").forward(request, response);
                    }
                } else {
                    //usuario Funcionario
                    System.out.println("Funcioanrio");
                    try {
                        request.setAttribute("festaList", FestaFacade.getAllFesta());
                        request.getRequestDispatcher("/jsp/funcionario-home.jsp").forward(request, response);
                    } catch (FestaException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("erro.jsp").forward(request, response);
                    }
                }
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
