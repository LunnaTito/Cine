package cine;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
/**
 *
 * @author Luna Ariadna Tito
 */
public class Cine {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        Persistencia persistencia = new Persistencia();
        ArrayList<Object> datos = persistencia.cargarDatos();
        
        GestionFuncion gestionFuncion;
        GestionReserva gestion;
        Funcion funcion1;
        
        String nombre;
        String dni;
        Cliente cliente1 = null;
        int opcion = 0;
        
        if (datos.size() == 2){
            gestionFuncion = (GestionFuncion) datos.get(0);
            gestion = (GestionReserva) datos.get(1);
            
            ArrayList<Funcion> funcionesGuardadas = gestionFuncion.getFuncion();

        }else{
            Pelicula pelicula1 = new Pelicula("Rapidos y furiosos", 106);
            Pelicula pelicula2 = new Pelicula("Obsesion", 105);
            Pelicula pelicula3 = new Pelicula("La isla siniestra", 138);

            Sala sala1 = new Sala(1, 10);
            Sala sala2 = new Sala(2, 12);
            
            funcion1 = new Funcion(pelicula1, sala1, LocalDate.of(2026, 11, 25), LocalTime.of(20, 0), 8000);
            Funcion funcion2 = new Funcion(pelicula2, sala2, LocalDate.of(2026, 11, 25), LocalTime.of(22, 0), 8000);
            Funcion funcion3 = new Funcion(pelicula1, sala1, LocalDate.of(2026, 11, 25), LocalTime.of(23, 0), 8000);
            Funcion funcion4 = new Funcion(pelicula3, sala2, LocalDate.of(2026, 11, 25), LocalTime.of(18,0), 8000);
            
            gestionFuncion = new GestionFuncion();
            
            gestionFuncion.agregarFuncion(funcion1);
            gestionFuncion.agregarFuncion(funcion2);
            gestionFuncion.agregarFuncion(funcion3);
            gestionFuncion.agregarFuncion(funcion4);
            
            gestion = new GestionReserva();
            
            System.out.println("Se crearon lis primeros datos");
        }
        ArrayList<Funcion> funciones = gestionFuncion.getFuncion();       
        System.out.println("|| Bienvenido a nuestro cine! ||");

        do {
            System.out.println("Ingrese su nombre: ");
            nombre = input.nextLine();
            
            System.out.println("Ingrese su DNI: ");
            dni = input.nextLine();

            try {
                cliente1 = new Cliente(nombre, dni);
                System.out.println("Bienvenido " + cliente1.getNombre() + "!");

            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        } while (cliente1 == null);
        
        do{
            mostrarMenu(cliente1);
            try{
                opcion = input.nextInt();
                
                switch(opcion){
                    case 1:
                        mostrarCartelera(funciones);
                        break;
                    case 2:
                        realizarReserva(input, cliente1, funciones, gestion);
                        persistencia.guardarDatos(gestionFuncion, gestion);
                        persistencia.exportarTXT(gestion);
                        break;
                    case 3:
                        gestion.mostrarReservas();
                        break;
                    case 4:
                        buscarReserva(input, gestion);
                        break;
                    case 5:
                        cancelarReserva(input, gestion);
                        persistencia.guardarDatos(gestionFuncion, gestion);
                        persistencia.exportarTXT(gestion);
                        break;
                    case 6:
                        System.out.println("Saliendo ...");
                        break;
                    default:
                        System.out.println("Opcion Invalida");
                        break;
                }
            }catch (Exception e){
                System.out.println("ERROR. Debe ingresar un numero");
                input.nextLine();
            }
        }while(opcion != 6);
    }    
    
    
    
    
    public static void mostrarMenu(Cliente nombreCliente){
        System.out.println("-------------- CINE -------------");
        System.out.println("Bienvenido/a " + nombreCliente.getNombre());
        
        System.out.println("1- Mostrar cartelera");
        System.out.println("2- Realizar reserva");
        System.out.println("3- Mostrar reserva");
        System.out.println("4- Buscar reserva");
        System.out.println("5-Cancelar reserva");
        System.out.println("6- Salir");
        System.out.println("Ingrese una opcion: ");
    }
    
    public static void mostrarCartelera(ArrayList<Funcion> funciones){
        
        for (int i = 0; i < funciones.size(); i++){
            System.out.println("-------------- FUNCION " + (i + 1) +"-------------------");
            
            System.out.println("Pelicula: " + funciones.get(i).getPelicula().getTitulo());
            System.out.println("Sala: " + funciones.get(i).getSala().getNumero());
            System.out.println("Fecha: "+ funciones.get(i).getFecha());
            System.out.println("Horario: " + funciones.get(i).getHorario());
            System.out.println("Precio: " + funciones.get(i).getPrecio());
            
            System.out.println("-------------------------------------");
        }
    }
    
