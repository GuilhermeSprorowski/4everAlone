/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.servlet;

import com.ufpr.tads.dac.beans.UserBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author elias
 */
@WebServlet(name = "ImageServlet", urlPatterns = {"/ImageServlet"})
public class ImageServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext cntx= request.getServletContext();
        String imgId = request.getParameter("img");
        
        // Get the absolute path of the image
        String filename = System.getProperty("user.dir") + "/pictures/" + imgId + ".png";
        System.out.println(filename);
        // retrieve mimeType dynamically
        String mime = cntx.getMimeType(filename);
        if (mime == null) {
          response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
          return;
        }

        response.setContentType(mime);
        File file = new File(filename);
        response.setContentLength((int)file.length());

        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

        // Copy the contents of the file to the output stream
        byte[] buf = new byte[1024];
         int count = 0;
         while ((count = in.read(buf)) >= 0) {
           out.write(buf, 0, count);
        }
        out.close();
        in.close();
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
