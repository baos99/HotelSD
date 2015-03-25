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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author german
 */
public class ServletBuscarReserva extends HttpServlet {

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
        
        String form = request.getParameter("format");
        
        format = (form==null ? format:form );
        
        String error = null;
        ArrayList<Reserva> reservas = (ArrayList<Reserva>) this.getServletContext().getAttribute("reservas");
     
        String nif = request.getParameter("nif");
        String fentrada = request.getParameter("fentrada");
        Reserva raux = null;
        
        for (Reserva r : reservas) {
           if (nif.equalsIgnoreCase(r.getCliente().getNif()) && fentrada.equalsIgnoreCase(r.getFentrada())){
                raux = r;
                break;
        }
        }  
           if(raux==null){
                error = "Error. No se ha podido encontrar la reserva";
           }
        
        if(format.equals("html")){
            response.setContentType("text/html;charset=UTF-8");
            request.setAttribute("reserva", raux);
            request.setAttribute("tab", "buscarReserva"); 
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
                    xstream.alias("reserva", Reserva.class);
                    xstream.toXML(raux, out);
                }catch(Exception e){}
            
            };
        }
    }
        
}
