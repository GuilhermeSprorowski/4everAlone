package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.CorCabeloException;
import com.ufpr.tads.dac.exceptions.CorPeleException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import com.ufpr.tads.dac.exceptions.EscolaridadeException;
import com.ufpr.tads.dac.exceptions.EstadoException;
import com.ufpr.tads.dac.facade.ClienteFacade;
import com.ufpr.tads.dac.facade.CorCabeloFacade;
import com.ufpr.tads.dac.facade.CorPeleFacade;
import com.ufpr.tads.dac.facade.EscolaridadeFacade;
import com.ufpr.tads.dac.facade.EstadoFacade;
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
                        request.setAttribute("form", "alterar");
                        request.setAttribute("cliente", cf.getClienteById(login.getClienteId()));
                        request.setAttribute("escolaridade", EscolaridadeFacade.getAllEscolaridade());
                        request.setAttribute("corPele", CorPeleFacade.getAllCoresPele());
                        request.setAttribute("corCabelo", CorCabeloFacade.getAllCoresCabelo());
                        request.setAttribute("estados", EstadoFacade.getAllEstados());
                        request.getRequestDispatcher("jsp/perfil.jsp").forward(request, response);
                    } catch (ClienteException | EnderecoException | EscolaridadeException 
                            | CorPeleException | CorCabeloException | EstadoException ex) {
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
                    cliente.setDescricao(request.getParameter("descricao"));

                    cliente.getEndereco().setCidadeId(Integer.parseInt(request.getParameter("endereco.cidadeId")));
                    cliente.getEndereco().setRua(request.getParameter("endereco.rua"));
                    //cliente.getPreferencias().setSexo(Integer.parseInt(request.getParameter("preferencias.cidadeId")));
                    //cliente.getPreferencias().setCorCabeloId(Integer.parseInt(request.getParameter("preferencias.corCabelo")));
                    //cliente.getPreferencias().setCorPeleId(Integer.parseInt(request.getParameter("preferencias.corPele")));
                    //String idadeStr[] = request.getParameter("preferencias.idade").split(" - ");
                    //int[] idade = { Integer.parseInt(idadeStr[0]), Integer.parseInt(idadeStr[1]) };
                    //cliente.getPreferencias().setIdade(idade);
                    
                    //String alturaStr[] = request.getParameter("preferencias.altura").split(" - ");
                    //int[] altura = { Integer.parseInt(alturaStr[0]), Integer.parseInt(alturaStr[1]) };
                    //cliente.getPreferencias().setAltura(altura);
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
