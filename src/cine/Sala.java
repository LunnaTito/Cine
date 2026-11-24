package cine;
import java.io.Serializable;
/**
 *
 * @author Luna
 */
public class Sala implements Serializable{
    private int numero;
    private int capacidad;
    
    public Sala(int numero, int capacidad){
        if(numero <=0){
            throw new IllegalArgumentException("El numero de la sala debe ser mayor a 0");
        }
        if(capacidad <= 0){
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        
        this.numero = numero;
        this.capacidad = capacidad;
    }
    
    public int getNumero(){
        return numero;
    }
    public int getCapacidad(){
        return capacidad;
    }
    
}
