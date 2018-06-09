package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EncontroException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import com.ufpr.tads.dac.facade.ClienteFacade;
import com.ufpr.tads.dac.facade.EncontroFacade;
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

@WebServlet(name = "EncontroServlet", urlPatterns = {"/EncontroServlet"})
public class EncontroServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean login = (UserBean) session.getAttribute("user");
        if (login == null) {

            //envia para fazer login
            request.setAttribute("msg", "É necessario esta logado para acessar essa pagina");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            String action = request.getParameter("action");

            switch (action) {
                case "meus-encontros":
                    System.out.println("meus-encontro");
                    request.getRequestDispatcher("jsp/meus-encontros.jsp").forward(request, response);
                    break;

                case "solicitar-encontro":
                    System.out.println("solicitar-encontro");

                    try {
                        ClienteBean cb = ClienteFacade.getClienteById(login.getClienteId());     
                        ArrayList<ClienteBean> listClientes = ClienteFacade.getClientesCompativeis(cb.getPreferencias(), cb.getEndereco().getCidade().getIdCidade());
                        request.setAttribute("listClientes",listClientes );
                    } catch (ClienteException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    } catch (EnderecoException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    } 

                    request.getRequestDispatcher("jsp/solicitar-encontro.jsp").forward(request, response);
                    break;
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
