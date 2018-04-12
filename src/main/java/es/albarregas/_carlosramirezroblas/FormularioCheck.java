/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas._carlosramirezroblas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "FormularioCheck", urlPatterns = {"/FormularioCheck"})
public class FormularioCheck extends HttpServlet {

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
            out.println("<title>Servlet FormularioCheck</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href='" + request.getContextPath() + "/CSS/formulariocss.css' media=\"all\" />");
            out.println("</head>");
            out.println("<body>");

            
            boolean errorN = false;
            boolean errorU = false;
            boolean errorP = false;
            boolean errorF = false;
            boolean errorApe = false;
            boolean errorTlf = false;
            boolean errorDni = false;

            if (request.getParameter("nombre") != null) {
                if ("".equals(request.getParameter("nombre"))) {
                    errorN = true;

                } else {
                    errorN = false;
                }
            }
            if (request.getParameter("apellidos") != null) {
                if ("".equals(request.getParameter("apellidos"))) {
                    errorApe = true;

                } else {
                    errorApe = false;
                }
            }
            if (request.getParameter("usuario") != null) {
                if ("".equals(request.getParameter("usuario"))) {
                    errorU = true;

                } else {
                    errorU = false;
                }
            }
            if (request.getParameter("password") != null) {
                if ("".equals(request.getParameter("password"))) {
                    errorP = true;

                } else {
                    errorP = false;
                }
            }
           
            Calendar calendario = new GregorianCalendar();
            int yActual = calendario.get(Calendar.YEAR);
            if (request.getParameter("fecha") != null) {
                if ("".equals(request.getParameter("fecha"))) {
                    errorF = true;
                   
                } else{ 
                    int year = Integer.parseInt(request.getParameter("fecha").substring(6));
                    if (year > yActual) {
                    errorF = true;
                } else {
                    int day = Integer.parseInt(request.getParameter("fecha").substring(0, 2));

                    int month = Integer.parseInt(request.getParameter("fecha").substring(3, 5));

                    if (month == 4 || month == 6 || month == 9 || month == 11) {
                        if (day == 31) {
                            errorF = true;
                        } else {
                            errorF = false;
                        }
                    }
                   
                    if (month == 2) {
                        if (year % 4 == 0 && year % 100 != 0 || year % 400
                                == 0) {
                            if (day <= 29) {
                                errorF = false;
                            } else if (day <= 28) {
                                errorF = false;
                            } else {
                                errorF = true;
                            }
                        } else if (day >= 29) {
                            errorF = true;
                        }

                    }
                }
            }
            }
            
            if (request.getParameter("tlf") != null) {
                if ("".equals(request.getParameter("tlf"))) {
                    errorTlf = true;

                } else {
                    String validarTlf = request.getParameter("tlf");
                    String pre = validarTlf.substring(0, 1);

                    if (validarTlf.length() == 9) {
                        if (Integer.parseInt(pre) == 9 || Integer.parseInt(pre) == 8 || Integer.parseInt(pre) == 7 || Integer.parseInt(pre) == 6) {
                            errorTlf = false;
                        } else {
                            errorTlf = true;
                        }
                    } else {
                        errorTlf = true;
                    }
                }
            }

            if (request.getParameter("nDocumento") != null) {
                if (request.getParameter("documento").equals("Nif")) {
                    String nif = request.getParameter("nDocumento");
                    if (nif.length() == 9) {
                        String dni = nif.substring(0, 8);
                        String let = nif.substring(8);
                        String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
                        int resto = Integer.parseInt(dni) % 23;
                        if (letras[resto].equalsIgnoreCase(let)) {

                        } else {
                            errorDni = true;
                        }
                    } else {
                        errorDni = true;
                    }
                }
            } else {
                errorDni = true;
            }

