package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.Logica;
import models.Partido;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DialogoPartido extends Stage {

    private TextField tfLocal;
    private TextField tfVisitante;
    private TextField tfDivision;
    private TextField tfResultado;
    private DatePicker dpFecha;
    private Button botonAceptar;

    public DialogoPartido() throws ParseException {
        inicializaVista();
        botonAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                añadirPartido();
            }
        });
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    public DialogoPartido(Partido partido,int posicion) {
        inicializaVista();
        tfLocal.setText(partido.getLocal());
        tfVisitante.setText(partido.getVisitante());
        tfDivision.setText(partido.getDivision());
        tfResultado.setText(partido.getResultado());
        dpFecha.setValue(LOCAL_DATE(partido.getFecha()));

        botonAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Partido partidoM = new Partido(tfLocal.getText(),tfVisitante.getText(),tfDivision.getText(),tfResultado.getText(), dpFecha.getValue().toString());
                Logica.getInstance().modificarPartido(partidoM,posicion);
                close();
            }
        });
    }

    private void añadirPartido() {
        Partido partido = new Partido(tfLocal.getText(), tfVisitante.getText(), tfDivision.getText(), tfResultado.getText(), dpFecha.getValue().toString());
        Logica.getInstance().addPartido(partido);
        close();
    }

    public void inicializaVista(){
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Alta partido");

        Label local = new Label("Equipo local");
        tfLocal = new TextField();
        Label visitante = new Label("Equipo visitante");
        tfVisitante = new TextField();
        Label division = new Label("Division");
        tfDivision = new TextField();
        Label resultado = new Label("Resultado");
        tfResultado = new TextField();
        Label fecha = new Label("Fecha");
        dpFecha = new DatePicker();
        botonAceptar = new Button("Aceptar");

        VBox vBox = new VBox(local,tfLocal,
                visitante,tfVisitante,
                division,tfDivision,
                resultado,tfResultado,
                fecha,dpFecha,
                botonAceptar);

        Scene scenePartido = new Scene(vBox, 500,500);
        setScene(scenePartido);
    }
}
