/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas._carlosramirezroblas;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "ContadorDeVisitasCookies", urlPatterns = {"/ContadorDeVisitasCookies"})
public class ContadorDeVisitasCookies extends HttpServlet {

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
            out.println("<title>Servlet ContadorDeVisitasCookies</title>"); 
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href='"+ request.getContextPath() +"/CSS/servlet.css' media=\"all\" />");
            out.println("</head>");
            out.println("<body>");
            boolean borrar=false;
            if(request.getParameter("Borrar")!=null){
            borrar=true;
            }
            boolean encontrada=false;
            Cookie[] ar;
            ar=request.getCookies();
            int i=0;
            int aux=0;
            for(i=0;i<ar.length;i++){
            if(ar[i].getName().equalsIgnoreCase("contador")){
            encontrada=true;
            aux=i;
            }
            }
            if(borrar){
            ar[aux].setMaxAge(1);
            encontrada=false;
            }
            if(!encontrada){
            
            String nombre="contador";
            String valor="1";
            Cookie prueba = new Cookie(nombre,valor);               
            prueba.setVersion(0);
            prueba.setSecure(false);
            prueba.setMaxAge(86400);
            response.addCookie(prueba);
            out.println("<h1>Has visitado la pagina" + prueba.getValue()+ "  vez.</h1>");
              out.println("<h2>La version es "+ prueba.getVersion()+"   </h2>");
                  out.println("<h3>Caducidad: "+  prueba.getMaxAge()+"  </h3>");
                  out.println("<h4>Seguridad: "+ prueba.getSecure()+"  </h4>");
            }else {
            int b=Integer.parseInt(ar[aux].getValue());
            b=b+1;
            String guardar="";
            guardar=""+b;
            ar[aux].setValue(guardar);
            out.println("<h1>Has visitado la pagina "+ ar[aux].getValue() +" veces.</h1>");
            }    
            
            
           out.println("</br> <a href='"+ request.getContextPath() +"/index.html' > Enlace a index </a>");
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
