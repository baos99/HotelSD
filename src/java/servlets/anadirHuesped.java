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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author german
 */
public class anadirHuesped extends HttpServlet {

    /**

     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
     
        ArrayList<Huesped> huespedes =  (ArrayList<Huesped>) this.getServletContext().getAttribute("huespedes");
        
        String nombreHuesped = (String) request.getParameter("name");
        String apellidosHuesped = (String) request.getParameter("surname");
        String nif = (String) request.getParameter("dni");
        String fecha = (String) request.getParameter("date");
        String direccion = (String) request.getParameter("dir");
        String localidad = (String) request.getParameter("loc");
        String codigo_postal = (String) request.getParameter("cp");
        String provincia = (String) request.getParameter("prov");
        String movil = (String) request.getParameter("mov");
        String telefono = (String) request.getParameter("tel");        
        String email = (String) request.getParameter("email");
        
        Integer i_telefono;
        Integer i_movil;
        Integer i_codigo_postal= Integer.parseInt(codigo_postal);
        
        if (telefono != null){
            i_telefono= Integer.parseInt(telefono);
        }else{
            i_telefono = 0;
        }
        if (movil != null){
            i_movil= Integer.parseInt(movil);
        }else{
            i_movil=0;
        }
        
        Huesped newHuesped = new Huesped(nombreHuesped, apellidosHuesped, nif, fecha, direccion, localidad, i_codigo_postal, provincia, i_telefono, i_movil, email);
                
        huespedes.add(newHuesped);

        request.setAttribute("huespedes", huespedes);
        request.setAttribute("tab", "buscarHuesped"); 
        response.sendRedirect("/HotelSD");
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
