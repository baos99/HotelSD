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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author german
 */
public class ServletBorrarHuesped extends HttpServlet {

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
            boolean borrado = false;
            String error = null;
            
            String nif = request.getParameter("nif");
            for (Huesped h : huespedes) {
                if (nif.equalsIgnoreCase(h.getNif())) {
                    huespedes.remove(h);
                    break;
                }
            }
            
              if(borrado){
                error = "Error. No se ha podido borrar el cliente";
            }
            
            request.setAttribute("huespedes", huespedes);
            request.setAttribute("tab", "administrarHuespedes"); 
            request.setAttribute("error", error); 
            response.sendRedirect(request.getContextPath());
    }
}
