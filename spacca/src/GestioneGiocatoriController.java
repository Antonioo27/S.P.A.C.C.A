import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GestioneGiocatoriController implements Initializable {

    @FXML
    private Button buttonConferma;

    @FXML
    private Button buttonElimina;

    @FXML
    private Button buttonGiocatore;

    @FXML
    private Button buttonTornaIndietro;

    @FXML
    private ListView<String> listView = new ListView<String>();

    @FXML
    private Button buttonAggiungi;

    @FXML
    private TextField textFieldVisualizzaGiocatore;

    @FXML
    private TextField textNuovoGiocatore;
    private Stage stage;
    private Scene scene;

    private ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
    ArrayList<String> nomi = new ArrayList<String>();


    @FXML
    void confermaGiocatore(ActionEvent event) throws FileNotFoundException {
        String nomeNuovo = "";
        String nomeVecchio = "";
        if (textNuovoGiocatore.getText().length() > 0) {
            PrintWriter scrivo = new PrintWriter(new File("LeaderBoardFile.csv"));
            nomeNuovo = textNuovoGiocatore.getText();
            nomeVecchio = textFieldVisualizzaGiocatore.getText();
            nomi.remove(nomeVecchio);
            nomi.add(nomeNuovo);
            for (int i = 0; i < giocatori.size(); i++) {
                if (giocatori.get(i).getUsername().equalsIgnoreCase(nomeVecchio)) {
                    listView.setItems(FXCollections.observableArrayList(nomi));
                    giocatori.get(i).setUsername(nomeNuovo);
                }
                scrivo.println(giocatori.get(i).getUsername() + "," + giocatori.get(i).getTotalScore());
            }
            scrivo.close();
            textFieldVisualizzaGiocatore.setText("");
            textNuovoGiocatore.setVisible(false);
            buttonConferma.setVisible(false);
            textNuovoGiocatore.setText("");
            textFieldVisualizzaGiocatore.setDisable(false);
        } else
            Allert.showAlert(Alert.AlertType.INFORMATION, "Nome non valido", "Devi inserire un nome prima");

    }

    @FXML
    void eliminaGiocatore(ActionEvent event) throws FileNotFoundException {

        if (textFieldVisualizzaGiocatore.getText().length() == 0) {
            Allert.showAlert(Alert.AlertType.INFORMATION, "Errore", "Non puoi eliminare un giocatore inesistente");
        } else {
            Giocatore giocatore = new Giocatore(textFieldVisualizzaGiocatore.getText(), 0);
            PrintWriter scrivo = new PrintWriter(new File("LeaderBoardFile.csv"));

            for (int i = 0; i < giocatori.size(); i++) {
                if (giocatore.getUsername().equalsIgnoreCase(giocatori.get(i).getUsername())) {
                    nomi.remove(giocatore.getUsername());
                    giocatori.remove(i);
                    listView.setItems(FXCollections.observableArrayList(nomi));
                }
            }
            for (int i = 0; i < giocatori.size(); i++)
                scrivo.println(giocatori.get(i).getUsername() + "," + giocatori.get(i).getTotalScore());

            scrivo.close();

            textFieldVisualizzaGiocatore.setText("");
        }
    }


    @FXML
    void modificaGiocatore(ActionEvent event) {
        if (textFieldVisualizzaGiocatore.getText().length() > 0) {
            textNuovoGiocatore.setVisible(true);
            textFieldVisualizzaGiocatore.setDisable(true);
            buttonConferma.setVisible(true);
        } else
            Allert.showAlert(Alert.AlertType.INFORMATION, "Errore", "Non puoi modificare un giocatore inesistente");


    }

    @FXML
    void tornaIndietro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/fxmlFiles/Home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonConferma.setVisible(false);

        File file = new File("LeaderBoardFile.csv");
        if(file.exists()) {
            Scanner scan = null;
            try {
                scan = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (scan.hasNextLine()) {
                    String riga = scan.nextLine();
                    String[] str = riga.split(",");
                    giocatori.add(new Giocatore(str[0], Integer.parseInt(str[1])));
                }
            }
            for (int i = 0; i < giocatori.size(); i++)
                nomi.add(giocatori.get(i).getUsername());
            if (nomi.size() == 1) {
                textFieldVisualizzaGiocatore.setText(nomi.get(0));
                listView.setItems(FXCollections.observableArrayList(nomi));
            } else {
                listView.setItems(FXCollections.observableArrayList(nomi));
            }
        }



    public void selezionaGiocatore(MouseEvent mouseEvent) {
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        textFieldVisualizzaGiocatore.setText(newValue);
                    }
                });
    }

    public void aggiungiGiocatore(ActionEvent event) throws IOException {
        if (textFieldVisualizzaGiocatore.getText().length() > 0) {
            File file = new File("LeaderBoardFile.csv");
            if (file.exists()) {

                if(nomi.contains(textFieldVisualizzaGiocatore.getText())){
                    Allert.showAlert(Alert.AlertType.INFORMATION, "Errore", "Non puoi aggiungere un giocatore già esistente");
                }
                else{
                    FileWriter fileWriter = new FileWriter(file,true);

                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    // Scrive il contenuto nel file
                    bufferedWriter.write(textFieldVisualizzaGiocatore.getText()+",0");

                    // Vai a capo
                    bufferedWriter.newLine();

                    bufferedWriter.close();
                    giocatori.add(new Giocatore(textFieldVisualizzaGiocatore.getText(),0));
                    nomi.add(textFieldVisualizzaGiocatore.getText());
                }
                  textFieldVisualizzaGiocatore.setText("");
                  listView.setItems(FXCollections.observableArrayList(nomi));
            }
            else{
                PrintWriter scrivo = new PrintWriter(file);
                scrivo.println(textFieldVisualizzaGiocatore.getText()+",0");
                scrivo.close();
                nomi.add(textFieldVisualizzaGiocatore.getText());
                giocatori.add(new Giocatore(textFieldVisualizzaGiocatore.getText(),0));
                listView.setItems(FXCollections.observableArrayList(nomi));
                textFieldVisualizzaGiocatore.setText("");
            }
        } else
            Allert.showAlert(Alert.AlertType.INFORMATION, "Errore", "Non puoi aggiungere un giocatore inesistente");
    }

}


