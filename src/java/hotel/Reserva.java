package hotel;

import java.util.Date;

/**
 *
 * @author Javier
 */
public class Reserva {
  
    private Huesped cliente;
    private int habitacion;
    private String fentrada;
    private String fsalida;
    
    public Reserva(Huesped cliente, int habitacion, String fentrada, String fsalida) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fentrada = fentrada;
        this.fsalida = fsalida;
    }

    public Huesped getCliente() {
        return cliente;
    }

    public void setCliente(Huesped cliente) {
        this.cliente = cliente;
    }

    public int getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(int habitacion) {
        this.habitacion = habitacion;
    }

    public String getFentrada() {
        return fentrada;
    }

    public void setFentrada(String fentrada) {
        this.fentrada = fentrada;
    }

    public String getFsalida() {
        return fsalida;
    }

    public void setFsalida(String fsalida) {
        this.fsalida = fsalida;
    }
    
    

}
