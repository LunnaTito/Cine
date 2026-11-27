package cine;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
/**
 *
 * @author Luna
 */

public class VentanaPrincipal extends Application{
    private GestionFuncion gestionFuncion;
    private GestionReserva gestion;
    private Persistencia persistencia;
    
    @Override
    public void start(Stage ventana){
        cargarDatos();
        
        Label titulo = new Label("Bienvenido/a");
        titulo.setFont(new Font("Century Gothic",50));
        titulo.setStyle("-fx-text-fill: #FDFDFF; -fx-font-weight: bold;");
        
        Label nombre = new Label("Ingrese su nombre: ");
        nombre.setFont(new Font("Century Gothic", 25));
        TextField campoNombre = new TextField();
        campoNombre.setMaxWidth(250);
        campoNombre.setStyle("-fx-background-color: #BEBDC7;-fx-faint-focus-color: transparent;-fx-background-radius: 10; -fx-focus-color: transparent;-fx-font-weight: bold;");
        nombre.setStyle("-fx-text-fill: #777691;-fx-font-weight: bold;");
                
        Label dni = new Label("Ingrese su DNI: ");
        dni.setFont(new Font("Century Gothic", 25));
        TextField campoDni = new TextField();
        campoDni.setMaxWidth(250);
        campoDni.setStyle("-fx-background-color: #BEBDC7;-fx-faint-focus-color: transparent; -fx-background-radius: 10; -fx-focus-color: transparent; -fx-font-weight: bold;");
        dni.setStyle("-fx-text-fill: #777691;-fx-font-weight: bold;");
                
        Button botonIngresar = new Button("Ingresar");
        botonIngresar.setPrefWidth(180);
        botonIngresar.setPrefHeight(38);  
        botonIngresar.setMinSize(180, 38);
        botonIngresar.setMaxSize(180, 38);
        botonIngresar.setStyle("-fx-background-color: #F7F5FF; -fx-text-fill: #10102C; -fx-background-radius: 18; -fx-font-family: 'Century Gothic'; -fx-font-size: 20px; -fx-font-weight: bold;");       
        
        Label mensaje = new Label();
        mensaje.setStyle("-fx-text-fill: #777691; -fx-font-weight: bold;");
        mensaje.setFont(new Font("Century Gothic", 15));
               
        botonIngresar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                try{
                    String nombreIngresado = campoNombre.getText();
                    String dniIngresado = campoDni.getText();                   
                    Cliente cliente = new Cliente(nombreIngresado, dniIngresado);
                    pantallaMenu(ventana, cliente);                   
                }catch(IllegalArgumentException e){
                    mensaje.setText(e.getMessage());
                }
            }       
        });
        VBox contenedor = new VBox(10);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: #10102C;");
        
        contenedor.getChildren().add(titulo);
        VBox.setMargin(titulo, new Insets(0,0,50,0));
        contenedor.getChildren().add(nombre);
        contenedor.getChildren().add(campoNombre);
        contenedor.getChildren().add(dni);
        contenedor.getChildren().add(campoDni);
        contenedor.getChildren().add(botonIngresar);
        VBox.setMargin(botonIngresar, new Insets(20,0,0,0));
        contenedor.getChildren().add(mensaje);
        
        Scene escena = new Scene(contenedor, 950, 800);
               
        ventana.setTitle("CINE");
        ventana.setScene(escena);
        ventana.show();
    }
    public void pantallaMenu(Stage ventana, Cliente cliente){
        Label titulo = new Label("Bienvenido/a " + cliente.getNombre() + "!");
        titulo.setFont(new Font(40));
        titulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        
        Button botonCartelera = new Button("Cartelera");
        estiloBoton(botonCartelera);   
        botonCartelera.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                pantallaCartelera(ventana, cliente);
            }
        });             
        Button botonMostrarReservas = new Button("Mostrar reservas");
        estiloBoton(botonMostrarReservas); 
        botonMostrarReservas.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                pantallaMostrarReservas(ventana, cliente);
            }
        });       
        Button botonBuscarReservas = new Button("Buscar Reserva");
        estiloBoton(botonBuscarReservas);  
        botonBuscarReservas.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                pantallaBuscarReserva(ventana, cliente);
            }
        });
        Button botonCancelarReserva = new Button("Cancelar reserva");
        estiloBoton(botonCancelarReserva);  
        botonCancelarReserva.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                pantallaCancelarReserva(ventana, cliente);
            }
        });
        Button botonAdministracion = new Button("Administracion");
        estiloBoton(botonAdministracion);

        botonAdministracion.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                pantallaAdministracion(ventana, cliente);
            }
        });
        
        Button botonSalir = new Button("Salir");
        estiloBotonVolver(botonSalir);  
        botonSalir.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent evento){
            ventana.close();
        }
        });
        
        VBox contenedor = new VBox(20);
        contenedor.setStyle("-fx-background-color: #10102C;");
        contenedor.setAlignment(Pos.CENTER);
        
        contenedor.getChildren().add(titulo);
        VBox.setMargin(titulo, new Insets(0,0,50,0));
        
        contenedor.getChildren().add(botonCartelera);
        contenedor.getChildren().add(botonMostrarReservas);
        contenedor.getChildren().add(botonBuscarReservas);
        contenedor.getChildren().add(botonCancelarReserva);
        contenedor.getChildren().add(botonAdministracion);
        contenedor.getChildren().add(botonSalir);
        
        Scene escena = new Scene(contenedor, 950, 800);        
        ventana.setScene(escena);       
    }
    public void estiloBoton(Button boton){
        boton.setStyle("-fx-background-color: #343453; -fx-text-fill: white; -fx-background-radius: 14; -fx-font-weight: bold; -fx-font-family: 'Century Gothic'; -fx-font-size: 20px;");
        boton.setPrefWidth(250);
        boton.setPrefHeight(42);
    }
    public void estiloBotonVolver(Button boton){
        boton.setStyle("-fx-background-color: #F7F5FF; -fx-text-fill: #10102C; -fx-background-radius: 14; -fx-font-weight: bold; -fx-font-family: 'Century Gothic'; -fx-font-size: 20px;");
        boton.setPrefWidth(250);
        boton.setPrefHeight(42);
    }
    public void cargarDatos(){
        persistencia = new Persistencia();
        ArrayList<Object> datos = persistencia.cargarDatos();
        
  
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
            
            Funcion funcion1 = new Funcion(pelicula1, sala1, LocalDate.of(2026, 11, 28), LocalTime.of(20, 0), 8000);
            Funcion funcion2 = new Funcion(pelicula2, sala2, LocalDate.of(2026, 11, 28), LocalTime.of(22, 0), 8000);
            Funcion funcion3 = new Funcion(pelicula1, sala1, LocalDate.of(2026, 11, 28), LocalTime.of(23, 0), 8000);
            Funcion funcion4 = new Funcion(pelicula3, sala2, LocalDate.of(2026, 11, 28), LocalTime.of(18,0), 8000);
            
            gestionFuncion = new GestionFuncion();
            
            gestionFuncion.agregarFuncion(funcion1);
            gestionFuncion.agregarFuncion(funcion2);
            gestionFuncion.agregarFuncion(funcion3);
            gestionFuncion.agregarFuncion(funcion4);
            
            gestion = new GestionReserva();
        }
    }
    public void pantallaCartelera(Stage ventana, Cliente cliente){
        
        Label titulo = new Label("Cartelera");
        titulo.setFont(new Font(40));
        titulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        VBox.setMargin(titulo, new Insets(0,0,40,0));
        
        Label indicacion = new Label("Seleccione la funcion que desea reservar");
        indicacion.setFont(new Font(18));
        indicacion.setStyle("-fx-text-fill: white;");
        
        VBox contenedor = new VBox(20);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: #10102C;");
        
        
        contenedor.getChildren().add(titulo);
        contenedor.getChildren().add(indicacion);
        
        ArrayList<Funcion> funciones = gestionFuncion.getFuncion();
        
        for(int i = 0; i < funciones.size(); i ++){
            Funcion funcion = funciones.get(i);
            
            Button botonFuncion = new Button("Funcion " + (i+1) + "\n" +
                                        "Pelicula: " + funcion.getPelicula().getTitulo() + "\n" +
                                        "Sala: " +funcion.getSala().getNumero() + "\n" +
                                        "Fecha: " + funcion.getFecha() + "\n" +
                                          "Horario: " + funcion.getHorario() + "\n" +
                                          "Precio $" + funcion.getPrecio());
            
            estiloBoton(botonFuncion);
            botonFuncion.setPrefHeight(500);
            botonFuncion.setPrefHeight(120);
            botonFuncion.setFont(new Font(18));
            botonFuncion.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent evento){
                    pantallaAsientos(ventana, cliente, funcion);
                }
            });
            
            contenedor.getChildren().add(botonFuncion);
        }
        Button botonVolver = new Button("Volver");   
        estiloBotonVolver(botonVolver);
        botonVolver.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent evento){
            pantallaMenu(ventana, cliente);
        }
        });
        
        contenedor.getChildren().add(botonVolver);
        
        Scene escena = new Scene(contenedor, 950, 800);
        ventana.setScene(escena);
        
    }
    public void pantallaAsientos(Stage ventana, Cliente cliente, Funcion funcion){
        
        Label titulo = new Label("Seleccione sus asientos");
        titulo.setFont(new Font("Century Gothic", 40));
        titulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        Label pelicula = new Label(funcion.getPelicula().getTitulo());
        pelicula.setStyle("-fx-text-fill: white;");
        pelicula.setFont(new Font(20));

        VBox contenedor = new VBox(15);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: #10102C;");

        contenedor.getChildren().add(titulo);
        contenedor.getChildren().add(pelicula);

        ArrayList<Asiento> asientos = funcion.getAsientos();
        ArrayList<Asiento> asientosSeleccionados = new ArrayList<Asiento>();
        
        for(int i = 0; i < asientos.size(); i++){
            Asiento asiento = asientos.get(i);

            Button botonAsiento = new Button(asiento.getFila() + asiento.getNumero());
            
            botonAsiento.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent evento){
                    if(asientosSeleccionados.contains(asiento)){
                        asientosSeleccionados.remove(asiento);
                        botonAsiento.setStyle("-fx-background-color: white;");
                    }else{
                        asientosSeleccionados.add(asiento);
                        botonAsiento.setStyle("-fx-background-color: #777691;");
                    }
                }
            });

            if(asiento.isDisponible() == false){
                botonAsiento.setDisable(true);
            }
            contenedor.getChildren().add(botonAsiento);
        }
        Label mensaje = new Label();
        mensaje.setStyle("-fx-text-fill: #ff6666;");
        
        Button botonContinuar = new Button("Continuar");
        estiloBoton(botonContinuar);
        
        botonContinuar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                if(asientosSeleccionados.isEmpty()){
                    mensaje.setText("Debe seleccionar al menos un asiento");
                }else{
                    pantallaEntrada(ventana, cliente, funcion, asientosSeleccionados);
                }
            }
        });
        
        contenedor.getChildren().add(mensaje);
        contenedor.getChildren().add(botonContinuar);
        
        Button botonVolver = new Button("Volver");
        estiloBotonVolver(botonVolver);
        botonVolver.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent evento){
            pantallaCartelera(ventana, cliente);
        }
        });
        contenedor.getChildren().add(botonVolver);

        Scene escena = new Scene(contenedor, 950, 800);
        ventana.setScene(escena);

        }
    public void pantallaEntrada(Stage ventana, Cliente cliente, Funcion funcion, ArrayList<Asiento> asientosSeleccionados){
        Label titulo = new Label("Seleccione el tipo de entrada");
        titulo.setFont(new Font(40));
        titulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Button botonGeneral = new Button("Entrada general");
        estiloBoton(botonGeneral);

        Button botonEstudiante = new Button("Entrada estudiante");
        estiloBoton(botonEstudiante);
        botonGeneral.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                Entrada entrada = new EntradaGeneral(funcion.getPrecio());
                pantallaConfirmacion(ventana, cliente, funcion, asientosSeleccionados, entrada);
            }
        });
        
        botonEstudiante.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                Entrada entrada = new EntradaEstudiante(funcion.getPrecio());
                pantallaConfirmacion(ventana, cliente, funcion, asientosSeleccionados, entrada);
            }
        });

        VBox contenedor = new VBox(20);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: #10102C;");

        contenedor.getChildren().add(titulo);
        contenedor.getChildren().add(botonGeneral);
        contenedor.getChildren().add(botonEstudiante);

        Scene escena = new Scene(contenedor, 950, 800);
        ventana.setScene(escena);
    }
    public void pantallaConfirmacion(Stage ventana, Cliente cliente, Funcion funcion, ArrayList<Asiento> asientosSeleccionados, Entrada entrada){
        Label titulo = new Label("Confirme su reserva");
        titulo.setFont(new Font(40));
        titulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        String asientos = "";

        for(int i = 0; i < asientosSeleccionados.size(); i++){
            Asiento asiento = asientosSeleccionados.get(i);
            asientos = asientos + asiento.getFila() + asiento.getNumero() + " ";
        }

        double total = entrada.calcularPrecio() * asientosSeleccionados.size();

        Label informacion = new Label(
            "Cliente: " + cliente.getNombre() + "\n" +
            "Pelicula: " + funcion.getPelicula().getTitulo() + "\n" +
            "Sala: " + funcion.getSala().getNumero() + "\n" +
            "Fecha: " + funcion.getFecha() + "\n" +
            "Horario: " + funcion.getHorario() + "\n" +
            "Asientos: " + asientos + "\n" +
            "Total: $" + total
        );

        informacion.setFont(new Font(20));
        informacion.setStyle("-fx-text-fill: white;");

        Button botonConfirmar = new Button("Confirmar reserva");
        estiloBoton(botonConfirmar);
        Label mensaje = new Label();
        mensaje.setStyle("-fx-text-fill: #ff6666;");

        botonConfirmar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                try{
                    Reserva reservaCreada = gestion.crearReserva(cliente, funcion, asientosSeleccionados, entrada);

                    persistencia.guardarDatos(gestionFuncion, gestion);
                    persistencia.exportarTXT(gestion);

                    pantallaComprobante(ventana, cliente, reservaCreada);

                }catch(IllegalArgumentException e){
                    mensaje.setText(e.getMessage());
                }
            }
        });

        Button botonVolver = new Button("Volver");
        estiloBoton(botonVolver);
        botonVolver.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                pantallaEntrada(ventana, cliente, funcion, asientosSeleccionados);
            }
        });

        VBox contenedor = new VBox(20);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: #10102C;");

        contenedor.getChildren().add(titulo);
        contenedor.getChildren().add(informacion);
        contenedor.getChildren().add(botonConfirmar);
        contenedor.getChildren().add(botonVolver);
        contenedor.getChildren().add(mensaje);

        Scene escena = new Scene(contenedor, 950, 800);
        ventana.setScene(escena);
    }
    public void pantallaComprobante(Stage ventana, Cliente cliente, Reserva reserva){
        Label titulo = new Label("Reserva realizada correctamente");
        titulo.setFont(new Font(35));
        titulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        
        Label comprobante = new Label(
        "Codigo de reserva: " + reserva.getCodigo() + "\n" +
        "Cliente: " + reserva.getCliente().getNombre() + "\n" +
        "Pelicula: " + reserva.getFuncion().getPelicula().getTitulo() + "\n" +
        "Cantidad de asientos: " + reserva.getAsientos().size() + "\n" +
        "Total: $" + reserva.calcularTotalReserva()
        );
        comprobante.setFont(new Font(20));
        comprobante.setStyle("-fx-text-fill: white;");

        Button botonVolver = new Button("Volver al menu");
        estiloBoton(botonVolver);        
        botonVolver.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent evento){
            pantallaMenu(ventana, cliente);
        }
        });
        
        VBox contenedor = new VBox(20);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: #10102C;");

        contenedor.getChildren().add(titulo);
        contenedor.getChildren().add(comprobante);
        contenedor.getChildren().add(botonVolver);

        Scene escena = new Scene(contenedor, 950, 800);
        ventana.setScene(escena);
    }
    public void pantallaMostrarReservas(Stage ventana, Cliente cliente){
        Label titulo = new Label("Mis reservas");
        titulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        VBox contenedor = new VBox(20);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: #10102C;");

        contenedor.getChildren().add(titulo);

        ArrayList<Reserva> reservas = gestion.getReserva();
        boolean encontroReservas = false;
        
        for(int i = 0; i < reservas.size(); i++){
            Reserva reserva = reservas.get(i);

            if(reserva.getCliente().getDni().equals(cliente.getDni())){

                encontroReservas = true;

                String estado;

                if(reserva.isActiva()){
                    estado = "Activa";
                }else{
                    estado = "Cancelada";
                }

                Label informacion = new Label(
                    "Codigo: " + reserva.getCodigo() + "\n" +
                    "Pelicula: " + reserva.getFuncion().getPelicula().getTitulo() + "\n" +
                    "Estado: " + estado + "\n" +
                    "Total: $" + reserva.calcularTotalReserva()
                );

                informacion.setFont(new Font(20));
                informacion.setStyle("-fx-text-fill: white;");

                contenedor.getChildren().add(informacion);
            }
        }

        if(encontroReservas == false){
            Label mensaje = new Label("No tiene reservas realizadas");
            mensaje.setStyle("-fx-text-fill: white;");
            contenedor.getChildren().add(mensaje);
        }

        Button botonVolver = new Button("Regresar");
        estiloBoton(botonVolver);

        botonVolver.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                pantallaMenu(ventana, cliente);
            }
        });

        contenedor.getChildren().add(botonVolver);

        Scene escena = new Scene(contenedor, 950, 800);
        ventana.setScene(escena);
    }
    public void pantallaBuscarReserva(Stage ventana, Cliente cliente){

        Label titulo = new Label("Buscar reserva");
        titulo.setFont(new Font(40));
        titulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label indicacion = new Label("Ingrese el codigo de su reserva");
        indicacion.setStyle("-fx-text-fill: white;");

        TextField campoCodigo = new TextField();
        campoCodigo.setMaxWidth(250);

        Label resultado = new Label();
        resultado.setFont(new Font(20));
        resultado.setStyle("-fx-text-fill: white;");

        Button botonBuscar = new Button("Buscar");
        estiloBoton(botonBuscar);

        botonBuscar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                try{
                    int codigo = Integer.parseInt(campoCodigo.getText());
                    Reserva reserva = gestion.buscarReserva(codigo);
                    if(reserva != null && reserva.getCliente().getDni().equals(cliente.getDni())){
                        String estado;
                        if(reserva.isActiva()){
                            estado = "Activa";
                        }else{
                            estado = "Cancelada";
                        }
                        resultado.setText(
                            "Codigo: " + reserva.getCodigo() + "\n" +
                            "Cliente: " + reserva.getCliente().getNombre() + "\n" +
                            "Pelicula: " + reserva.getFuncion().getPelicula().getTitulo() + "\n" +
                            "Fecha: " + reserva.getFuncion().getFecha() + "\n" +
                            "Horario: " + reserva.getFuncion().getHorario() + "\n" +
                            "Estado: " + estado + "\n" +
                            "Total: $" + reserva.calcularTotalReserva()
                        );
                    }else{
                        resultado.setText("No se encontro la reserva");
                    }
                }catch(NumberFormatException e){
                    resultado.setText("Debe ingresar un codigo numerico");
                }
            }
        });

        Button botonVolver = new Button("Regresar");
        estiloBoton(botonVolver);

        botonVolver.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                pantallaMenu(ventana, cliente);
            }
        });

        VBox contenedor = new VBox(20);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: #10102C;");

        contenedor.getChildren().add(titulo);
        contenedor.getChildren().add(indicacion);
        contenedor.getChildren().add(campoCodigo);
        contenedor.getChildren().add(botonBuscar);
        contenedor.getChildren().add(resultado);
        contenedor.getChildren().add(botonVolver);

        Scene escena = new Scene(contenedor, 950, 800);
        ventana.setScene(escena);
    }
    public void pantallaCancelarReserva(Stage ventana, Cliente cliente){
        Label titulo = new Label("Cancelar reserva");
        titulo.setFont(new Font(40));
        titulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label indicacion = new Label("Ingrese el codigo de la reserva a cancelar");
        indicacion.setStyle("-fx-text-fill: white;");

        TextField campoCodigo = new TextField();
        campoCodigo.setMaxWidth(250);

        Label mensaje = new Label();
        mensaje.setFont(new Font(20));
        mensaje.setStyle("-fx-text-fill: white;");

        Button botonCancelar = new Button("Cancelar reserva");
        estiloBoton(botonCancelar);

        botonCancelar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                try{
                    int codigo = Integer.parseInt(campoCodigo.getText());
                    Reserva reserva = gestion.buscarReserva(codigo);
                    if(reserva == null){
                        mensaje.setText("No se encontro la reserva");
                    }else if(!reserva.getCliente().getDni().equals(cliente.getDni())){
                        mensaje.setText("No se encontro la reserva");
                    }else if(reserva.isActiva() == false){
                        mensaje.setText("La reserva ya esta cancelada");
                    }else{
                        gestion.cancelarReserva(codigo);
                        persistencia.guardarDatos(gestionFuncion, gestion);
                        persistencia.exportarTXT(gestion);
                        mensaje.setText("Reserva cancelada correctamente");
                    }
                }catch(NumberFormatException e){
                    mensaje.setText("Debe ingresar un codigo numerico");
                }
            }
        });

        Button botonVolver = new Button("Regresar");
        estiloBoton(botonVolver);

        botonVolver.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                pantallaMenu(ventana, cliente);
            }
        });

        VBox contenedor = new VBox(20);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: #10102C;");

        contenedor.getChildren().add(titulo);
        contenedor.getChildren().add(indicacion);
        contenedor.getChildren().add(campoCodigo);
        contenedor.getChildren().add(botonCancelar);
        contenedor.getChildren().add(mensaje);
        contenedor.getChildren().add(botonVolver);

        Scene escena = new Scene(contenedor, 950, 800);
        ventana.setScene(escena);
    }
    public void pantallaAdministracion(Stage ventana, Cliente cliente){

        Label titulo = new Label("Administracion del cine");
        titulo.setStyle("-fx-text-fill: white; -fx-font-family: 'Century Gothic'; -fx-font-size: 40px; -fx-font-weight: bold;");

        Button botonTodas = new Button("Todas las reservas");
        estiloBoton(botonTodas);
        botonTodas.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                pantallaReservasAdministracion(ventana, cliente, 1);
            }
            });
            Button botonActivas = new Button("Reservas activas");
            estiloBoton(botonActivas);
            botonActivas.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent evento){
                    pantallaReservasAdministracion(ventana, cliente, 2);
                }
            });
            Button botonCanceladas = new Button("Reservas canceladas");
            estiloBoton(botonCanceladas);
            botonCanceladas.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent evento){
                    pantallaReservasAdministracion(ventana, cliente, 3);
                }
            });
            Button botonVolver = new Button("Volver");
            estiloBotonVolver(botonVolver);
            botonVolver.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent evento){
                    pantallaMenu(ventana, cliente);
                }
            });
            VBox contenedor = new VBox(20);
            contenedor.setAlignment(Pos.CENTER);
            contenedor.setStyle("-fx-background-color: #10102C;");

            contenedor.getChildren().add(titulo);
            VBox.setMargin(titulo, new Insets(0,0,40,0));
            contenedor.getChildren().add(botonTodas);
            contenedor.getChildren().add(botonActivas);
            contenedor.getChildren().add(botonCanceladas);
            contenedor.getChildren().add(botonVolver);

            Scene escena = new Scene(contenedor, 950, 800);
            ventana.setScene(escena);
        }
    public void pantallaReservasAdministracion(Stage ventana, Cliente cliente, int opcion){
        Label titulo = new Label();
        titulo.setStyle("-fx-text-fill: white; -fx-font-family: 'Century Gothic'; -fx-font-size: 40px; -fx-font-weight: bold;");

        if(opcion == 1){
            titulo.setText("Todas las reservas");
        }else if(opcion == 2){
            titulo.setText("Reservas activas");
        }else{
            titulo.setText("Reservas canceladas");
        }

        VBox contenedor = new VBox(20);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: #10102C;");

        contenedor.getChildren().add(titulo);
        ArrayList<Reserva> reservas = gestion.getReserva();
        boolean encontroReservas = false;

        for(int i = 0; i < reservas.size(); i++){
            Reserva reserva = reservas.get(i);
            if(opcion == 1 || (opcion == 2 && reserva.isActiva()) || (opcion == 3 && reserva.isActiva() == false)){

                encontroReservas = true;
                String estado;

                if(reserva.isActiva()){
                    estado = "Activa";
                }else{
                    estado = "Cancelada";
                }
                Label informacion = new Label(
                    "Codigo: " + reserva.getCodigo() + "\n" +
                    "Cliente: " + reserva.getCliente().getNombre() + "\n" +
                    "DNI: " + reserva.getCliente().getDni() + "\n" +
                    "Pelicula: " + reserva.getFuncion().getPelicula().getTitulo() + "\n" +
                    "Estado: " + estado + "\n" +
                    "Total: $" + reserva.calcularTotalReserva()
                );
                informacion.setStyle("-fx-text-fill: white; -fx-font-family: 'Century Gothic'; -fx-font-size: 18px;");

                contenedor.getChildren().add(informacion);
            }
        }
        if(encontroReservas == false){
            Label mensaje = new Label("No se encontraron reservas");
            mensaje.setStyle("-fx-text-fill: white;");
            contenedor.getChildren().add(mensaje);
        }
        Button botonVolver = new Button("Volver");
        estiloBotonVolver(botonVolver);

        botonVolver.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                pantallaAdministracion(ventana, cliente);
            }
        });
        contenedor.getChildren().add(botonVolver);

        Scene escena = new Scene(contenedor, 950, 800);
        ventana.setScene(escena);
    }    
    public static void main(String[] args){
        launch(args);
    }
}
