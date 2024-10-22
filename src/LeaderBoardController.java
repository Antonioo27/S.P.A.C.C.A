import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;


    public class LeaderBoardController implements Initializable {

        @FXML
        private TableView<Giocatore> TableView ;

        @FXML
        private TableColumn<Giocatore, String> username ;

        @FXML
        private TableColumn<Giocatore, Integer> totalScore;


        private ObservableList<Giocatore> giocatori;
        private Stage stage;
        private Scene scene;
        private Parent root;
        private ArrayList<Giocatore> GIOCATORI = new ArrayList<Giocatore>();

        
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            Scanner scan = null;
            try {
                scan = new Scanner(new File("LeaderBoardFile.csv"));
            } catch (FileNotFoundException e){
                System.out.print("File non trovato");
            }
            while (scan.hasNextLine()) {
                String riga = scan.nextLine();
                String[] str = riga.split(",");
                GIOCATORI.add(new Giocatore(str[0], Integer.parseInt(str[1])));
            }
            ObservableList<Giocatore> listaGiocatori = FXCollections.observableArrayList(GIOCATORI);
            username.setCellValueFactory(new PropertyValueFactory<Giocatore, String>("Username"));
            totalScore.setCellValueFactory(new PropertyValueFactory<Giocatore, Integer>("totalScore"));
            TableView.setItems(listaGiocatori);

        }

    }