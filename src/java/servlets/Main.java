/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import hotel.Huesped;
import hotel.Reserva;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Javier
 */
public class Main extends HttpServlet {

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
         response.setContentType("text/html;charset=UTF-8");
        
         ArrayList<Reserva> reservas =  (ArrayList<Reserva>) this.getServletContext().getAttribute("reservas");
         ArrayList<Huesped> huespedes =  (ArrayList<Huesped>) this.getServletContext().getAttribute("huespedes");
       
        request.setAttribute("reservas", reservas);
        request.setAttribute("huespedes", huespedes); 
       
        //RequestDispatcher dispatcher = request.getRequestDispatcher("mostrarReservas.jsp");       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");       
       
       dispatcher.forward(request, response);
    }
    
}


