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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author german
 */
@WebServlet(name = "anadirReserva", urlPatterns = {"/anadirReserva"})
public class ServletAnadirReserva extends HttpServlet {

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
     
        String error = null;
        ArrayList<Huesped> huespedes =  (ArrayList<Huesped>) this.getServletContext().getAttribute("huespedes");
        ArrayList<Reserva> reservas = (ArrayList<Reserva>) this.getServletContext().getAttribute("reservas");
        
        String nif =  request.getParameter("nifReserva");
        String entrada = request.getParameter("dateEntrada");
        String salida =  request.getParameter("dateSalida");
        Huesped haux = null;
        /*
        Date entradad = null;
        Date salidad = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try{
            entradad = df.parse(entrada);
            salidad = df.parse(salida);
        }catch (Exception e){}
      */
        //busqueda por dni, y se saca el objeto.
        for (Huesped h : huespedes) {
            if (nif.equalsIgnoreCase(h.getNif())) {
                haux = h;
                break;
            }
        }
        
        if (haux != null){ 
            int numHabitacion = (int) Math.floor(Math.random()*(599-100+1)+100);  
            Reserva newReserva = new Reserva(haux, numHabitacion, entrada, salida);
        
            reservas.add(newReserva);
            
        }else{
            error = "Error. No se ha podido añadir el cliente";
        }
        
        request.setAttribute("reservas", reservas);
        request.setAttribute("tab", "anadirReserva"); 
        request.setAttribute("error", error); 
       
       RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");       
       
       dispatcher.forward(request, response);
       
        
        
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

