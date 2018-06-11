/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.beans.ConviteBean;
import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.ConviteException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import com.ufpr.tads.dac.exceptions.FestaException;
import com.ufpr.tads.dac.facade.ClienteFacade;
import com.ufpr.tads.dac.facade.ConviteFacade;
import com.ufpr.tads.dac.facade.FestaFacade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "FestaServlet", urlPatterns = {"/FestaServlet"})
public class FestaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean login = (UserBean) session.getAttribute("user");

        if (login == null) {
            //envia para fazer login
            request.setAttribute("msg", "É necessario esta logado para acessar essa pagina");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            //usuario logado
            if (login.isCliente()) {
                String action = request.getParameter("action");
                if (action.equals("view")) {
                    try {
                        request.setAttribute("convites", ConviteFacade.getAllConvites(login.getClienteId()));
                    } catch (ConviteException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                        return;
                    }
                    request.getRequestDispatcher("jsp/festas.jsp").forward(request, response);
                } else if (action.equals("update")) {
                    if (request.getParameter("resp") != null) {
                        boolean confirmou = Boolean.parseBoolean(request.getParameter("resp"));
                        ConviteBean conv = new ConviteBean();
                        conv.setIdConvite(request.getParameter("conviteId") == null ? 0 : Integer.parseInt(request.getParameter("conviteId")));
                        conv.setConfirmado(confirmou);
                        try {
                            ConviteFacade.updateConvite(conv);
                        } catch (ConviteException ex) {
                            request.setAttribute("msg", ex);
                            request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                            return;
                        }
                    } else {
                        request.setAttribute("msg", "Confirmação não informada");
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                        return;
                    }
                    request.getRequestDispatcher("HomeServlet").forward(request, response);
                }
            } else {
                //usuario Funcionario
                
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
