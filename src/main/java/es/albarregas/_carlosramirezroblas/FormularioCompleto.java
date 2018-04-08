/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas._carlosramirezroblas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "FormularioCompleto", urlPatterns = {"/FormularioCompleto"})
public class FormularioCompleto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormularioCompleto</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href='"+ request.getContextPath() +"/CSS/servlet.css' media=\"all\" />");
            out.println("</head>");
            out.println("<body>");

            Map m = request.getParameterMap();
            Set s = m.entrySet();
            Iterator it = s.iterator();

            out.println("<h1>Formulario relleno</h1>");
            
            while (it.hasNext()) {

                Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) it.next();

                String key = entry.getKey();
                String[] value = entry.getValue();

              
                if (key.equalsIgnoreCase("Enviar") || value[0].toString()==("")) {

                } else if (value.length > 1) {
                    out.println("La lista de  " + key + " :</br>");
                   for (int i = 0; i < value.length; i++) {
                        out.println("<li>" + value[i].toString() + "</li></br>");
                    }
                    
                } else {
                    out.println(key + " - " + value[0].toString() + "</br>");
                }
            }
            out.println("</br> <a href='" + request.getContextPath() + "/index.html' > Enlace a index </a>");
            out.println("</body>");
            out.println("</html>");
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
