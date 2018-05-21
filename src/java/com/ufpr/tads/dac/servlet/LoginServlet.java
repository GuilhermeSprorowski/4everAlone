package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.UserBean;
import com.ufpr.tads.dac.facade.UserFacade;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //PASSAR EMAIL E SENHA
        String email = (String) request.getParameter("inputEmail");
        String senha = (String) request.getParameter("inputPassword");
        String msg = null;
        UserFacade userFacade = new UserFacade();
        try {
            //Convercao senha -> md5
            MessageDigest md;
            String senhaMd5 = "";
            try {
                md = MessageDigest.getInstance("MD5");
                md.update(senha.getBytes("UTF8"));
                byte s[] = md.digest();
                for (int i = 0; i < s.length; i++) {
                    senhaMd5 += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Confere Login
            UserBean user = userFacade.getUserByLogin(email, senhaMd5);
            if (user != null) {
                //USUARIO VALIDO
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.getRequestDispatcher("/HomeServlet").forward(request, response);
            } else {
                //USUARIO INVALIDO
                request.setAttribute("msg", "Usuario Invalido");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException ex) {

            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
