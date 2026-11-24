
package cine;
import java.io.Serializable;
/**
 *
 * @author Luna Ariadna Tito
 */
public class Pelicula implements Serializable{
    private String titulo;
    private int duracion;    
    
    
    public Pelicula(String titulo, int duracion){
        if(titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException("El titulo no puede estar vacio");
        }
        if (duracion <= 0){
            throw new IllegalArgumentException("La duracion debe ser mayor a 0");
        }
        
        this.titulo = titulo;
        this.duracion = duracion;
        
    }
    public String getTitulo(){
        return titulo;
    }
    public int getDuracion(){
        return duracion;
    }
}
