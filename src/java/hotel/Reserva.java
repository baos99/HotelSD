package hotel;

import java.util.Date;

/**
 *
 * @author Javier
 */
public class Reserva {
  
    private Huesped cliente;
    private int habitacion;
    private Date fentrada;
    private Date fsalida;
    
    public Reserva(Huesped cliente, int habitacion, Date fentrada, Date fsalida) {
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

    public Date getFentrada() {
        return fentrada;
    }

    public void setFentrada(Date fentrada) {
        this.fentrada = fentrada;
    }

    public Date getFsalida() {
        return fsalida;
    }

    public void setFsalida(Date fsalida) {
        this.fsalida = fsalida;
    }
    
    

}
