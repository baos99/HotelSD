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
        
        ArrayList<Reserva> reservas = new ArrayList<>();
        ArrayList<Huesped> huespedes = new ArrayList<>();
        
        Huesped h1 = new Huesped("Jose", "Perez Perez", "12345678A", "5/1/1975", "Calle de la Piruleta", "Mostoles", 12345,"Madrid");
        Huesped h2 = new Huesped("Javier", "Jimenez del Peso", "49142652R", "6/7/1993","Versalles", "Loranca", 28942,"Fuenlabrada",999999999,666666667,"javier@aebd.com");
        Huesped h3 = new Huesped("German", "Alonso Azcutia", "49103449J", "3/8/1992", "Calle la calle","Loranca",28942,"Fuenlabrada",888888888,76666666,"german@aebd.com");
        
        /*actuales.add(new Reserva(h1, 101, new Date(2015, 3, 1),new Date(2015, 3, 1)));
        actuales.add(new Reserva(h2, 001, new Date(2015, 1, 5),new Date(2015, 1, 15)));
        actuales.add(new Reserva(h3, 205, new Date(2015, 6, 14),new Date(2015, 6, 17)));
        
        */
        reservas.add(new Reserva(h1, 101, "07/02/1994","07/03/1994"));
        reservas.add(new Reserva(h2, 001, "07/02/1994","07/03/1994"));
        reservas.add(new Reserva(h3, 205, "07/02/1994","07/03/1994"));
        
        huespedes.add(h1);huespedes.add(h2);huespedes.add(h3);
        
        sce.getServletContext().setAttribute("reservas", reservas);
        sce.getServletContext().setAttribute("huespedes", huespedes);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}
