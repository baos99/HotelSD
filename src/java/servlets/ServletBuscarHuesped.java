/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import hotel.Huesped;
import hotel.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author german
 */
public class ServletBuscarHuesped extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String format = "html";
        Huesped haux = null;
        String error = null;
        ArrayList<Huesped> huespedes =  (ArrayList<Huesped>) this.getServletContext().getAttribute("huespedes");
        ArrayList<Reserva> reservas = (ArrayList<Reserva>) this.getServletContext().getAttribute("reservas");
     
        String nif = request.getParameter("nif");
        String name = request.getParameter("name");
        
        String form = request.getParameter("format");
        
        format = (form==null ? format:form );
        
        if (!nif.isEmpty()) {
            for (Huesped h : huespedes) {
                if (request.getParameter("nif").equalsIgnoreCase(h.getNif())) {
                    haux = h;
                    break;
                }
            }
        }else if (!name.isEmpty()){
            for (Huesped h : huespedes) {
                if (request.getParameter("name").equalsIgnoreCase(h.getNombre()) && request.getParameter("surname").equalsIgnoreCase(h.getApellidos())){
                    haux = h;
                    break;
                }
            }
        }
        
        if(haux==null){
                error = "Error. No se ha podido encontrar la reserva";
           }
         
        if(format.equals("html")){
            response.setContentType("text/html;charset=UTF-8");
            request.setAttribute("cliente", haux);
            request.setAttribute("tab", "buscarHuesped"); 
            request.setAttribute("error", error); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");       
            dispatcher.forward(request, response);
        }else{
            if(error != null){
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }else{
            
                response.setContentType("text/xml;charset=UTF-8");
                try(PrintWriter out = response.getWriter()){        
                    XStream xstream = new XStream(new DomDriver());
                    xstream.alias("cliente", Huesped.class);
                    xstream.toXML(haux, out);

                }catch(Exception e){

                };
            }
        }
           
    }
}
