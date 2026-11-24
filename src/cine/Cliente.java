package cine;
import java.io.Serializable;
/**
 *
 * @author Luna Ariadna Tito
 */
public class Cliente implements Serializable{
    private String nombre;
    private String dni;
    
    public Cliente(String nombre, String dni){
        this.setDni(dni);
        this.setNombre(nombre);
    }
    
    public String getNombre(){
        return nombre;
    }
    public String getDni(){
        return dni;
    }
    
    public void setNombre(String nombre){
        if(nombre == null && nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (nombre.length() > 20){
            throw new IllegalArgumentException("El nombre no puede superar los 20 caracteres");
        }
        this.nombre = nombre;
    }
    public void setDni(String dni){
        if (dni == null || dni.isBlank()){
            throw new IllegalArgumentException("El DNI no puede estar vacio");
        }
        if (dni.length() < 7 || dni.length() > 8){
            throw new IllegalArgumentException("El DNI debe contener entre 7 y 8 numeros");
        }
        this.dni = dni;
    }
}
