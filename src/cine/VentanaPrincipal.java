package cine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 *
 * @author Luna
 */

public class VentanaPrincipal extends Application{
    
    @Override
    public void start(Stage ventana){
        Label titulo = new Label("Binevenido a nuestro cine!");
        
        Label nombre = new Label("Ingrese su nombre: ");
        TextField campoNombre = new TextField();
        
        Label dni = new Label("Ingrese su DNI: ");
        TextField campoDni = new TextField();
        
        Button botonIngresar = new Button("Ingresar");
        
        Label mensaje = new Label();
        
        botonIngresar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evento){
                mensaje.setText("Bienvenido/a " + campoNombre.getText());
            }
        
        });
        
        VBox contenedor = new VBox(30);
        
        contenedor.getChildren().add(titulo);
        contenedor.getChildren().add(nombre);
        contenedor.getChildren().add(campoNombre);
        contenedor.getChildren().add(dni);
        contenedor.getChildren().add(campoDni);
        contenedor.getChildren().add(botonIngresar);
        contenedor.getChildren().add(mensaje);
        
        Scene escena = new Scene(contenedor, 600, 500);
        
        ventana.setTitle("CINE");
        ventana.setScene(escena);
        ventana.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
