package cine;
import java.util.ArrayList;
import java.io.Serializable;
/**
 *
 * @author Luna
 */
public class Reserva implements Serializable{
    private int codigo;
    private Cliente cliente;
    private Funcion funcion;
    private ArrayList<Asiento> asientos;
    private boolean activa;
    private Entrada entrada;
    
    public Reserva(int codigo, Cliente cliente, Funcion funcion){
        
        if(codigo <= 0){
            throw new IllegalArgumentException("El codigo debe ser mayor a 0");
        }
        if(cliente == null){
            throw new IllegalArgumentException("La reserva debe tener un cliente");
        }
        if(funcion == null){
            throw new IllegalArgumentException("La reserva debe tener una funcion");
        }
        
        
        this.codigo = codigo;
        this.cliente = cliente;
        this.funcion = funcion;
        this.asientos = new ArrayList<Asiento>();
        this.activa = true;
    }
    
    
    public int getCodigo(){
        return codigo;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public Funcion getFuncion(){
        return funcion;
    }
    public ArrayList<Asiento> getAsientos(){
        return asientos;
    }
    public boolean isActiva(){
        return activa;
    }
    public Entrada entrada(){
        return entrada;
    }
    
    public void agregarAsiento(Asiento asiento){
        if(asiento.isDisponible()){
            asientos.add(asiento);
            asiento.setDisponible(false);
        }    
    }
    public void agregarAsientos(ArrayList<Asiento> nuevosAsientos){
        for (int i = 0; i < nuevosAsientos.size(); i++ ){
            agregarAsiento(nuevosAsientos.get(i));
        }
    }
    public void cancelarReserva(){
        activa = false;
    }
    public void setEntrada(Entrada entrada){
        this.entrada = entrada;
    }
    public double calcularTotalReserva(){
        if(entrada != null){
            return entrada.calcularPrecio() * asientos.size();
        }else{
            return 0;
        }
    }
    
}
