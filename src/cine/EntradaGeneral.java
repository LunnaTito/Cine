package cine;

/**
 *
 * @author Luna
 */
public class EntradaGeneral extends Entrada{
    
    public EntradaGeneral(double precioBase){
        super(precioBase);
    }
    
    @Override
    public double calcularPrecio(){
        return getPrecioBase();
    }
    
}
