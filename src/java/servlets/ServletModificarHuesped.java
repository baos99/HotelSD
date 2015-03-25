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
public class ServletModificarHuesped extends HttpServlet {

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
        String nif = request.getParameter("nif");
        Huesped haux = null;
        for (Huesped h : huespedes) {
            if (nif.equalsIgnoreCase(h.getNif())) {
                haux = h;
                break;
            }
        } 
            try {
                haux.setNombre(request.getParameter("name"));
            } catch (Exception e) {}
            try {
                haux.setApellidos(request.getParameter("surname"));
            } catch (Exception e) {}
            try {
                haux.setDireccion(request.getParameter("dir"));
            } catch (Exception e) {}
            try {
                haux.setLocalidad(request.getParameter("loc"));
            } catch (Exception e) {}
            try {
                haux.setCodigo_postal(Integer.parseInt(request.getParameter("cp")));
            } catch (Exception e) {}
            try {
                haux.setProvincia(request.getParameter("prov"));
            } catch (Exception e) {}
            try {
                haux.setTelefono(Integer.parseInt(request.getParameter("tel")));
            } catch (Exception e) {}
            try {
                haux.setMovil(Integer.parseInt(request.getParameter("mov")));
            } catch (Exception e) {haux.setMovil(666);}
            try {
                haux.setMail(request.getParameter("email"));
            } catch (Exception e) {}
           
        
        
        
        request.setAttribute("huespedes", huespedes);
        request.setAttribute("tab", "modificarHuespedes");
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
        rd.forward(request, response);
       
    }
}
