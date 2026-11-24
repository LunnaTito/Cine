package cine;
import java.io.Serializable;
/**
 *
 * @author Luna Ariadna Tito
 */
public abstract class Entrada  implements Serializable{
    private final double precioBase;
    
    public Entrada(double precioBase){
        this.precioBase = precioBase;
    }
    public double getPrecioBase(){
        return precioBase;
    }
    public abstract double calcularPrecio();
}
