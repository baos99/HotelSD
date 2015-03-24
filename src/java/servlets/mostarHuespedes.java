/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import hotel.Huesped;
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
@WebServlet(name = "mostarHuespedes", urlPatterns = {"/mostarHuespedes"})
public class mostarHuespedes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
        
        //ArrayList<Reserva> actuales =  (ArrayList<Reserva>) this.getServletContext().getAttribute("actuales");
         ArrayList<Huesped> huespedes =  (ArrayList<Huesped>) this.getServletContext().getAttribute("huespedes");
       
        //request.setAttribute("reservas", actuales);
        request.setAttribute("huespedes", huespedes); 
       
        //RequestDispatcher dispatcher = request.getRequestDispatcher("mostrarReservas.jsp");       
        RequestDispatcher dispatcher = request.getRequestDispatcher("mostrarHuespedes.jsp");
       
       dispatcher.forward(request, response);
    }

  

}
