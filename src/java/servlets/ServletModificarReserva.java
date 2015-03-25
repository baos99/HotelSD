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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author german
 */
public class ServletModificarReserva extends HttpServlet {

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
        Reserva raux = null;
        String nif = request.getParameter("nif");
        String fentrada = (String) request.getParameter("fentrada");
        for (Reserva r : reservas) {
            if (nif.equalsIgnoreCase(r.getCliente().getNif()) && fentrada.equalsIgnoreCase(r.getFentrada())) {
                raux = r;
                break;
            }
        }
        
        try {
            raux.setFentrada(request.getParameter("fentrada"));
        } catch (Exception e) {}
        try {
            raux.setFsalida(request.getParameter("fsalida"));
        } catch (Exception e) {}
        try {
            raux.setHabitacion(Integer.parseInt(request.getParameter("numHab")));
        } catch (Exception e) {}
        
        request.setAttribute("huespedes", huespedes);
        request.setAttribute("tab", "modificarReserva"); 
        response.sendRedirect(request.getContextPath());
        
        }
}
