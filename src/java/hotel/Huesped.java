package hotel;

/**
 *
 * @author Javier
 */
public class Huesped {

    private String nombre;
    private String apellidos;
    private String nif;
    private String fecha;
    private String direccion;
    private String localidad;
    private int codigo_postal;
    private String provincia;
    private int telefono;
    private int movil;
    private String mail;
    
    public Huesped(String nombre, String apellidos, String nif, String fecha, String direccion, String localidad, int cp, String provincia, int telefono, int movil, String mail) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.fecha = fecha;
        this.direccion = direccion;
        this.localidad = localidad;
        this.codigo_postal = cp;
        this.provincia = provincia;
        this.telefono = telefono;
        this.movil = movil;
        this.mail = mail;
    }
    
    public Huesped(String nombre, String apellidos, String nif, String fecha, String direccion, String localidad, int cp, String provincia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.fecha = fecha;
        this.direccion = direccion;
        this.localidad = localidad;
        this.codigo_postal = cp;
        this.provincia = provincia;
        this.telefono = 0;
        this.movil = 0;
        this.mail = null;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(int codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }


    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getMovil() {
        return movil;
    }

    public void setMovil(int movil) {
        this.movil = movil;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    
}