            if (request.getParameter("nDocumento") != null) {
                if (request.getParameter("documento").equals("Nie")) {
                    String nie = request.getParameter("nDocumento");
                    if ("".equals(nie)) {
                        errorDni = true;
                    } else {
                        boolean esValido = false;
                        int i = 1;
                        int caracterASCII = 0;
                        char letra = ' ';
                        int miNIE = 0;
                        int resto = 0;
                        char[] asignacionLetra = {'T', 'R', 'W', 'A',
                            'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
                            'V', 'H', 'L', 'C', 'K', 'E'};

                        if (nie.length() == 9 && Character.isLetter(nie.charAt(8))
                                && nie.substring(0, 1).toUpperCase().equals("X")
                                || nie.substring(0, 1).toUpperCase().equals("Y")
                                || nie.substring(0, 1).toUpperCase().equals("Z")) {

                            do {
                                caracterASCII = nie.codePointAt(i);
                                esValido
                                        = (caracterASCII > 47 && caracterASCII < 58);
                                i++;
                            } while (i
                                    < nie.length() - 1 && esValido);
                        }

                        if (esValido && nie.substring(0, 1).toUpperCase().equals("X")) {
                            nie = "0" + nie.substring(1, 9);
                        } else if (esValido
                                && nie.substring(0, 1).toUpperCase().equals("Y")) {
                            nie = "1"
                                    + nie.substring(1, 9);
                        } else if (esValido
                                && nie.substring(0, 1).toUpperCase().equals("Z")) {
                            nie = "2"
                                    + nie.substring(1, 9);
                        }

                        if (esValido) {
                            letra = Character.toUpperCase(nie.charAt(8));
                            miNIE = Integer.parseInt(nie.substring(1, 8));
                            resto = miNIE % 23;
                            esValido = (letra == asignacionLetra[resto]);
                        }

                    }
                }

            }
            if (request.getParameter("nDocumento") != null) {
                if (request.getParameter("documento").equals("Pasaporte")) {
                    String pasa = request.getParameter("documento");
                    if (pasa.equals("")) {
                        errorDni = true;
                    }
                }

            }

