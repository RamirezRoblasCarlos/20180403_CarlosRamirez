/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas._carlosramirezroblas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "CicloDeVida", urlPatterns = {"/CicloDeVida"}, initParams = {
    @WebInitParam(name = "Nombre", value = "Carlos"),
    @WebInitParam(name = "Edad", value = "25")})
public class CicloDeVida extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param config
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
     @Override
     public void init(ServletConfig config){
        System.out.println("init() "+ config.getServletName());
        Enumeration<String> parametros = config.getInitParameterNames();
            while(parametros.hasMoreElements()){
                String elemento = parametros.nextElement();
                String valor = config.getInitParameter(elemento);
                System.out.println(elemento+" - "+valor);
                
            }
      
   }
    
     @Override
   public void destroy(){
       System.out.println("destroy()");
   }
    
    
     @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("service()");
      
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
  

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
 
}
