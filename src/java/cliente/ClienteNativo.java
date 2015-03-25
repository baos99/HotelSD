/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import hotel.Huesped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClienteNativo {

    static Pattern NOMBRE = Pattern.compile("[A-Za-záéíóúÁÉÍÓÚñÑ ]+");
    static Pattern DNI = Pattern.compile("(\\d{8})([-]?)([A-Za-z]{1})");
    static Pattern CP = Pattern.compile("(\\d{5})");
    static Pattern TLFN = Pattern.compile("(\\d{9})");
    static Pattern FECHA = Pattern.compile("(\\d{2})([/])(\\d{2})([/])(\\d{4})");
    static Pattern MAIL = Pattern.compile("([A-Za-z]+)([@])([A-Za-z]+)([.][A-Za-z]+)?");
    static Matcher mat = null;
    static String opcion = "-1";

    
    private static void showMenu() {
        String aux = "    === HOTEL ===";
       aux += "\n\nBienvenido al programa de gestión del hotel\n";
       aux += "\n     1) Buscar cliente";
       aux += "\n     2) Añadir cliente";
       aux += "\n     3) Buscar reserva";
       aux += "\n     4) Añadir reserva";
       aux += "\n\n     0) Salir\n";
       aux += "\nPor favor, escoga una accion: ";
       
       System.out.print(aux);
    }
    
    private static void showMenuBuscarCliente() {
       String aux = "\n\n\n == BUSCAR CLIENTE ==";
       aux += "\nCómo desea búscar al cliente?\n";
       aux += "\n     1) Por su NIF";
       aux += "\n     2) Por su nombre y apellidos";
       aux += "\n\n     0) Atrás\n";
       aux += "\nPor favor, escoga una accion: ";
       
       System.out.print(aux);
    }
    
    static void buscarClienteNIF() throws MalformedURLException {
        String nif;
        System.out.print("\n\nIntroduzca el NIF del cliente [INTRO para Atrás]: ");
        Scanner sc = new Scanner(System.in);
        nif = sc.nextLine();

        mat = DNI.matcher(nif);

        while (!(mat.matches())) {
            if(nif.isEmpty()){
                break;
            }else{
               System.out.print("\nERROR. NIF nválido, por favor introduzcalo de nuevo: ");
               nif = sc.nextLine();
               mat = DNI.matcher(nif); 
            }
            
        }

        if (!nif.isEmpty()) {

            HttpURLConnection urlConn;
            URL url = new URL("http://localhost:8080/HotelSD/ServletBuscarHuesped?format=xml&nif=" + nif);

            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("GET");
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                XStream xstream = new XStream(new DomDriver());
                xstream.alias("cliente", Huesped.class);

                Huesped aux = (Huesped) xstream.fromXML(response.toString());
                in.close();

                mostrarCliente(aux);

            } catch (IOException e) {
                System.out.println("Fallo de connexxion: " + e.toString());
            }
        }
    }
    
    static void buscarClienteNombreApellidos() throws MalformedURLException {
        String name;
        System.out.print("\n\nIntroduzca el Nombre del cliente [INTRO para Atrás]: ");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();

        mat = NOMBRE.matcher(name);

        while (!(mat.matches())) {
            if(name.isEmpty()){
                break;
            }else{
               System.out.print("\nERROR. Nombre nválido, por favor introduzcalo de nuevo: ");
               name = sc.nextLine();
               mat = NOMBRE.matcher(name); 
            }
            
        }
        
        
        if (!name.isEmpty()) {
            
            String surname;
            System.out.print("\n\nIntroduzca los apellidos del cliente: ");
            surname = sc.nextLine();

            mat = NOMBRE.matcher(surname);

            while (!(mat.matches())) {
                   System.out.print("\nERROR. Apellidos inválidos, por favor introduzcalo de nuevo: ");
                   surname = sc.nextLine();
                   mat = NOMBRE.matcher(surname); 

            }
            
            surname = surname.replace(' ', '+');

            HttpURLConnection urlConn;
            URL url = new URL("http://localhost:8080/HotelSD/ServletBuscarHuesped?format=xml&name=" + name + "&surname="+surname);

            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("GET");
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                XStream xstream = new XStream(new DomDriver());
                xstream.alias("cliente", Huesped.class);

                Huesped aux = (Huesped) xstream.fromXML(response.toString());
                in.close();

                mostrarCliente(aux);

            } catch (IOException e) {
                System.out.println("Fallo de connexxion: " + e.toString());
            }
        }
    }
    
    static void mostrarCliente(Huesped huesped) throws MalformedURLException {
        
        String aux = "\n\n  == DATOS DEL CLIENTE ==\n";
        
        
        aux += "Nombre:" + huesped.getNombre();
        aux += "\nApellidos:" + huesped.getApellidos();
        aux += "\nNacimiento:" + huesped.getFecha();
        aux += "\nDomicilio:" + huesped.getDireccion();
        if (huesped.getTelefono() == 0) {
            aux += "\nFijo: N/A";
        } else {
            aux += "\nFijo:" + huesped.getTelefono();
        }
        if (huesped.getMovil() == 0) {
            aux += "\nMovil: Vacío";
        } else {
            aux += "\nMovil:" + huesped.getMovil();
        }
        if (huesped.getMail().isEmpty()) {
            aux += "\nEmail: Vacío";
        } else {
            aux += "\nCorreo:" + huesped.getMail();
        }
        
        aux += "\n\n ¿Qué desea hacer?\n";
        aux += "\n     1) Modificar";
        aux += "\n     2) Borrar";
        aux += "\n\n     0) Arás\n";
        aux += "\nPor favor, escoga una accion: ";
        
        Scanner sc = new Scanner(System.in);
        opcion="-1";
        while(! (opcion.equals("0") || opcion.equals("1") ||opcion.equals("2"))){
            System.out.print(aux);
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":modificarCliente(huesped);break;
                case "2":borrarCliente(huesped);break;
                case "0":opcion="-1";break;
                default:System.out.println("Opción incorrecta");break;
            }
                
        }
   
    }
    
    public static void modificarCliente(Huesped huesped) throws MalformedURLException{
        Scanner sc = new Scanner(System.in);
        
        System.out.print("\nIntroduzca el nombre ["+huesped.getNombre()+"]:");
        String nombre = sc.nextLine();
        
        if(nombre.isEmpty()){
            nombre = huesped.getNombre();
        }
        mat = NOMBRE.matcher(nombre);
        while (!(mat.matches())) {
            System.out.println("Nombre incorrecto ["+huesped.getNombre()+"]:");
            nombre = sc.nextLine();
            mat = NOMBRE.matcher(nombre);
        }
        
        System.out.print("\nIntroduzca los apellidos ["+huesped.getApellidos()+"]:");
        String apellidos = sc.nextLine();
        if(apellidos.isEmpty()){
            apellidos = huesped.getApellidos();
        }
        mat = NOMBRE.matcher(apellidos);
        while (!(mat.matches())) {
            System.out.print("\nApellidos incorrectos ["+huesped.getApellidos()+"]:");
            apellidos = sc.nextLine();
            mat = NOMBRE.matcher(apellidos);
        }
        String nif = huesped.getNif();
        
        System.out.print("\nIntroduzca la fecha de nacimiento ["+huesped.getFecha()+"]:");
        String fecha = sc.nextLine();
        if(fecha.isEmpty()){
            fecha = huesped.getFecha();
        }
        
        
        System.out.print("\nIntroduzca la direccion ["+huesped.getDireccion()+"]:");
        String direccion = sc.nextLine();
        if(direccion.isEmpty()){
            direccion = huesped.getDireccion();
        }
        
        mat = NOMBRE.matcher(direccion);
        while (!(mat.matches())) {
            System.out.print("\nDireccion incorrecta ["+huesped.getDireccion()+"]:");
            direccion = sc.nextLine();
            mat = NOMBRE.matcher(direccion);
        }
        
        System.out.print("\nIntroduzca la provincia ["+huesped.getProvincia()+"]:");
        String provincia = sc.nextLine();
        if(provincia.isEmpty()){
            provincia = huesped.getProvincia();
        }
        
        mat = NOMBRE.matcher(provincia);
        while (!(mat.matches())) {
            System.out.print("\nProvincia incorrecta ["+huesped.getProvincia()+"]:");
            provincia = sc.nextLine();
            mat = NOMBRE.matcher(provincia);
        }
        
        System.out.print("\nIntroduzca el codigo postal ["+huesped.getCodigo_postal()+"]:");
        String codpost = sc.nextLine();
        
        if(codpost.isEmpty()){
            codpost = huesped.getCodigo_postal()+"";
        }
        
        mat = CP.matcher(codpost);
        while (!(mat.matches())) {
            System.out.print("\nCP incorrecto ["+huesped.getCodigo_postal()+"]:");
            codpost = sc.nextLine();
            mat = CP.matcher(codpost);
        }
        
        System.out.print("\nIntroduzca la localidad ["+huesped.getLocalidad()+"]:");
        String localidad = sc.nextLine();
        if(localidad.isEmpty()){
            localidad = huesped.getLocalidad();
        }
        
        mat = NOMBRE.matcher(localidad);
        while (!(mat.matches())) {
            System.out.print("\nLocalidad incorrecta ["+huesped.getLocalidad()+"]:");
            localidad = sc.nextLine();
            mat = NOMBRE.matcher(localidad);
        }
        
        
        System.out.print("\nIntroduzca el fijo ["+huesped.getTelefono()+"]:");
        String fijo = sc.nextLine();

        mat = TLFN.matcher(fijo);
        while (!(mat.matches()) && fijo != "") {
            System.out.print("\n Número de telefono incorrecto");
            fijo = sc.nextLine();
            mat = TLFN.matcher(fijo);
        }
        System.out.print("\nIntroduzca el movil ["+huesped.getMovil()+"]:");
        String movil = sc.nextLine();
        mat = TLFN.matcher(movil);
        while (!(mat.matches()) && movil.isEmpty()) {
            System.out.print("\nNúmero de telefono incorrecto: ");
            movil = sc.nextLine();
            mat = TLFN.matcher(movil);
        }
        System.out.print("\nIntroduzca el correo ["+huesped.getMail()+"]:");
        String correo = sc.nextLine();
        mat = MAIL.matcher(correo);
        while (!(mat.matches()) && correo.isEmpty()) {
            System.out.print("\nCorreo Incorrecto: ");
            correo = sc.nextLine();
            mat = MAIL.matcher(correo);
        }
        
        URL    url            = new URL("http://localhost:8080/HotelSD/ServletModificarHuesped");
        
            try {
                

                String urlParameters  = "nif="+huesped.getNif()+"&name="+nombre+"&surname="+apellidos+"&date"+fecha+"&dir="+direccion+"&loc="+localidad+"&cp="+codpost+"&prov="+provincia+"&mov"+movil+"&tel"+fijo+"&email="+correo;
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setFollowRedirects(false);
                conn.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write(urlParameters);
            writer.flush();

            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            writer.close();
            reader.close();

            } catch (IOException e) {
                
            }
            
        
        
        
    }
    
    public static void borrarCliente(Huesped huesped) throws MalformedURLException{
            URL    url            = new URL("http://localhost:8080/HotelSD/ServletBorrarHuesped");
        
            try {
                

                String urlParameters  = "nif="+huesped.getNif();
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setFollowRedirects(false);
                conn.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write(urlParameters);
            writer.flush();

            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            writer.close();
            reader.close();

            } catch (IOException e) {
                
            }
            
            
            
    }
    
    public static void main(String argv[]) throws MalformedURLException {
        
        Scanner sc = new Scanner(System.in);
        while (!opcion.equals("0")) {
            showMenu();
        
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    while(!opcion.equals("0")){
                        showMenuBuscarCliente();
                        opcion = sc.nextLine();
                        switch (opcion) {
                            case "1":buscarClienteNIF();break;
                            case "2":buscarClienteNombreApellidos();break;
                            default:System.out.println("Opción incorrecta");break;
                        }
                    //buscarCliente();
                    }
                    opcion = "-1";
                    break;
                case "2":
                    //aniadirCliente();
                    break;
                case "3":
                    //buscarReservas();
                    break;
                case "4":
                    //aniadirReserva();
                    break;
                case "0":break; //Salir
                default:
                    System.out.println("Opción incorrecta");
            }

        }

    }
