import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AggiungiGiocatoriTorneoController {

    @FXML
    private Label LBLv1;

    @FXML
    private Label LBLv1_1;

    @FXML
    private Label LBLv1_2;

    @FXML
    private Label LBLv1_3;

    @FXML
    private Label LBLv2;

    @FXML
    private Label LBLv2_1;

    @FXML
    private Label LBLv2_2;

    @FXML
    private Label LBLv2_3;

    @FXML
    private Label LBLv3;

    @FXML
    private Label LBLv3_1;

    @FXML
    private Label LBLv3_2;

    @FXML
    private Label LBLv3_3;

    @FXML
    private TextField Lbl1_1_1;

    @FXML
    private TextField Lbl1_1_2;

    @FXML
    private TextField Lbl1_1_3;

    @FXML
    private TextField Lbl1_2_1;

    @FXML
    private TextField Lbl1_2_2;

    @FXML
    private TextField Lbl1_2_3;

    @FXML
    private TextField Lbl1_3_1;

    @FXML
    private TextField Lbl1_3_2;

    @FXML
    private TextField Lbl1_3_3;

    @FXML
    private TextField Lbl2_1_1;

    @FXML
    private TextField Lbl2_1_2;

    @FXML
    private TextField Lbl2_1_3;

    @FXML
    private TextField Lbl2_2_1;

    @FXML
    private TextField Lbl2_2_2;

    @FXML
    private TextField Lbl2_2_3;

    @FXML
    private TextField Lbl2_3_1;

    @FXML
    private TextField Lbl2_3_2;

    @FXML
    private TextField Lbl2_3_3;

    @FXML
    private TextField Lbl3_1_1;

    @FXML
    private TextField Lbl3_1_2;

    @FXML
    private TextField Lbl3_1_3;

    @FXML
    private TextField Lbl3_2_1;

    @FXML
    private TextField Lbl3_2_2;

    @FXML
    private TextField Lbl3_2_3;

    @FXML
    private TextField Lbl3_3_1;

    @FXML
    private TextField Lbl3_3_2;

    @FXML
    private TextField Lbl3_3_3;

    @FXML
    private Label LblCodiceTorneo;


    private Stage stage;
    private Scene scene;

    private ArrayList<String> nomiGiocatori=new ArrayList<String>();
    private MainEngine m = new MainEngine();


    @FXML
    void creaTorneo(ActionEvent event) throws IOException {

        nomiGiocatori.add(Lbl1_1_1.getText());
        nomiGiocatori.add(Lbl2_1_1.getText());
        nomiGiocatori.add(Lbl3_1_1.getText());
        nomiGiocatori.add(Lbl1_2_1.getText());
        nomiGiocatori.add(Lbl2_2_1.getText());
        nomiGiocatori.add(Lbl3_2_1.getText());
        nomiGiocatori.add(Lbl1_3_1.getText());
        nomiGiocatori.add(Lbl2_3_1.getText());
        nomiGiocatori.add(Lbl3_3_1.getText());
        nomiGiocatori.add(Lbl1_1_2.getText());
        nomiGiocatori.add(Lbl2_1_2.getText());
        nomiGiocatori.add(Lbl3_1_2.getText());
        nomiGiocatori.add(Lbl1_2_2.getText());
        nomiGiocatori.add(Lbl2_2_2.getText());
        nomiGiocatori.add(Lbl3_2_2.getText());
        nomiGiocatori.add(Lbl1_3_2.getText());
        nomiGiocatori.add(Lbl2_3_2.getText());
        nomiGiocatori.add(Lbl3_3_2.getText());
        nomiGiocatori.add(Lbl1_1_3.getText());
        nomiGiocatori.add(Lbl2_1_3.getText());
        nomiGiocatori.add(Lbl3_1_3.getText());
        nomiGiocatori.add(Lbl1_2_3.getText());
        nomiGiocatori.add(Lbl2_2_3.getText());
        nomiGiocatori.add(Lbl3_2_3.getText());
        nomiGiocatori.add(Lbl1_3_3.getText());
        nomiGiocatori.add(Lbl2_3_3.getText());
        nomiGiocatori.add(Lbl3_3_3.getText());

        int botCount=1;
        for(int i=0; i<nomiGiocatori.size(); i++){
            if(nomiGiocatori.get(i).length()==0){
                nomiGiocatori.set(i,"bot"+botCount);
           botCount++;
            }
        }

        File file = new File("PartiteETornei.csv");
        if(file.exists()){
            FileWriter fw = new FileWriter(file,true);
            fw.append(LblCodiceTorneo.getText()+System.lineSeparator());
            fw.close();
        }
        else{
            PrintWriter pw=new PrintWriter(file);
            pw.println(LblCodiceTorneo.getText());
            pw.close();
        }

        for(int i=0; i<this.nomiGiocatori.size(); i++)
            new ModificaLeaderBoard().aggiungiGiocatoreLeaderBoard(this.nomiGiocatori.get(i));

        m.creaQuarti(nomiGiocatori, LblCodiceTorneo.getText());

        for(int i=0; i<9; i++){
            m.getPartitaTorneo(i).salvaPartita();
        }

        PrintWriter scrivo = new PrintWriter(LblCodiceTorneo.getText()+".csv");
        for(int i=0; i<9; i++){
           scrivo.println(m.getPartitaTorneo(i).getCodice());
        }

        scrivo.close();

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Resources/fxmlFiles/Home.fxml"));
        Parent root=loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }



    public void visualizzaCodiceTorneo(String codicePartita) {
        LblCodiceTorneo.setText(codicePartita);
    }

    public void tornaAllaHome(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Resources/fxmlFiles/Home.fxml"));
        Parent root=loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

}

