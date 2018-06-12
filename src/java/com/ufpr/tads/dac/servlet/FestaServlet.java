/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.servlet;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.ufpr.tads.dac.beans.ConviteBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.beans.FestaBean;
import com.ufpr.tads.dac.beans.FuncionarioBean;
import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.ConviteException;
import com.ufpr.tads.dac.exceptions.EstadoException;
import com.ufpr.tads.dac.exceptions.FestaException;
import com.ufpr.tads.dac.facade.ClienteFacade;
import com.ufpr.tads.dac.facade.ConviteFacade;
import com.ufpr.tads.dac.facade.EstadoFacade;
import com.ufpr.tads.dac.facade.FestaFacade;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    private void SendEmail(int cliId, FestaBean festa) {
        try {
            Email to = new Email(ClienteFacade.getEmailCliente(cliId));

            Email from = new Email("eliasdarruda@gmail.com");
            String subject = "4EverAlone - Convite para festa";
            Content content = new Content("text/html", 
                "<h1>Você foi convidado para a festa " + festa.getTema() + "!</h1><br>"
                + "<p>Entre já no 4EverAlone e confirme a sua presença!</p>");
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid("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");
            
            try {
                Request request = new Request();
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
            } catch (IOException ex) {
                Logger.getLogger(FestaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClienteException ex) {
            Logger.getLogger(FestaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                } else if (action.equals("cadastrar")) {
                    
                    try {
                        request.setAttribute("clientesList", ClienteFacade.getAllClientes());
                        request.setAttribute("estados", EstadoFacade.getAllEstados());
                        request.getRequestDispatcher("jsp/cadastrar-festa.jsp").forward(request, response);
                    } catch (ClienteException | EstadoException ex) {
                        request.setAttribute("msg", "Confirmação não informada");
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    }
                    
                } else if (action.equals("new")) {
                    String descricao = request.getParameter("descricao");
                    String tema = request.getParameter("tema");
                    int vagas = request.getParameter("vagas") == null ? 0 : Integer.parseInt(request.getParameter("vagas"));
                    int local = request.getParameter("localId") == null ? 0 : Integer.parseInt(request.getParameter("localId"));

                    Date data = formatDate(request.getParameter("dataFesta"), request.getParameter("horario"));
                    ArrayList<String> clientes = new ArrayList<>(Arrays.asList(request.getParameter("clientes").split(";")));

                    FestaBean festa = new FestaBean();
                    festa.setDescricao(descricao);
                    festa.setTema(tema);
                    festa.setVagas(vagas);
                    festa.setDatahora(data);
                    EnderecoBean end = new EnderecoBean();
                    end.setEnderecoId(local);
                    FuncionarioBean fn = new FuncionarioBean();
                    fn.setIdFuncionario(login.getFuncionarioId());
                    festa.setFuncionarioResponsavel(fn);
                    festa.setEndereco(end);
                    int idFesta = 0;
                    try {
                        idFesta = FestaFacade.novaFesta(festa);
                    } catch (FestaException ex) {
                        request.setAttribute("msg", "Confirmação não informada");
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    }
                    festa.setIdFesta(idFesta);
                    
                    clientes.forEach((c) -> {
                        try {
                            ConviteFacade.enviaConvite(Integer.parseInt(c), festa);
                            SendEmail(Integer.parseInt(c), festa);
                        } catch (ConviteException ex) {
                            Logger.getLogger(FestaServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    
                    System.out.println("Enviou convites");
                    String json = "{\"ok\": true}";
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                }
            } else {
                //usuario Funcionario
                
            }
        }
    }
    private Date formatDate(String data, String hora) {    
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = null;
        try {
            date = format.parse(data + " " + hora);
        } catch (ParseException ex) {
            Logger.getLogger(FestaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return date;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
/     * @param request servlet request
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