/*

    static void buscarClienteNIF() {
        ArrayList<Huesped> huespedes = null;
        String nif;
        System.out.println("Introduzca el NIF (00000000x ó 00000000-x) o escriba salir");
        Scanner sc = new Scanner(System.in);
        nif = sc.nextLine();

        mat = patronDNI.matcher(nif);

        while (!(mat.matches() || nif.equals("salir"))) {
            System.out.println("Nif incorrecto");
            nif = sc.nextLine();
            mat = patronDNI.matcher(nif);
        }

        if (!nif.equals("salir")) {

            HttpURLConnection urlConn;
            URL url;
            DataOutputStream printout;
            DataInputStream input;
            url = null;
            try {

                url = new URL("http://localhost:8080/PracticaSD/ServletMain?operacion=BuscarCliente&nifCliente=" + nif + "&paramBusqueda=NIF&cliente=desktopApp");
            } catch (java.net.MalformedURLException e) {
            }
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("POST");
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        urlConn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                Hotel hotel = new Hotel();
                XStream xstream = new XStream(new DomDriver());
                ArrayList<Huesped> aux = new ArrayList<>();
                xstream.alias("Resultados", aux.getClass());
                xstream.alias("Huesped", Huesped.class);
                xstream.alias("Domicilio", Domicilio.class);
                xstream.alias("Calendario", Calendar.class);

                huespedes = (ArrayList<Huesped>) xstream.fromXML(response.toString());
                in.close();

                mostrarClientes(huespedes);

            } catch (IOException e) {
                System.out.println("connect: connection failed " + e.toString());
            }
        }
    }

    static void buscarClienteNombre() {
        String nombre;

        System.out.println("Introduzca el nombre y apellidos o escriba salir");
        Scanner sc = new Scanner(System.in);
        nombre = sc.nextLine();
        mat = patronLetras.matcher(nombre);
        while (!(mat.matches())) {
            System.out.println("Nombre incorrecto");
            nombre = sc.nextLine();
            mat = patronLetras.matcher(nombre);
        }

        nombre = nombre.replaceAll(" ", "+");

        if (!nombre.equals("salir")) {

            HttpURLConnection urlConn;
            URL url;
            DataOutputStream printout;
            DataInputStream input;
            url = null;
            try {

                url = new URL("http://localhost:8080/PracticaSD/ServletMain?&operacion=BuscarCliente&nombre=" + nombre + "&paramBusqueda=nombre&cliente=desktopApp");
            } catch (java.net.MalformedURLException e) {
            }
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("POST");
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        urlConn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                Hotel hotel = new Hotel();
                XStream xstream = new XStream(new DomDriver());
                ArrayList<Huesped> aux = new ArrayList<>();
                xstream.alias("Resultados", aux.getClass());
                xstream.alias("Huesped", Huesped.class);
                xstream.alias("Domicilio", Domicilio.class);
                xstream.alias("Calendario", Calendar.class);

                ArrayList<Huesped> huespedes = (ArrayList<Huesped>) xstream.fromXML(response.toString());
                in.close();

                mostrarClientes(huespedes);

            } catch (IOException e) {
                System.out.println("connect: connection failed " + e.toString());
            }
        }

    }

    static void aniadirCliente() {
        System.out.println("Introduzca el nombre");
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        mat = patronLetras.matcher(nombre);
        while (!(mat.matches())) {
            System.out.println("Nombre incorrecto");
            nombre = sc.nextLine();
            mat = patronLetras.matcher(nombre);
        }
        System.out.println("Introduzca los apellidos");
        String apellidos = sc.nextLine();
        mat = patronLetras.matcher(apellidos);
        while (!(mat.matches())) {
            System.out.println("Apellidos incorrectos");
            apellidos = sc.nextLine();
            mat = patronLetras.matcher(apellidos);
        }
        System.out.println("Introduzca el NIF");
        String nif = sc.nextLine();
        mat = patronDNI.matcher(nif);
        while (!(mat.matches())) {
            System.out.println("DNI incorrecto");
            nif = sc.nextLine();
            mat = patronLetras.matcher(nif);
        }
        System.out.println("Introduzca la fecha de nacimiento");
        String nacimiento = sc.nextLine();
        mat = patronFecha.matcher(nacimiento);
        while (!(mat.matches())) {
            System.out.println("Fecha incorrecta");
            nacimiento = sc.nextLine();
            mat = patronLetras.matcher(nacimiento);
        }
        System.out.println("Introduzca la direccion");
        String direccion = sc.nextLine();
        mat = patronLetras.matcher(direccion);
        while (!(mat.matches())) {
            System.out.println("Nombre incorrecto");
            direccion = sc.nextLine();
            mat = patronLetras.matcher(direccion);
        }
        System.out.println("Introduzca la provincia");
        String provincia = sc.nextLine();
        mat = patronLetras.matcher(provincia);
        while (!(mat.matches())) {
            System.out.println("Nombre incorrecto");
            provincia = sc.nextLine();
            mat = patronLetras.matcher(provincia);
        }
        System.out.println("Introduzca el codigo postal");
        String codpost = sc.nextLine();
        mat = patronCP.matcher(codpost);
        while (!(mat.matches())) {
            System.out.println("CP incorrecto");
            codpost = sc.nextLine();
            mat = patronLetras.matcher(codpost);
        }
        System.out.println("Introduzca la localidad");
        String localidad = sc.nextLine();
        mat = patronLetras.matcher(localidad);
        while (!(mat.matches())) {
            System.out.println("Nombre incorrecto");
            localidad = sc.nextLine();
            mat = patronLetras.matcher(localidad);
        }
        System.out.println("Introduzca el fijo");
        String fijo = sc.nextLine();
        mat = patronTlf.matcher(fijo);
        while (!(mat.matches())) {
            System.out.println("Número de telefono incorrecto");
            fijo = sc.nextLine();
            mat = patronLetras.matcher(fijo);
        }
        System.out.println("Introduzca el movil");
        String movil = sc.nextLine();
        mat = patronTlf.matcher(movil);
        while (!(mat.matches())) {
            System.out.println("Número de telefono incorrecto");
            movil = sc.nextLine();
            mat = patronLetras.matcher(movil);
        }
        System.out.println("Introduzca el correo");
        String correo = sc.nextLine();
        mat = patronEmail.matcher(correo);
        while (!(mat.matches())) {
            System.out.println("Correo Incorrecto");
            correo = sc.nextLine();
            mat = patronLetras.matcher(correo);
        }

        HttpURLConnection urlConn;
        URL url;
        DataOutputStream printout;
        DataInputStream input;
        url = null;
        String loc = null;
        try {

            url = new URL("http://localhost:8080/PracticaSD/ServletMain?operacion=AniadirCliente&cliente=desktopApp");
        } catch (java.net.MalformedURLException e) {
        }
        try {
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setFollowRedirects(false);
            urlConn.setRequestMethod("POST");
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);

            urlConn.setUseCaches(false);
            printout = new DataOutputStream(urlConn.getOutputStream());
            String content = "nombreCliente=" + nombre + "&apellidoCliente=" + apellidos + "&nifCliente=" + nif + "&fechaCliente="
                    + nacimiento + "&direccionCliente=" + direccion + "&provinciaCliente=" + provincia + "&cpostalCliente=" + codpost
                    + "&localidadCliente=" + localidad + "&tlffijoCliente=" + fijo + "&tlfmovilCliente=" + movil + "&emailCliente=" + correo;
            printout.writeBytes(content);
            printout.flush();
            printout.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            System.out.println(response);

            in.close();

        } catch (IOException e) {
            System.out.println("connect: connection failed " + e.toString());
        }
    }

    static void postModificarCliente(String content) {
        HttpURLConnection urlConn;
        URL url;
        DataOutputStream printout;
        DataInputStream input;
        url = null;
        try {

            url = new URL("http://localhost:8080/PracticaSD/ServletMain?operacion=ModificarCliente&cliente=desktopApp");
        } catch (java.net.MalformedURLException e) {
        }
        try {
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setFollowRedirects(false);
            urlConn.setRequestMethod("POST");
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);

            urlConn.setUseCaches(false);
            printout = new DataOutputStream(urlConn.getOutputStream());
            printout.writeBytes(content);
            printout.flush();
            printout.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            System.out.println(response);

            in.close();

        } catch (IOException e) {
            System.out.println("connect: connection failed " + e.toString());
        }
    }

    static void mostrarCliente(Huesped huesped) {
        System.out.println("Nombre:" + huesped.getNombre());
        System.out.println("Apellidos:" + huesped.getApellidos());
        System.out.println("Nacimiento:" + calendarToString(huesped.getNacimiento()));
        System.out.println("Domicilio:" + huesped.getDomicilio().toString());
        if (huesped.getFijo() == null) {
            System.out.println("Fijo: Vacío");
        } else {
            System.out.println("Fijo:" + huesped.getFijo());
        }
        if (huesped.getMovil() == null) {
            System.out.println("Movil: Vacío");
        } else {
            System.out.println("Movil:" + huesped.getMovil());
        }
        if (huesped.getEmail().equalsIgnoreCase("")) {
            System.out.println("Email: Vacío");
        } else {
            System.out.println("Correo:" + huesped.getEmail());
        }
    }

    static void mostrarClientes(ArrayList<Huesped> huespedes) {
        int i = 0;
        if (huespedes == null) {
            System.out.println("La búsqueda no ha obnenido ningún resultado");
        } else {
            for (Huesped huesped : huespedes) {
                i++;
                String op = "default";
                String nuevoNombre = huesped.getNombre(), nuevoApellidos = huesped.getApellidos(), nuevoNacimiento = calendarToString(huesped.getNacimiento()),
                        nuevoNif = huesped.getNif(), nuevoDireccion = huesped.getDomicilio().getDireccion(), nuevoLocalidad = huesped.getDomicilio().getLocalidad(),
                        nuevoCodPost = Integer.toString(huesped.getDomicilio().getCodpost()), nuevoProvincia = huesped.getDomicilio().getProvincia();
                String nuevoFijo = "";
                String nuevoMovil = "";
                String nuevoCorreo = "";
                try {
                    nuevoFijo = Integer.toString(huesped.getFijo());
                } catch (Exception e) {
                }
                try {
                    nuevoMovil = Integer.toString(huesped.getMovil());
                } catch (Exception e) {
                }
                try {
                    nuevoCorreo = huesped.getEmail();
                } catch (Exception e) {
                }
                System.out.println("Mostrando resultado " + i + "de " + huespedes.size());

                mostrarCliente(huesped);

                System.out.println("¿Qué desea modificar? Escriba borrar o continuar si lo desea.");
                Scanner sc = new Scanner(System.in);
                while (!op.equals("continuar") && !op.equals("borrar")) {
                    op = sc.nextLine().toLowerCase();
                    switch (op) {
                        case "nombre":
                            System.out.println("Introduca el nuevo nombre: ");
                            nuevoNombre = sc.nextLine();
                            mat = patronLetras.matcher(nuevoNombre);
                            while (!(mat.matches())) {
                                System.out.println("Nombre incorrecto");
                                nuevoNombre = sc.nextLine();
                                mat = patronLetras.matcher(nuevoNombre);
                            }

                            break;
                        case "apellidos":
                            System.out.println("Introduca los nuevos apellidos: ");
                            nuevoApellidos = sc.nextLine();
                            mat = patronLetras.matcher(nuevoApellidos);
                            while (!(mat.matches())) {
                                System.out.println("Apellidos incorrectos");
                                nuevoApellidos = sc.nextLine();
                                mat = patronLetras.matcher(nuevoApellidos);
                            }

                            break;
                        case "nif":
                            System.out.println("Introduca el nuevo NIF: ");
                            nuevoNif = sc.nextLine();
                            mat = patronDNI.matcher(nuevoNif);
                            while (!(mat.matches())) {
                                System.out.println("DNI incorrecto");
                                nuevoNif = sc.nextLine();
                                mat = patronLetras.matcher(nuevoNif);
                            }
                            break;
                        case "domicilio":
                            System.out.println("Introduca la nueva direccion: ");
                            nuevoDireccion = sc.nextLine();
                            mat = patronLetras.matcher(nuevoDireccion);
                            while (!(mat.matches())) {
                                System.out.println("Nombre incorrecto");
                                nuevoDireccion = sc.nextLine();
                                mat = patronLetras.matcher(nuevoDireccion);
                            }
                            System.out.println("Introduca la nueva localidad: ");
                            nuevoLocalidad = sc.nextLine();
                            mat = patronLetras.matcher(nuevoLocalidad);
                            while (!(mat.matches())) {
                                System.out.println("Nombre incorrecto");
                                nuevoLocalidad = sc.nextLine();
                                mat = patronLetras.matcher(nuevoLocalidad);
                            }
                            System.out.println("Introduca el nuevo código postal (5 digitos) ");
                            nuevoCodPost = sc.nextLine();
                            mat = patronCP.matcher(nuevoCodPost);
                            while (!(mat.matches())) {
                                System.out.println("CP incorrecto");
                                nuevoCodPost = sc.nextLine();
                                mat = patronLetras.matcher(nuevoCodPost);
                            }
                            System.out.println("Introduca la nueva provincia: ");
                            nuevoProvincia = sc.nextLine();
                            mat = patronLetras.matcher(nuevoProvincia);
                            while (!(mat.matches())) {
                                System.out.println("Nombre incorrecto");
                                nuevoProvincia = sc.nextLine();
                                mat = patronLetras.matcher(nuevoProvincia);
                            }
                            break;
                        case "nacimiento":
                            System.out.println("Introduca la nueva fecha de nacimiento: (dd/mm/aaaa");
                            nuevoNacimiento = sc.nextLine();
                            mat = patronFecha.matcher(nuevoNacimiento);
                            while (!(mat.matches())) {
                                System.out.println("Fecha incorrecta");
                                nuevoNacimiento = sc.nextLine();
                                mat = patronLetras.matcher(nuevoNacimiento);
                            }
                            break;
                        case "fijo":
                            System.out.println("Introduca el nuevo teléfono fijo: ");
                            nuevoFijo = sc.nextLine();
                            mat = patronTlf.matcher(nuevoFijo);
                            while (!(mat.matches())) {
                                System.out.println("Número de telefono incorrecto");
                                nuevoFijo = sc.nextLine();
                                mat = patronLetras.matcher(nuevoFijo);
                            }
                            break;
                        case "movil":
                            System.out.println("Introduca la nueva direccion: ");
                            nuevoMovil = sc.nextLine();
                            mat = patronTlf.matcher(nuevoMovil);
                            while (!(mat.matches())) {
                                System.out.println("Numero de telefono incorrecto");
                                nuevoMovil = sc.nextLine();
                                mat = patronLetras.matcher(nuevoMovil);
                            }
                            break;
                        case "correo":
                            System.out.println("Introduca el nuevo correo: ");
                            nuevoCorreo = sc.nextLine();
                            mat = patronEmail.matcher(nuevoCorreo);
                            while (!(mat.matches())) {
                                System.out.println("Correo Incorrecto");
                                nuevoCorreo = sc.nextLine();
                                mat = patronLetras.matcher(nuevoCorreo);
                            }
                            break;
                        case "continuar":
                            String content = "operacion=ModificarCliente&nombreCliente=" + nuevoNombre + "&apellidoCliente=" + nuevoApellidos + "&nifCliente=" + nuevoNif + "&fechaCliente="
                                    + nuevoNacimiento + "&direccionCliente=" + nuevoDireccion + "&provinciaCliente=" + nuevoProvincia + "&cpostalCliente=" + nuevoCodPost
                                    + "&localidadCliente=" + nuevoLocalidad + "&tlffijoCliente=" + nuevoFijo + "&tlfmovilCliente=" + nuevoMovil + "&emailCliente=" + nuevoCorreo;
                            postModificarCliente(content);
                            break;
                        case "borrar":
                            borrarCliente("nifCliente=" + huesped.getNif());
                            break;
                        default:
                            System.out.println("Opción incorrecta");
                            break;

                    }

                }
                System.out.println("Búsqueda finalizada");
            }
        }
    }

    static void buscarReservas() {
        String fecha;

        System.out.println("Escrba la fecha de entrada o si lo desea, salir");
        Scanner sc = new Scanner(System.in);
        fecha = sc.nextLine();

        if (!fecha.equals("salir")) {

            HttpURLConnection urlConn;
            URL url;
            DataOutputStream printout;
            DataInputStream input;
            url = null;
            try {

                url = new URL("http://localhost:8080/PracticaSD/ServletMain?&operacion=BuscarReserva&fecha=" + fecha + "&cliente=desktopApp");
            } catch (java.net.MalformedURLException e) {
            }
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("POST");
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        urlConn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                Hotel hotel = new Hotel();
                XStream xstream = new XStream(new DomDriver());
                ArrayList<Reserva> aux = new ArrayList<>();
                xstream.alias("Resultados", aux.getClass());
                xstream.alias("Reserva", Reserva.class);
                xstream.alias("Huesped", Huesped.class);
                xstream.alias("Domicilio", Domicilio.class);
                xstream.alias("Calendario", Calendar.class);

                in.close();
                ArrayList<Reserva> reservas = (ArrayList<Reserva>) xstream.fromXML(response.toString());

                mostrarReservas(reservas);

            } catch (IOException e) {
                System.out.println("connect: connection failed " + e.toString());
            }
        }

    }

    static void mostrarReservas(ArrayList<Reserva> reservas) {
        int i = 0;
        if (reservas == null) {
            System.out.println("La búsqueda no ha obnenido ningún resultado");
        } else {
            for (Reserva reserva : reservas) {
                i++;
                String nuevoHabitacion = Integer.toString(reserva.getHabitacion()), nuevoEntrada = calendarToString(reserva.getEntrada()), nuevoSalida = calendarToString(reserva.getSalida());

                System.out.println("Mostrando resultado " + i + " de " + reservas.size());

                mostrarReserva(reserva);

                System.out.println("Si desea modificar la reserva, escriba el NIF del cliente, para continuar deje en blanco");

                Scanner sc = new Scanner(System.in);
                Pattern pat = Pattern.compile("(\\d{8})([-]?)([A-Za-z]{1})");

                String nif = sc.nextLine();
                Matcher mat = pat.matcher(nif);
                while (mat.matches()) {
                    if (nif.equals(reserva.getHuesped().getNif())) {
                        String op = "default";

                        while (!op.equals("continuar") && !op.equals("borrar")) {
                            op = sc.nextLine().toLowerCase();
                            switch (op) {
                                case "habitacion":
                                    System.out.println("Introduca la nueva habitacion: ");
                                    nuevoHabitacion = sc.nextLine();
                                    break;
                                case "entrada":
                                    System.out.println("Introduca la nueva fecha de entrada: ");
                                    nuevoEntrada = sc.nextLine();
                                    break;
                                case "salida":
                                    System.out.println("Introduca la nueva fecha de salida: ");
                                    nuevoSalida = sc.nextLine();
                                    break;
                                case "continuar":
                                    String content = "nifCliente=" + reserva.getHuesped().getNif() + "&antiguaHab=" + reserva.getHabitacion() + "&nuevaHab=" + nuevoHabitacion + "&fechaEntr=" + nuevoEntrada + "&fechaSal=" + nuevoSalida;
                                    postModificarReserva(content);
                                    break;
                                case "borrar":
                                    borrarReserva("nifCliente=" + reserva.getHuesped().getNif() + "&antiguaHab=" + reserva.getHabitacion());
                                    break;
                                default:
                                    System.out.println("Opción incorrecta");
                                    break;

                            }

                        }
                        break;
                    } else {
                        System.out.println("NIF incorrecto, introduzca de nuevo&");
                        nif = sc.nextLine();
                        mat = pat.matcher(nif);
                    }

                }
                System.out.println("Búsqueda finalizada");
            }
        }
    }

    static void mostrarReserva(Reserva reserva) {
        System.out.println("--Reserva a nombre de " + reserva.getHuesped().getNombre() + " " + reserva.getHuesped().getApellidos() + "--");
        System.out.println("Habitación: " + reserva.getHabitacion());
        System.out.println("Entrada: " + calendarToString(reserva.getEntrada()));
        System.out.println("Salida: " + calendarToString(reserva.getSalida()));
    }

    static void postModificarReserva(String content) {
        HttpURLConnection urlConn;
        URL url;
        DataOutputStream printout;
        DataInputStream input;
        url = null;
        try {

            url = new URL("http://localhost:8080/PracticaSD/ServletMain?operacion=ModificarReserva&cliente=desktopApp");
        } catch (java.net.MalformedURLException e) {
        }
        try {
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setFollowRedirects(false);
            urlConn.setRequestMethod("POST");
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);

            urlConn.setUseCaches(false);
            printout = new DataOutputStream(urlConn.getOutputStream());
            printout.writeBytes(content);
            printout.flush();
            printout.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            System.out.println(response);

            in.close();

        } catch (IOException e) {
            System.out.println("connect: connection failed " + e.toString());
        }
    }

    static void borrarCliente(String content) {
        HttpURLConnection urlConn;
        URL url;
        DataOutputStream printout;
        DataInputStream input;
        url = null;
        try {

            url = new URL("http://localhost:8080/PracticaSD/ServletMain?operacion=BorrarCliente&cliente=desktopApp");
        } catch (java.net.MalformedURLException e) {
        }
        try {
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setFollowRedirects(false);
            urlConn.setRequestMethod("POST");
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);

            urlConn.setUseCaches(false);
            printout = new DataOutputStream(urlConn.getOutputStream());
            printout.writeBytes(content);
            printout.flush();
            printout.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            System.out.println(response);

            in.close();

        } catch (IOException e) {
            System.out.println("connect: connection failed " + e.toString());
        }
    }

    static void borrarReserva(String content) {
        HttpURLConnection urlConn;
        URL url;
        DataOutputStream printout;
        DataInputStream input;
        url = null;
        try {

            url = new URL("http://localhost:8080/PracticaSD/ServletMain?operacion=BorrarReserva&cliente=desktopApp");
        } catch (java.net.MalformedURLException e) {
        }
        try {
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setFollowRedirects(false);
            urlConn.setRequestMethod("POST");
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);

            urlConn.setUseCaches(false);
            printout = new DataOutputStream(urlConn.getOutputStream());
            printout.writeBytes(content);
            printout.flush();
            printout.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            System.out.println(response);

            in.close();

        } catch (IOException e) {
            System.out.println("connect: connection failed " + e.toString());
        }
    }

    static String calendarToString(Calendar c) {
        String ceroMes = "";
        String ceroDia = "";
        if (c.getTime().getMonth() < 9) {
            ceroMes = "0";
        }
        if (c.getTime().getDate() < 10) {
            ceroDia = "0";
        }

        return ceroMes + (c.getTime().getMonth() + 1) + "/" + ceroDia + c.getTime().getDate() + "/" + (c.getTime().getYear() + 1900);
    }

    static void aniadirReserva() {
        System.out.println("Introduzca la fecha de entrada");
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        System.out.println("Introduzca la fecha de salida");
        String salida = sc.nextLine();
        System.out.println("Introduzca el NIF del cliente");
        String nif = sc.nextLine();

        HttpURLConnection urlConn;
        URL url;
        DataOutputStream printout;
        DataInputStream input;
        url = null;
        String loc = null;
        try {

            url = new URL("http://localhost:8080/PracticaSD/ServletMain?operacion=AniadirReserva&cliente=desktopApp");
        } catch (java.net.MalformedURLException e) {
        }
        try {
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setFollowRedirects(false);
            urlConn.setRequestMethod("POST");
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);

            urlConn.setUseCaches(false);
            printout = new DataOutputStream(urlConn.getOutputStream());
            String content = "fechaEnt=" + entrada + "&fechaSal=" + salida + "&nifReserva=" + nif;
            printout.writeBytes(content);
            printout.flush();
            printout.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            System.out.println(response);

            in.close();

        } catch (IOException e) {
            System.out.println("connect: connection failed " + e.toString());
        }
    }

    */

}
