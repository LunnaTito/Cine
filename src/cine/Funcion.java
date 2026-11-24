package cine;
import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 *
 * @author Luna Ariadna Tito
 */
public class Funcion implements Serializable{
    private Pelicula pelicula;
    private Sala sala;
    private LocalDate fecha;
    private LocalTime horario;
    private double precio;
    private ArrayList<Asiento> asientos;
    
    public Funcion(Pelicula pelicula, Sala sala, LocalDate fecha, LocalTime horario, double precio){
        if (horario == null){
            throw new IllegalArgumentException("El horario no puede estar vacio");
        }
        if(precio <= 0){
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.horario = horario;
        this.precio = precio;
        
        this.asientos = new ArrayList<Asiento>();
        
        for (int i = 1; i <= sala.getCapacidad(); i++){
            Asiento asiento = new Asiento("A", i);
            asientos.add(asiento);
        }
    }
    
    public Pelicula getPelicula(){
        return pelicula;
    }
    public Sala getSala(){
        return sala;
    }
    public LocalDate getFecha(){
        return fecha;
    }
    public LocalTime getHorario(){
        return horario;
    }
    public double getPrecio(){
        return precio;
    }
    public ArrayList<Asiento> getAsientos(){
        return asientos;
    }
    
    public boolean sePuedeReservar(){
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        
        if(fecha.isAfter(fechaActual)){
            return true;
        }else if(fecha.isEqual(fechaActual) && horario.isAfter(horaActual)){
            return true;
        }else{
            return false;
        }
    }
}
