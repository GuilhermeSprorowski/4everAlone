
package com.ufpr.tads.dac.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet(name = "ImageServlet", urlPatterns = {"/ImageServlet"})
public class ImageServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {

            case "view":
                System.out.println("View imagem");
                ServletContext cntx = request.getServletContext();
                String imgId = request.getParameter("img");

                // Get the absolute path of the image
                String filename = System.getProperty("user.dir") + "/pictures/perfil" + imgId + ".png";
                System.out.println(filename);
                // retrieve mimeType dynamically
                String mime = cntx.getMimeType(filename);
                if (mime == null) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    return;
                }
                response.setContentType(mime);
                FileInputStream in;
                OutputStream out;
                try {
                    File file = new File(filename);
                    response.setContentLength((int) file.length());

                    in = new FileInputStream(file);
                    out = response.getOutputStream();

                    // Copy the contents of the file to the output stream
                    byte[] buf = new byte[1024];
                    int count = 0;
                    while ((count = in.read(buf)) >= 0) {
                        out.write(buf, 0, count);
                    }
                    out.close();
                    in.close();
                } catch(FileNotFoundException ex) {
                    response.setContentType("image/jpeg");
                    filename = System.getProperty("user.dir") + "/pictures/default.jpg";
                    
                    File file = new File(filename);
                    response.setContentLength((int) file.length());

                    in = new FileInputStream(file);
                    out = response.getOutputStream();

                    byte[] buf = new byte[1024];
                    int count = 0;
                    while ((count = in.read(buf)) >= 0) {
                        out.write(buf, 0, count);
                    }
                    out.close();
                    in.close();
                }

                break;
            case "salva":
                System.out.println("Salva imagem");
                String imgId2 = request.getParameter("img");
                ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
                String filename2 = System.getProperty("user.dir") + "/pictures/perfil" + imgId2 + ".png";
                File fs = new File(System.getProperty("user.dir") + "/pictures");
                fs.mkdir();
                try {
                    System.out.println("Chegou!!");
                    List<FileItem> multifiles = sf.parseRequest(request);
                    if (multifiles != null) {
                        for (FileItem item : multifiles) {
                            File imgFile = new File(filename2);
                            BufferedImage imagem = ImageIO.read(item.getInputStream());
                            ImageIO.write(imagem, "png", imgFile);
                        }
                    }
                } catch (Exception ex) {
                    if (ex.equals("Erro java.lang.NullPointerException")) {
                        request.getRequestDispatcher("ClienteServlet?action=view").forward(request, response);
                    } else {
                        request.setAttribute("msg", ex);
                        request.getRequestDispatcher("jsp/erro.jsp").forward(request, response);
                    }
                }
                request.getRequestDispatcher("ClienteServlet?action=view").forward(request, response);
                break;
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
