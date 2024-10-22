import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HomeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label loginLabel;
    @FXML
    private Button buttonGestisciGiocatori;
    @FXML
    private Button buttonLogout;
    private ArrayList<String> nomigiocatori = new ArrayList<String>();

    public void displayName(String username){
        loginLabel.setText("Benvenuto : "+username);
    }

    public void creaPartitaSingola(ActionEvent event) throws IOException {
        String codicePartita = String.valueOf((int)(Math.random() * (3000 - 2000 + 1) + 2000));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/fxmlFiles/AggiungiGiocatoriPartitaSingola.fxml"));
        root= loader.load();
        AggiungiGiocatoriPartitaSingolaController addPlayerSingleGameController = loader.getController();
        addPlayerSingleGameController.visualizzaCodicePartita(codicePartita);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void creaTorneo(ActionEvent event) throws IOException {
        String codicePartita = String.valueOf((int)(Math.random() * (2000 - 1000 + 1) + 3000));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/fxmlFiles/AggiungiGiocatoriTorneo.fxml"));
        root= loader.load();
        AggiungiGiocatoriTorneoController torneoController = loader.getController();
        torneoController.visualizzaCodiceTorneo(codicePartita);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void gestisciGiocatori(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/fxmlFiles/GestioneGiocatori.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/fxmlFiles/PaginaIniziale.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void nomiTorneo(ArrayList<String> nomiGiocatori) {
       this.nomigiocatori = nomiGiocatori;
    }

    public void eliminaPartiteTornei(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/fxmlFiles/EliminaPartiteTornei.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

}
