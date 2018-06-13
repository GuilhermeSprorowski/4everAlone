package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.PedidoCasamentoBean;
import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.exceptions.PedidoCasamentoException;
import com.ufpr.tads.dac.facade.PedidoCasamentoFacade;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
                    Client client = ClientBuilder.newClient();
                    Response resp =  client.target("http://localhost:8080/AlwaysTogether/webresources/casamento/orcamentoByClienteId/"+ login.getClienteId())
                            .request(MediaType.APPLICATION_JSON).get();
                    List<PedidoCasamentoBean> pcb = resp.readEntity(new GenericType<List<PedidoCasamentoBean>>() {} );
                    System.out.println("asdasdas  " +pcb.size());
                    request.setAttribute("pedidosList", pcb);
                    request.getRequestDispatcher("jsp/casamento.jsp").forward(request, response);
                    break;
                case "solicitar":
                    request.getRequestDispatcher("jsp/solicitar-casamento.jsp").forward(request, response);
                    break;
                case "orcar":
                    System.out.println("SolicitaOrcamento");
                    client = ClientBuilder.newClient();
                    if (request.getParameter("conjugeId") == null) {
                        request.setAttribute("msg", "Conjuge não encontrado");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                    PedidoCasamentoBean pc = new PedidoCasamentoBean(Integer.parseInt(request.getParameter("conjugeId")), login.getClienteId(),
                            request.getParameter("convidados") == null ? 0 : Integer.parseInt(request.getParameter("conjugeId")),
                            request.getParameter("padre"), request.getParameter("igreja"), request.getParameter("lua"), request.getParameter("padrinho1"),
                            request.getParameter("padrinho2"), request.getParameter("madrinha1"), request.getParameter("madrinha2"));
                    pc.setDataCasamento(request.getParameter("dataCasamento"));
                    try {
                        pc.setIdPedido(PedidoCasamentoFacade.setPedidoCasamento(pc));
                        client.target("http://localhost:8080/AlwaysTogether/webresources/casamento/orcamento")                                
                            .request(MediaType.APPLICATION_JSON).post(Entity.json(pc));
                        request.getRequestDispatcher("CasamentoServlet?action=visualizar").forward(request, response);
                    } catch (PedidoCasamentoException ex) {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                    
                    break;
                case "resp":
                    // passar resposta, true or false
                    System.out.println("RespostaOrcamento");
                    client = ClientBuilder.newClient();
                    PedidoCasamentoBean pc1 = new PedidoCasamentoBean();
                    pc1.setIdPedido(Integer.parseInt(request.getParameter("idPedido")));
                    pc1.setDataConfirmacao(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
                    System.out.println(" 4ever " + pc1.getDataConfirmacao());
                    pc1.setAceito(Boolean.parseBoolean(request.getParameter("aceio")));
                    client.target("http://localhost:8080/AlwaysTogether/webresources/casamento/respostaOrcamento").request(MediaType.APPLICATION_JSON).put(Entity.json(pc1));
                    request.getRequestDispatcher("CasamentoServlet?action=visualizar").forward(request, response);
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
