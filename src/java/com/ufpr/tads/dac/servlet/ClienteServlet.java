package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.CidadeBean;
import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.beans.CorCabeloBean;
import com.ufpr.tads.dac.beans.CorPeleBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.beans.EscolaridadeBean;
import com.ufpr.tads.dac.beans.PreferenciaBean;
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

@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean login = (UserBean) session.getAttribute("user");
        ArrayList<CorPeleBean> coresPele = new ArrayList<>();
        ArrayList<CorCabeloBean> coresCabelo = new ArrayList<>();

        if (login == null) {
            //envia para fazer login
            request.setAttribute("msg", "É necessario esta logado para acessar essa pagina");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            //usuario logado
            if (login.isCliente()) {
                //usuario Cliente
                String action = request.getParameter("action");
                ClienteFacade cf = new ClienteFacade();
                switch (action) {
                    case "view":
                        try {
                            System.out.println("View Cliente");
                            request.setAttribute("form", "alterar");
                            request.setAttribute("cliente", cf.getClienteById(login.getClienteId()));
                            request.setAttribute("escolaridade", EscolaridadeFacade.getAllEscolaridade());
                            coresPele = CorPeleFacade.getAllCoresPele();
                            request.setAttribute("corPele", coresPele);
                            coresCabelo = CorCabeloFacade.getAllCoresCabelo();
                            request.setAttribute("corCabelo", coresCabelo);
                            request.setAttribute("estados", EstadoFacade.getAllEstados());
                            request.getRequestDispatcher("jsp/perfil.jsp").forward(request, response);
                        } catch (ClienteException | EnderecoException | CorPeleException
                                | CorCabeloException | EscolaridadeException | EstadoException ex) {
                            request.setAttribute("msg", ex);
                            request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                        }
                        break;
                    case "salva":
                        // passar corCabelo(id), corPele(id), escolaridade(id), se houver Endereco(id), rua, cidade(id), descricao e dataNasc em string 
                        ClienteBean cliente;
                        try {
                            cliente = cf.getClienteById(login.getClienteId());
                            coresPele = CorPeleFacade.getAllCoresPele();
                            coresCabelo = CorCabeloFacade.getAllCoresCabelo();

                            cliente.setClienteId(login.getClienteId());
                            cliente.setCorCabelo(new CorCabeloBean(request.getParameter("corCabelo") == null ? 0 : Integer.parseInt(request.getParameter("corCabelo"))));
                            cliente.setCorPele(new CorPeleBean(request.getParameter("corPele") == null ? 0 : Integer.parseInt(request.getParameter("corPele"))));
                            cliente.setEscolaridade(new EscolaridadeBean(request.getParameter("escolaridade") == null ? 0 : Integer.parseInt(request.getParameter("escolaridade"))));
                            cliente.setEndereco(new EnderecoBean(request.getParameter("idEndereco") == null ? 0 : Integer.parseInt(request.getParameter("idEndereco")),
                                    request.getParameter("rua"), new CidadeBean(request.getParameter("idCidade") == null ? 0 : Integer.parseInt(request.getParameter("idCidade")))));
                            cliente.setDataNasc(request.getParameter("dataNasc"));
                            cliente.setAltura(request.getParameter("altura") == null ? 0 : Integer.parseInt(request.getParameter("altura")));
                            cliente.setDescricao(request.getParameter("descricao"));
                            PreferenciaBean pf = cliente.getPreferencias();
                            pf.setSexo(request.getParameter("psexo"));
                            int corCabeloId = request.getParameter("pcorCabelo") == null ? 0 : Integer.parseInt(request.getParameter("pcorCabelo"));

                            for (int i = 0; i < coresCabelo.size(); i++) {
                                if (coresCabelo.get(i).getIdCorCabelo() == corCabeloId) {
                                    pf.setCorCabelo(coresCabelo.get(i));
                                    break;
                                }
                            }
                            int corPeleId = request.getParameter("pcorPele") == null ? 0 : Integer.parseInt(request.getParameter("pcorPele"));
                            for (int i = 0; i < coresPele.size(); i++) {
                                if (coresPele.get(i).getIdCorPele() == corPeleId) {
                                    System.out.println(coresPele.get(i).getCor());
                                    pf.setCorPele(coresPele.get(i));
                                    break;
                                }
                            }
                            String idadeStr[] = request.getParameter("pidade").split(" - ");
                            int[] idade = {Integer.parseInt(idadeStr[0]), Integer.parseInt(idadeStr[1])};
                            pf.setIdade(idade);

                            String alturaStr[] = request.getParameter("paltura").split(" - ");
                            int[] altura = {Integer.parseInt(alturaStr[0]), Integer.parseInt(alturaStr[1])};
                            pf.setAltura(altura);

                            cliente.setPreferencias(pf);
                            try {
                                cf.updateCliente(cliente);
                                request.setAttribute("salvo", true);
                                request.getRequestDispatcher("ClienteServlet?action=view").forward(request, response);
                            } catch (ClienteException ex) {
                                request.setAttribute("msg", ex);
                                request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                            }
                        } catch (ClienteException | EnderecoException | CorCabeloException | CorPeleException ex) {
                            request.setAttribute("msg", ex);
                            request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                        }

                        break;
                    case "user":
                        break;
                }
            } else {
                String action = request.getParameter("action");
                System.out.println("Funcionario");
                switch (action) {
                    case "form-new":
                        request.getRequestDispatcher("jsp/form-new-cliente.jsp").forward(request, response);
                        break;
                    case "salva":
                        try {
                            ClienteFacade.setNovoCliente(new ClienteBean(request.getParameter("nome"),request.getParameter("cpf"), request.getParameter("sexo")), request.getParameter("email"));
                        } catch (ClienteException ex) {
                            request.setAttribute("msg", ex);
                            request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                        }
                        break;

                }
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
        
            () {
        return "Short description";
        }// </editor-fold>

    }
