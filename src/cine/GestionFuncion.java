package cine;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.Serializable;
/**
 *
 * @author Luna Ariadna Tito
 */
public class GestionFuncion implements Serializable{
    private ArrayList<Funcion> funciones;
    
    public GestionFuncion(){
        funciones = new ArrayList<Funcion>();
    }
    
    public void agregarFuncion(Funcion nuevaFuncion){
        
        for (int i = 0; i < funciones.size(); i++){
            Funcion funcionExistente = funciones.get(i);
            
            if(funcionExistente.getSala().getNumero() == nuevaFuncion.getSala().getNumero() && funcionExistente.getFecha().isEqual(nuevaFuncion.getFecha())){
                
                LocalDateTime inicioExistente = LocalDateTime.of(funcionExistente.getFecha(), funcionExistente.getHorario());
                
                LocalDateTime finExistente = inicioExistente.plusMinutes(funcionExistente.getPelicula().getDuracion());
                
                LocalDateTime inicioNuevo = LocalDateTime.of(nuevaFuncion.getFecha(), nuevaFuncion.getHorario());
                
                LocalDateTime finNuevo = inicioNuevo.plusMinutes(nuevaFuncion.getPelicula().getDuracion());
                
                if (inicioNuevo.isBefore(finExistente) && finNuevo.isAfter(inicioExistente)){
                    throw new IllegalArgumentException("La sala ya tiene una funcion programada en ese horario");
                }
            }
        }
        funciones.add(nuevaFuncion);
    }
    
    public ArrayList<Funcion> getFuncion(){
        return funciones;
    }
}
