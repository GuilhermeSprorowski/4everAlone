package com.ufpr.tads.dac.servlet;

import com.google.gson.Gson;
import com.ufpr.tads.dac.beans.CidadeBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.exceptions.CidadeException;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import com.ufpr.tads.dac.exceptions.UserException;
import com.ufpr.tads.dac.facade.CidadeFacade;
import com.ufpr.tads.dac.facade.ClienteFacade;
import com.ufpr.tads.dac.facade.EnderecoFacade;
import com.ufpr.tads.dac.facade.UserFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AJAXServlet", urlPatterns = {"/AJAXServlet"})
public class AJAXServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "viewCidade":
                int estado = request.getParameter("estadoId") == null ? 0 : Integer.parseInt(request.getParameter("estadoId"));
                CidadeFacade cf = new CidadeFacade();
                try {
                    ArrayList<CidadeBean> lc = cf.getCidadeByEstadoId(estado);
                    String json = new Gson().toJson(lc);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } catch (CidadeException ex) {
                    request.setAttribute("msg", ex);
                    request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
                }
                break;
            case "viewLocais":
                int cidade = request.getParameter("cidadeId") == null ? 0 : Integer.parseInt(request.getParameter("cidadeId"));
                
                try {
                    ArrayList<EnderecoBean> ff = EnderecoFacade.getAllLocais(cidade);
                    String json = new Gson().toJson(ff);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } catch (EnderecoException ex) {
                    request.setAttribute("msg", ex);
                    request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
                }
                break;
            case "verificaEmail":
                //passar EMAIL para verificar
                try {
                    boolean disponivel = new UserFacade().isEmailDisponivel(request.getParameter("email"));
                    String json = "{\"emailDisponivel\": " + disponivel + "}";
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } catch (UserException ex) {
                    request.setAttribute("msg", ex);
                    request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
                }
                break;
            case "verificaCpf": {
                try {
                    //passar Cpf ja verificado
                    Boolean disponivel = new ClienteFacade().isCpfDisponivel(request.getParameter("cpf"));
                    String json = "{\"cpfDisponivel\": " + disponivel + "}";
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } catch (ClienteException ex) {
                    request.setAttribute("msg", ex);
                    request.getRequestDispatcher("/jsp/erro.jsp").forward(request, response);
                }
            }
            break;

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
