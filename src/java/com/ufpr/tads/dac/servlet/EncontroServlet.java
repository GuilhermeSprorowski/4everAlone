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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
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
    
    private Date getDateBetween(int diamin, int diamax, int horamin, int horamax) {
        Random rand = new Random();
        int randomDia = rand.nextInt((diamax - diamin) + 1) + diamin;
        int randomHora = rand.nextInt((horamax - horamin) + 1) + horamin;
        
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, randomDia);
        c.add(Calendar.HOUR_OF_DAY, randomHora);
        
        return c.getTime();
    }

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
            
                    try {
                        request.setAttribute("encontrosEnviados", EncontroFacade.getAllEncontrosByIdCliente(login.getClienteId()));
                        request.setAttribute("encontrosParaResponder", EncontroFacade.getEncontrosRecebidos(login.getClienteId()));
                    } catch (EncontroException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                        return;
                    }
            
                    request.getRequestDispatcher("jsp/meus-encontros.jsp").forward(request, response);
                    break;

                case "solicitar-encontro-view":
                    System.out.println("solicitar-encontro-view");

                    try {
                        ClienteBean cb = ClienteFacade.getClienteById(login.getClienteId());
                        ArrayList<ClienteBean> listClientes = ClienteFacade.getClientesCompativeis(cb.getPreferencias(), cb.getEndereco().getCidade().getIdCidade(), login.getClienteId());
                        request.setAttribute("clienteList", listClientes);
                        request.setAttribute("locaisList", EnderecoFacade.getAllEndereco());
                        request.getRequestDispatcher("jsp/solicitar-encontro.jsp").forward(request, response);
                    } catch (ClienteException | EnderecoException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    }
                    break;
                case "solicitar-encontro-new":
                    System.out.println("solicitar-encontro-new");
                    try {
                        ClienteBean cli = ClienteFacade.getClienteById(login.getClienteId());
                        int clienteId = request.getParameter("clienteId") == null ? 0 : Integer.parseInt(request.getParameter("clienteId"));
                        Date dataEncontro = getDateBetween(5, 15, 10, 23); // De 5 até 15 dias, das 10 as 23h
                        EnderecoBean enderecoEncontro = EnderecoFacade.getRandomLocal(cli.getEndereco().getCidade().getIdCidade());
                        EncontroBean eb = new EncontroBean(dataEncontro, new ClienteBean(clienteId), enderecoEncontro);
                        try {
                            EncontroFacade.setSolicitacao(eb, login.getClienteId());
                        } catch (EncontroException ex) {
                            System.out.println(ex);
                            request.setAttribute("msg", ex);
                            request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                            return;
                        }
                        request.getRequestDispatcher("EncontroServlet?action=meus-encontros").forward(request, response);
                    } catch (ClienteException | EnderecoException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    }
            

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
                            return;
                        }
                    } else {
                        request.setAttribute("msg", "erro ao passar resposta");
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                        return;
                    }
                    
                    request.getRequestDispatcher("HomeServlet").forward(request, response);
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
