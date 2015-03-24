/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.context;

import hotel.Huesped;
import hotel.Reserva;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



/**
 *
 * @author Javier
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        // IMPLEMENTAR CARGAR ARCHIVO
        
        ArrayList<Reserva> pasadas = new ArrayList<>();
        ArrayList<Reserva> actuales = new ArrayList<>();
        ArrayList<Reserva> futuras = new ArrayList<>();
        ArrayList<Huesped> huespedes = new ArrayList<>();
        
        Huesped h1 = new Huesped("José", "Pérez Pérez", "12345678A", "5/1/1975", "Calle de la Piruleta", "Mostoles", 12345,"Madrid");
        Huesped h2 = new Huesped("Javier", "Jiménez del Peso", "49142652R", "6/7/1993","Versalles", "Loranca", 28942,"Fuenlabrada",999999999,666666667,"javier@aebd.com");
        Huesped h3 = new Huesped("Germán", "Alonso Azcutia", "49103449J", "3/8/1992", "Calle la calle","Loranca",28942,"Fuenlabrada",888888888,76666666,"german@aebd.com");
        
        actuales.add(new Reserva(h1, 101, new Date(2015, 3, 1),new Date(2015, 3, 1)));
        actuales.add(new Reserva(h2, 001, new Date(2015, 1, 5),new Date(2015, 1, 15)));
        actuales.add(new Reserva(h3, 205, new Date(2015, 6, 14),new Date(2015, 6, 17)));
        
        sce.getServletContext().setAttribute("pasadas", pasadas);
        sce.getServletContext().setAttribute("actuales", actuales);
        sce.getServletContext().setAttribute("futuras", futuras);
        sce.getServletContext().setAttribute("huespedes", huespedes);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}
