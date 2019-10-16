package sample;

import DAM.logica.Logica;
import DAM.models.Partido;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.ParseException;

public class PantallaPrincipal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /*
        SimpleDateFormat format = new SimpleDateFormat( "dd-MM-yyyy");
        Partido partido1 = new Partido("Real Oviedo", "Sporting", "segunda", 2, format.parse("02-12-2001"));
        Partido partido2 = new Partido("Aviles","Llanes","segunda",2, format.parse("02-12-2001"));
        listaPartidos.add(partido1);
        listaPartidos.add(partido2);*/

        stage.setTitle("Gestión de partidos");
        TableView tablaPartidos = new TableView();
        AnchorPane.setTopAnchor(tablaPartidos,10d);
        AnchorPane.setLeftAnchor(tablaPartidos,10d);
        AnchorPane.setRightAnchor(tablaPartidos,10d);
        AnchorPane.setBottomAnchor(tablaPartidos,50d);

        TableColumn<String, Partido> column1 = new TableColumn<>("Local");
        column1.setCellValueFactory(new PropertyValueFactory<>("local"));

        TableColumn<String, Partido> column2 = new TableColumn<>("Visitante");
        column2.setCellValueFactory(new PropertyValueFactory<>("visitante"));

        TableColumn<String, Partido> column3 = new TableColumn<>("Division");
        column3.setCellValueFactory(new PropertyValueFactory<>("division"));

        TableColumn<String, Partido> column4 = new TableColumn<>("Resultado");
        column4.setCellValueFactory(new PropertyValueFactory<>("resultado"));

        TableColumn<String, Partido> column5 = new TableColumn<>("Fecha");
        column5.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        tablaPartidos.getColumns().addAll(column1, column2, column3, column4, column5);

        Button buttonAlta = new Button("Alta");
        AnchorPane.setBottomAnchor(buttonAlta,10d);
        AnchorPane.setLeftAnchor(buttonAlta,10d);

        Button buttonBorrar = new Button("Borrar");
        AnchorPane.setBottomAnchor(buttonBorrar,10d);
        AnchorPane.setRightAnchor(buttonBorrar,10d);

        Button buttonModificar = new Button("Modificar");
        AnchorPane.setBottomAnchor(buttonModificar,10d);
        AnchorPane.setLeftAnchor(buttonModificar,70d);

        AnchorPane anchorPane = new AnchorPane(tablaPartidos, buttonAlta, buttonBorrar, buttonModificar);
        Scene scene = new Scene(anchorPane,500,500);
        stage.setScene(scene);
        stage.show();

        buttonAlta.setOnAction(new EventHandler<ActionEvent>() {
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

        buttonBorrar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                int id = tablaPartidos.getSelectionModel().getSelectedIndex();
                if (id>=0)
                    Logica.getInstance().borrarPartido(id);
            }
        });

        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int i = tablaPartidos.getSelectionModel().getSelectedIndex();
                Partido selectPartido = Logica.getInstance().getListaPersonas().get(i);
                DialogoPartido dialogoPersona = null;
                try {
                    dialogoPersona = new DialogoPartido(selectPartido,i);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dialogoPersona.show();
            }
        });
    }
}
