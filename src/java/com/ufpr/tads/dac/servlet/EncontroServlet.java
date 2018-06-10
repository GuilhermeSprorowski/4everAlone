package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.beans.EncontroBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EncontroException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import com.ufpr.tads.dac.facade.ClienteFacade;
import com.ufpr.tads.dac.facade.EncontroFacade;
import com.ufpr.tads.dac.facade.EnderecoFacade;
import java.io.IOException;
import java.util.ArrayList;
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

                case "solicitar-encontro-view":
                    System.out.println("solicitar-encontro-view");

                    try {
                        ClienteBean cb = ClienteFacade.getClienteById(login.getClienteId());
                        ArrayList<ClienteBean> listClientes = ClienteFacade.getClientesCompativeis(cb.getPreferencias(), cb.getEndereco().getCidade().getIdCidade());
                        request.setAttribute("clienteList", listClientes);
                        request.setAttribute("locaisList", EnderecoFacade.getAllEndereco());
                        request.getRequestDispatcher("jsp/solicitar-encontro.jsp").forward(request, response);
                    } catch (ClienteException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    } catch (EnderecoException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    }
                    break;
                case "solicitar-encontro-new":
                    System.out.println("solicitar-encontro-new");
                    int clienteId = request.getParameter("solicitadoId") == null ? 0 : Integer.parseInt(request.getParameter("solicitadoId"));
                    int enderecoId = request.getParameter("enderecoId") == null ? 0 : Integer.parseInt(request.getParameter("enderecoId"));
                    EncontroBean eb = new EncontroBean(request.getParameter("dataEncontro"), new ClienteBean(clienteId), new EnderecoBean(enderecoId));
                    try {
                        EncontroFacade.setSolicitacao(eb, login.getClienteId());
                    } catch (EncontroException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    }
                    request.getRequestDispatcher("EncontroServlet?action=meus-encontros").forward(request, response);
                    break;
                case "resposta-encontro":
                    System.out.println("resposta-encontro");
                    if (request.getParameter("resp") != null) {
                        try {
                            EncontroFacade.setResposta(new EncontroBean(
                                    request.getParameter("encontroId") == null ? 0 : Integer.parseInt(request.getParameter("encontroId")),
                                    Boolean.parseBoolean(request.getParameter("resp"))));
                        } catch (EncontroException ex) {
                            request.setAttribute("msg", ex);
                            request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("msg", "erro ao passar resposta");
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    }

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
