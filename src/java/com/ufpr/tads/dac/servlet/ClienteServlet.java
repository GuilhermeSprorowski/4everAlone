package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import com.ufpr.tads.dac.facade.ClienteFacade;
import com.ufpr.tads.dac.facade.PreferenciaFacade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

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
            String action = request.getParameter("action");
            ClienteFacade cf = new ClienteFacade();
            PreferenciaFacade pf = new PreferenciaFacade();
            switch (action) {
                case "view":
                    try {
                        request.setAttribute("cliente", cf.getClienteById(login.getClienteId()));
                        request.getRequestDispatcher("jsp/perfil.jsp").forward(request, response);
                    } catch (ClienteException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    } catch (EnderecoException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    }
                    break;
                case "salva":
                    ClienteBean cliente = new ClienteBean();
                    cliente.setClienteId(login.getClienteId());
                    cliente.setCorCabelo(request.getParameter("corCabelo"));
                    cliente.setCorPele(request.getParameter("corPele"));
                    cliente.setEscolaridade(request.getParameter("escolaridade"));
                    cliente.getEndereco().setCidadeId(Integer.parseInt(request.getParameter("codCidade")));
                    cliente.getEndereco().setRua(request.getParameter("edereco"));
                    cliente.setDescricao(request.getParameter("descricao"));
                    try {
                        cf.updateCliente(cliente);
                        request.setAttribute("salvo", true);
                        request.getRequestDispatcher("jsp/perfil.jsp").forward(request, response);
                    } catch (ClienteException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    }
                    break;
                case "user":
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
