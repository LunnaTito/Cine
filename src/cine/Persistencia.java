package cine;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *
 * @author Luna
 */
public class Persistencia {
    
    public void guardarDatos(GestionFuncion gestionFuncion, GestionReserva gestionReserva){
        
        ArrayList<Object> datos = new ArrayList<Object>();
        
        datos.add(gestionFuncion);
        datos.add(gestionReserva);
        
        try(ObjectOutputStream salida = new ObjectOutputStream(Files.newOutputStream(Paths.get("cine.dat")))){
            salida.writeObject(datos);
            System.out.println("Datos guardados correctamente");
        }catch(IOException e){
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }
    public ArrayList<Object> cargarDatos(){
        
        ArrayList<Object> datos = new ArrayList<Object>();
        
        try(ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(Paths.get("cine.dat")))){
            
            datos = (ArrayList<Object>) entrada.readObject();
            System.out.println("Datos recuperados correctamente");
        }catch(IOException e){
            System.out.println("No se pudieron cargar los datos: " + e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("No se encontro la clase" + e.getMessage());
        }
        return datos;
    }
    public void exportarTXT(GestionReserva gestionReserva){
        ArrayList<Reserva> reservas = gestionReserva.getReserva();
        String contenido = "--------- Reservas del cine -----------\n\n";
        
        for (int i = 0 ; i < reservas.size(); i++){
            Reserva reserva = reservas.get(i);
            
            contenido = contenido + "Codigo: " + reserva.getCodigo() + "\n";
            contenido = contenido + "Cliente: " + reserva.getCliente().getNombre() + "\n";
            contenido = contenido + "Pelicula: " + reserva.getFuncion().getPelicula().getTitulo() + "\n";
            
            if (reserva.isActiva()){
                contenido = contenido + "Estado: Activa\n";
            }else {
                contenido = contenido + "Estado: Cancelada\n";
            }
            contenido = contenido + "Total: $" + reserva.calcularTotalReserva() + "\n\n";
        }
        try{
            Files.write(Paths.get("reservas.txt"), contenido.getBytes(StandardCharsets.UTF_8));
            System.out.println("Archivo TXT exportado correctamente");
        }catch (IOException e){
            System.out.println("Error al exportar archivo TXT: " + e.getMessage());
        }
    }
}