    public static void realizarReserva(Scanner input, Cliente cliente1, ArrayList<Funcion> funciones, GestionReserva gestion){
        
        Reserva reservaCreada = null;
        int numeroFuncion;
        Funcion funcionSeleccionada;
        int cantidadDisponible = 0;
        int cantidadAsientosAReservar;
        Entrada entrada;
        int tipoEntrada;
        
        ArrayList<Asiento> asientosSeleccionados = new ArrayList<Asiento>();
        mostrarCartelera(funciones);
        
        do{
            System.out.println("Selecciones la funcion que desee ver: ");
            numeroFuncion = input.nextInt();
            
            if (numeroFuncion < 1 || numeroFuncion > funciones.size()){
                System.out.println("ERROR. La funcion que usted quiere ver es incorrecta");
            }
        }while(numeroFuncion < 1 || numeroFuncion > funciones.size());
        
        funcionSeleccionada = funciones.get(numeroFuncion - 1);
        
        if(funcionSeleccionada.sePuedeReservar() == false){
            System.out.println("ERROR. La funcion ya no esta disponible para reservar");
            return;
        }
        
        System.out.println("La pelicula seleccionada es: " + funcionSeleccionada.getPelicula().getTitulo());
        System.out.println("Asientos disponibles: ");
        
        for(int i = 0; i < funcionSeleccionada.getAsientos().size(); i ++){
            Asiento asiento = funcionSeleccionada.getAsientos().get(i);
            
            if(asiento.isDisponible()){
                System.out.println(asiento.getFila() + asiento.getNumero());
                cantidadDisponible ++;
            }
        }
        if(cantidadDisponible == 0){
            System.out.println("No quedan asientos disponibles");
            return;
        }
        do {
            System.out.println("Cantidad de asientos a reservar: ");
            cantidadAsientosAReservar = input.nextInt();
            
            if(cantidadAsientosAReservar <= 0 || cantidadAsientosAReservar > cantidadDisponible){
                System.out.println("ERROR. Ingrese una cantidad entre 1 y " + cantidadDisponible);
            }
        } while (cantidadAsientosAReservar <= 0 || cantidadAsientosAReservar > cantidadDisponible);
        
        for(int i = 0; i < cantidadAsientosAReservar; i ++){
            System.out.println("Ingrese el numero del asiento: ");
            int numeroAsientoASeleccionar = input.nextInt();
            
            if(numeroAsientoASeleccionar >= 1 && numeroAsientoASeleccionar <= funcionSeleccionada.getAsientos().size()){
                
                Asiento asiento = funcionSeleccionada.getAsientos().get(numeroAsientoASeleccionar - 1);
                boolean asientoYaSeleccionado = false;
                
                for(int j = 0; j < asientosSeleccionados.size(); j ++){
                    if(asientosSeleccionados.get(j).getNumero() == numeroAsientoASeleccionar){
                        asientoYaSeleccionado = true;
                    }
                }
                if(asiento.isDisponible() && !asientoYaSeleccionado){
                    asientosSeleccionados.add(asiento);
                    System.out.println("Asiento seleccionado correctamente");
                }else{
                    System.out.println("El asiento esta ocupado o ya fue seleccionado");
                    i --;
                }
            }else{
                System.out.println("ERROR. El numero de asiento que selecciono es incorrecto");
                i --;
            }
        }
        do{
            System.out.println("Seleccione el tipo de entrada: ");
            System.out.println("1- General");
            System.out.println("2- Estudiante");
            
            tipoEntrada = input.nextInt();
            
            if(tipoEntrada != 1 && tipoEntrada != 2){
                System.out.println("ERROR. Tipo de entrada invalido");
            }
            
        }while (tipoEntrada != 1 && tipoEntrada != 2);
        if(tipoEntrada == 1){
            entrada = new EntradaGeneral(funcionSeleccionada.getPrecio());
        }else {
            entrada = new EntradaEstudiante(funcionSeleccionada.getPrecio());
        }
        try{
            reservaCreada = gestion.crearReserva(cliente1, funcionSeleccionada, asientosSeleccionados, entrada);
        }catch(IllegalArgumentException e){
            System.out.println("ERROR: " + e.getMessage());
        }
        if(reservaCreada != null){
            
            System.out.println("Su reserva se creo correctamente!\n");
            
            System.out.println("------------- COMPROBANTE --------------");
            System.out.println("Codigo: " + reservaCreada.getCodigo());
            System.out.println("Cliente: " + cliente1.getNombre());
            System.out.println("Pelicula: " + funcionSeleccionada.getPelicula().getTitulo());
            System.out.println("Fecha: " + funcionSeleccionada.getFecha());
            System.out.println("Horario: " + funcionSeleccionada.getHorario());
            System.out.println("Sala: " + funcionSeleccionada.getSala().getNumero());

            System.out.println("Asientos reservados: ");

            for(int i= 0; i < asientosSeleccionados.size(); i ++){
                Asiento asiento = asientosSeleccionados.get(i);            
                System.out.println(asiento.getFila() + asiento.getNumero());
            }
            System.out.println("------------------------------------------");
            System.out.println("Total: $" + reservaCreada.calcularTotalReserva());
            System.out.println("------------------------------------------");
        }else {
            System.out.println("L a reserva no se pudo crear");
        }
    }
    
    public static void buscarReserva(Scanner input, GestionReserva gestion){
        int codigoBusqueda;
        Reserva reservaBuscada;
        
        System.out.println("Ingrese el codigo de la reserva: ");
        codigoBusqueda = input.nextInt();
        
        reservaBuscada = gestion.buscarReserva(codigoBusqueda);
        
        if(reservaBuscada != null){
            System.out.println("Reserva encontrada");
            System.out.println("Codigo: " + reservaBuscada.getCodigo());
            System.out.println("Cliente: " + reservaBuscada.getCliente().getNombre());
            System.out.println("Pelicula: " + reservaBuscada.getFuncion().getPelicula().getTitulo());
            System.out.println("Total: $" + reservaBuscada.calcularTotalReserva());
            
            if(reservaBuscada.isActiva()){
                System.out.println("Estado: activo");
            }else{
                System.out.println("Estado: cancelada");
            }
        }else{
            System.out.println("No hay ninguna reserva con ese codigo");
        }
    }
    
    public static void cancelarReserva(Scanner input, GestionReserva gestion){
        int codigoReserva;
        
        System.out.println("Ingrese el codigo de la reserva que desea cancelar: ");
        codigoReserva = input.nextInt();
        
        gestion.cancelarReserva(codigoReserva);
    }
}

