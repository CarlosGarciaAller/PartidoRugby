package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logica.Logica;
import models.Partido;

import java.text.ParseException;

public class PantallaPrincipal extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gestión de partidos");
        TableView tablaPartidos = new TableView(Logica.getInstance().getListaPartidos());
        AnchorPane.setTopAnchor(tablaPartidos,10d);
        AnchorPane.setLeftAnchor(tablaPartidos,10d);
        AnchorPane.setRightAnchor(tablaPartidos,10d);
        AnchorPane.setBottomAnchor(tablaPartidos,50d);

        TableColumn<String, Partido> colLocal = new TableColumn<>("Local");
        colLocal.setCellValueFactory(new PropertyValueFactory<>("local"));

        TableColumn<String, Partido> colVisitante = new TableColumn<>("Visitante");
        colVisitante.setCellValueFactory(new PropertyValueFactory<>("visitante"));

        TableColumn<String, Partido> colDivision = new TableColumn<>("Division");
        colDivision.setCellValueFactory(new PropertyValueFactory<>("division"));

        TableColumn<String, Partido> colResultado = new TableColumn<>("Resultado");
        colResultado.setCellValueFactory(new PropertyValueFactory<>("resultado"));

        TableColumn<String, Partido> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        tablaPartidos.getColumns().addAll(colLocal, colVisitante, colDivision, colResultado, colFecha);

        Button btnAlta = new Button("Alta");
        AnchorPane.setBottomAnchor(btnAlta,10d);
        AnchorPane.setLeftAnchor(btnAlta,10d);

        Button btnBorrar = new Button("Borrar");
        AnchorPane.setBottomAnchor(btnBorrar,10d);
        AnchorPane.setRightAnchor(btnBorrar,10d);

        Button btnModificar = new Button("Modificar");
        AnchorPane.setBottomAnchor(btnModificar,10d);
        AnchorPane.setLeftAnchor(btnModificar,70d);

        AnchorPane anchorPane = new AnchorPane(tablaPartidos, btnAlta, btnBorrar, btnModificar);
        Scene scene = new Scene(anchorPane,500,500);
        stage.setScene(scene);
        stage.show();

        btnAlta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DialogoPartido dialogoPartido = null;
                try {
                    dialogoPartido = new DialogoPartido();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dialogoPartido.show();
            }
        });

        btnBorrar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                int id = tablaPartidos.getSelectionModel().getSelectedIndex();
                if (id>=0)
                    Logica.getInstance().borrarPartido(id);
            }
        });

        btnModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int i = tablaPartidos.getSelectionModel().getSelectedIndex();
                Partido selectPartido = Logica.getInstance().getListaPartidos().get(i);
                DialogoPartido dialogoPartido = new DialogoPartido(selectPartido,i);
                dialogoPartido.show();
            }
        });
    }
}