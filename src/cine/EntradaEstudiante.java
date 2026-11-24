package cine;

/**
 *
 * @author Luna
 */
public class EntradaEstudiante extends Entrada {
    public EntradaEstudiante(double precioBase){
        super(precioBase);
    }
    
    @Override
    public double calcularPrecio(){
        return getPrecioBase() * 0.75;
    }
}
