package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.PedidoCasamentoBean;
import com.ufpr.tads.dac.beans.UserBean;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@WebServlet(name = "CasamentoServlet", urlPatterns = {"/CasamentoServlet"})
public class CasamentoServlet extends HttpServlet {

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
                case "visualizar":
                    request.getRequestDispatcher("jsp/casamento.jsp").forward(request, response);
                    break;

                case "solicitar":

                    request.getRequestDispatcher("jsp/solicitar-casamento.jsp").forward(request, response);
                    break;
                case "orcar":
                    System.out.println("SolicitaOrcamento");
                    Client client = ClientBuilder.newClient();
                    if (request.getParameter("conjugeId") == null) {
                        request.setAttribute("msg", "Conjuge não encontrado");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                    PedidoCasamentoBean pc = new PedidoCasamentoBean(Integer.parseInt(request.getParameter("conjugeId")), login.getClienteId(),
                            request.getParameter("nConvidados") == null ? 0 : Integer.parseInt(request.getParameter("conjugeId")),
                            request.getParameter("padre"), request.getParameter("igreja"), request.getParameter("luademel"), request.getParameter("padrinho1"),
                            request.getParameter("padrinho2"), request.getParameter("madrinha1"), request.getParameter("madrinha2"));
                    pc.setDataCasamento(request.getParameter("dataCasamento"));
                    client.target("http://localhost:8080/AlwaysTogether/webresources/casamento/orcamento")
                            .request(MediaType.APPLICATION_JSON).post(Entity.json(pc));
                    break;
                case "resp":

                    System.out.println("RespostaOrcamento");
                    client = ClientBuilder.newClient();
                    PedidoCasamentoBean pc1 = new PedidoCasamentoBean();
                    pc1.setIdPedido(1);
                    pc1.setDataConfirmacao(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
                    System.out.println(" 4ever " + pc1.getDataConfirmacao());
                    pc1.setAceito(true);
                    client.target("http://localhost:8080/AlwaysTogether/webresources/casamento/respostaOrcamento").request(MediaType.APPLICATION_JSON).put(Entity.json(pc1));
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