            if (errorN || errorU || errorP || errorF || errorTlf || errorApe || errorDni) {
            
                out.println("<form id=\"formulario\" action=\"FormularioCheck\" method=\"post\">");
                out.println("<fieldset>");
                out.println("<legend>Formulario con check</legend>");
                if(errorN){
                    out.println("<label>*Nombre &#x2716;</label>");
                out.println("<input id=\"nombre\" name=\"nombre\" type=\"text\" value=\"" + request.getParameter("nombre") + "\" >");
                }else{
                out.println("<label>*Nombre &#10004;</label>");
                out.println("<input id=\"nombre\" name=\"nombre\" type=\"text\" value=\"" + request.getParameter("nombre") + "\" >");
                }
                out.println("</br>");
                if(errorApe){
                out.println("<label>Apellidos &#x2716;</label>");
                out.println("<input id=\"apellidos\" name=\"apellidos\" type=\"text\" value=\"" + request.getParameter("apellidos") + "\" >");
                }else{
                out.println("<label>Apellidos &#10004;</label>");
                out.println("<input id=\"apellidos\" name=\"apellidos\" type=\"text\" value=\"" + request.getParameter("apellidos") + "\" >");
                }
                out.println("</br>");
                if(errorU){
                    out.println("<label>*Usuario &#x2716;</label>");
                out.println("<input id=\"usuario\" name=\"usuario\" type=\"text\" value=\"" + request.getParameter("usuario") + "\" />");
                }else{out.println("<label>*Usuario &#10004;</label>");
                out.println("<input id=\"usuario\" name=\"usuario\" type=\"text\" value=\"" + request.getParameter("usuario") + "\" />");
                }                
                out.println("</br>");
                if(errorP){
                    out.println("<label>*Password &#x2716;</label>");
                out.println("<input id=\"password\" name=\"password\" type=\"text\" value=\"" + request.getParameter("password") + "\" />");
                }else{
                   out.println("<label>*Password &#10004;</label>");
                out.println("<input id=\"password\" name=\"password\" type=\"text\" value=\"" + request.getParameter("password") + "\" />"); 
                }                
                out.println("</br>");
                if(errorF){
                    out.println("<label>*Fecha de nacimiento &#x2716;</label>");
                out.println("<input id=\"usuario\" name=\"fecha\" type=\"text\" placeholder=\"Formato(DD/MM/YYYY)\" value=\"" + request.getParameter("fecha") + "\" />");
                }else{
                    out.println("<label>*Fecha de nacimiento &#10004;</label>");
                out.println("<input id=\"usuario\" name=\"fecha\" type=\"text\" placeholder=\"Formato(DD/MM/YYYY)\" value=\"" + request.getParameter("fecha") + "\" />");
                }                
                out.println("</br>");
                if(errorTlf){
                    out.println("<label>*Telefono &#x2716;</label>");
                out.println("<input id=\"password\" name=\"tlf\" type=\"text\" placeholder=\"Formato(9-8-7-6)+8digitos\" value=\"" + request.getParameter("tlf") + "\" />");
                }else{
                    out.println("<label>*Telefono &#10004;</label>");
                out.println("<input id=\"password\" name=\"tlf\" type=\"text\" placeholder=\"Formato(9-8-7-6)+8digitos\" value=\"" + request.getParameter("tlf") + "\" />");
                }                
                out.println("</br>");
                if(errorDni){
                out.println("<label>*Tipo de documentacion &#x2716;</label>");
                out.println("<select name=\"documento\">");
                if (request.getParameter("documento") != null) {
                    out.println("<option selected>" + request.getParameter("documento") + "</option>");
                }
                out.println("<option value=\"Nif\">Nif</option>");
                out.println("<option value=\"Nie\">Nie</option>");
                out.println("<option value=\"Pasaporte\">Numero de pasaporte</option>");
                out.println("</select> ");
                
                }else {
                    out.println("<label>*Tipo de documentacion &#10004;</label>");
                out.println("<select name=\"documento\">");
                if (request.getParameter("documento") != null) {
                    out.println("<option selected>" + request.getParameter("documento") + "</option>");
                }
                out.println("<option value=\"Nif\">Nif</option>");
                out.println("<option value=\"Nie\">Nie</option>");
                out.println("<option value=\"Pasaporte\">Numero de pasaporte</option>");
                out.println("</select> ");
                
                }
                out.println("</br>");
                out.println("<input name=\"nDocumento\" type=\"text\" placeholder=\"Numero de su identificacion\" value=\"" + request.getParameter("nDocumento") + "\" >");
                out.println("</br>");
                out.println("<label>Preferencias</label>");
                out.println("</br>");

                if (request.getParameter("Deporte") != null) {
                    out.println("<input type=\"checkbox\" name=\"Deporte\" value=\"Deporte\" checked>Deporte");
                    out.println("</br>");
                } else {
                    out.println("<input type=\"checkbox\" name=\"Deporte\" value=\"Deporte\">Deporte");
                    out.println("</br>");
                }
                if (request.getParameter("Lectura") != null) {
                    out.println("<input type=\"checkbox\" name=\"Lectura\" value=\"Lectura\" checked >Lectura");
                    out.println("</br>");
                } else {
                    out.println("<input type=\"checkbox\" name=\"Lectura\" value=\"Lectura\" >Lectura");
                    out.println("</br>");
                }
                if (request.getParameter("Cine") != null) {
                    out.println("<input type=\"checkbox\" name=\"Cine\" value=\"Cine\" checked>Cine");
                    out.println("</br>");
                } else {
                    out.println("<input type=\"checkbox\" name=\"Cine\" value=\"Cine\">Cine");
                    out.println("</br>");
                }
                if (request.getParameter("Viajes") != null) {
                    out.println("<input type=\"checkbox\" name=\"Viajes\" value=\"Viajes\" checked>Viajes");
                    out.println("</br>");
                } else {
                    out.println("<input type=\"checkbox\" name=\"Viajes\" value=\"Viajes\">Viajes");
                    out.println("</br>");
                }

                out.println("</fieldset>");
                out.println("<input type=\"submit\" value=\"Enviar\"formaction=\"FormularioCheck\" />");
                out.println("<input type=\"submit\" value=\"Borrar\"formaction=\"HTML/FormularioCheck.html\" />");
                out.println("</form>");

            } else {
                Enumeration<String> parametros = request.getParameterNames();
                int pref = 0;
                while (parametros.hasMoreElements()) {
                    String elemento = parametros.nextElement();
                    String valor = request.getParameter(elemento);

                    if (elemento.equalsIgnoreCase("Enviar")) {

                    } else if (elemento.equalsIgnoreCase("documento")) {

                        out.println("Tipo de documento " + request.getParameter("documento"));
                        out.println("</br>");

                    } else if (elemento.equalsIgnoreCase("Deporte") || elemento.equalsIgnoreCase("Lectura") || elemento.equalsIgnoreCase("Cine") || elemento.equalsIgnoreCase("Viajes")) {
                        if (pref == 0) {
                            out.print("Preferencias:");
                            pref++;
                        }
                        String[] aficiones = request.getParameterValues(elemento);
                        for (int i = 0; i < aficiones.length; i++) {
                            out.print(aficiones[i] + " ");
                        }
                    } else {
                        out.print(elemento + " : " + valor + "</br>");

                    }

                }

                out.println("</br> <a href='" + request.getContextPath() + "/index.html' > Enlace a index </a>");
            }

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
