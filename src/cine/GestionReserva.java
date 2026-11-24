
package cine;
import java.util.ArrayList;
import java.io.Serializable;
/**
 *
 * @author Luna
 */
public class GestionReserva implements Serializable{
    private ArrayList<Reserva> reservas;
    public int siguienteCodigo;
    
    public GestionReserva(){
        reservas = new ArrayList<Reserva>();
        siguienteCodigo = 1;
    }
    public ArrayList<Reserva> getReserva(){
        return reservas;
    }
    
    public void agregarReserva(Reserva reserva){
        reservas.add(reserva);
    }
    
    public Reserva crearReserva(Cliente cliente, Funcion funcion, ArrayList<Asiento> asientosSeleccionados, Entrada entrada){
        
        if(cliente == null){
            throw new IllegalArgumentException("Debe ingresar un cliente");
        }
        if(funcion == null){
            throw new IllegalArgumentException("Debe seleccionar una fucnion");
        }
        if(asientosSeleccionados == null){
            throw new IllegalArgumentException("Debe seleccionar los asientos");
        }
        if(entrada == null){
            throw new IllegalArgumentException("Debe seleccionar un tipo de entrada");
        }
        
        
        if(!funcion.sePuedeReservar()){
            throw new IllegalArgumentException("La funcion ya comenzo o finalizo");
        }
        
        if (asientosSeleccionados.size() == 0){
            throw new IllegalArgumentException("Debe seleccionar al menos un asiento");
        }
        
        for(int i = 0; i < asientosSeleccionados.size(); i ++){
            
            Asiento asientoSeleccionado = asientosSeleccionados.get(i);
            boolean perteneceFuncion = false;
            
            for(int j = 0; j < i; j++){
                if(asientoSeleccionado == asientosSeleccionados.get(j)){
                    throw new IllegalArgumentException("Usted no puede seleccionar el mismo asiento mas de una vez");
                }
            }
            
            for (int j = 0; j < funcion.getAsientos().size(); j++){
                if(asientoSeleccionado == funcion.getAsientos().get(j)){
                    perteneceFuncion = true;
                }
            }
            if(!perteneceFuncion){
                throw new IllegalArgumentException("El asiento que selecciono no pertenece a esta funcion");
            }
            if(!asientoSeleccionado.isDisponible()){
                throw new IllegalArgumentException("Uno de los asientos que selecciono no esta disponible");
            }
        }
        Reserva reserva = new Reserva(siguienteCodigo, cliente, funcion);
        siguienteCodigo ++;
        
        reserva.setEntrada(entrada);
        reserva.agregarAsientos(asientosSeleccionados);
        agregarReserva(reserva);
        return reserva;
    }
        
    
    public Reserva buscarReserva(int codigo){
        for (int i = 0; i < reservas.size(); i++){
            if (reservas.get(i).getCodigo() == codigo){
                return reservas.get(i);
            }
        }
        return null;
    }
    
    public void cancelarReserva(int codigo){
        Reserva reserva = buscarReserva(codigo);
        
        if(reserva != null && reserva.isActiva()){
            
            reserva.cancelarReserva();
            
            for(int i = 0; i < reserva.getAsientos().size(); i++){
                reserva.getAsientos().get(i).setDisponible(true);
            }
            System.out.println("Su reserva codigo " + reserva.getCodigo() + " se cancelo correctamente.");
        }else {
            System.out.println("No se pudo cancelar ninguna reserva con ese codigo.");
        }
    }
    
    public void mostrarReservas(){
        for (int i = 0; i < reservas.size(); i++){
            
            System.out.println("Codigo: " + reservas.get(i).getCodigo());
            System.out.println("Cliente: " + reservas.get(i).getCliente().getNombre());
            System.out.println("Pelicula: " + reservas.get(i).getFuncion().getPelicula().getTitulo());
            System.out.println("Estado: " + reservas.get(i).isActiva());
            
            System.out.println("--------------------------------------------");
        }
    }
}
