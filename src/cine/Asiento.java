
package cine;
import java.io.Serializable;
/**
 *
 * @author Luna
 */
public class Asiento implements Serializable{
    private String fila;
    private int numero;
    private boolean disponible;
    
    public Asiento(String fila, int numero){
        this.fila = fila;
        this.numero = numero;
        this.disponible = true;
    }
    
    public String getFila(){
        return fila;
    }
    public int getNumero(){
        return numero;
    }
    public boolean isDisponible(){
        return disponible;
    }
    public void setDisponible(boolean disponible){
    this.disponible = disponible;
}
}
