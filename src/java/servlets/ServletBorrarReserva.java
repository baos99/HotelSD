/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

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
public class ServletBorrarReserva extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            response.setContentType("text/html;charset=UTF-8");
            ArrayList<Huesped> huespedes =  (ArrayList<Huesped>) this.getServletContext().getAttribute("huespedes");
            ArrayList<Reserva> reservas = (ArrayList<Reserva>) this.getServletContext().getAttribute("reservas");
            boolean borrado = false;
            String error = null;
            
            String nif = request.getParameter("nif");
            String fentrada = (String) request.getParameter("fentrada");
            for (Reserva r : reservas) {
                if (nif.equalsIgnoreCase(r.getCliente().getNif()) && fentrada.equalsIgnoreCase(r.getFentrada())) {
                    reservas.remove(r);
                    borrado = true;
                    break;
                }
            }
            
            if(borrado){
                error = "Error. No se ha podido borrar la reserva";
            }
            
            request.setAttribute("reservas", reservas);
            request.setAttribute("tab", "administrarReservas"); 
            request.setAttribute("error", error); 
            response.sendRedirect(request.getContextPath());
    }
}
