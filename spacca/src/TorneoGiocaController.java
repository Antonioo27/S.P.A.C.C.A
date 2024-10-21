import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.*;


public class TorneoGiocaController   {

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
    private Label Lbl1_1_1;

    @FXML
    private Label Lbl1_1_2;

    @FXML
    private Label Lbl1_1_3;

    @FXML
    private Label Lbl1_2_1;

    @FXML
    private Label Lbl1_2_2;

    @FXML
    private Label Lbl1_2_3;

    @FXML
    private Label Lbl1_3_1;

    @FXML
    private Label Lbl1_3_2;

    @FXML
    private Label Lbl1_3_3;

    @FXML
    private Label Lbl2_1_1;

    @FXML
    private Label Lbl2_1_2;

    @FXML
    private Label Lbl2_1_3;

    @FXML
    private Label Lbl2_2_1;

    @FXML
    private Label Lbl2_2_2;

    @FXML
    private Label Lbl2_2_3;

    @FXML
    private Label Lbl2_3_1;

    @FXML
    private Label Lbl2_3_2;

    @FXML
    private Label Lbl2_3_3;

    @FXML
    private Label Lbl3_1_1;

    @FXML
    private Label Lbl3_1_2;

    @FXML
    private Label Lbl3_1_3;

    @FXML
    private Label Lbl3_2_1;

    @FXML
    private Label Lbl3_2_2;

    @FXML
    private Label Lbl3_2_3;

    @FXML
    private Label Lbl3_3_1;

    @FXML
    private Label Lbl3_3_2;

    @FXML
    private Label Lbl3_3_3;

    @FXML
    private Label LBVINCITORE;

    @FXML
    private Label LblCodiceTorneo;

    @FXML
    private Button buttonGiocaQuarti1;
    @FXML
    private Button buttonGiocaQuarti2;
    @FXML
    private Button buttonGiocaQuarti3;
    @FXML
    private Button buttonGiocaQuarti4;
    @FXML
    private Button buttonGiocaQuarti5;
    @FXML
    private Button buttonGiocaQuarti6;
    @FXML
    private Button buttonGiocaQuarti7;
    @FXML
    private Button buttonGiocaQuarti8;
    @FXML
    private Button buttonGiocaQuarti9;
    @FXML
    private Button buttonGiocaSemi1;
    @FXML
    private Button buttonGiocaSemi2;
    @FXML
    private Button buttonGiocaSemi3;
    @FXML
    private Button buttonGiocaFinale;
    @FXML
    private Button buttonTornaIndietro;
    @FXML
    private Button buttonVisualizzaLeaderBoard;

    private Stage stage;
    private Scene scene;
    private File fileTorneo;

    private MainEngine m = new MainEngine();

    ArrayList<String> finalisti=new ArrayList<String>();

    ArrayList<Label> labelArrayList =  new ArrayList<>();


    @FXML 
    public void caricaSchermataPartita(ActionEvent event, Partita partita) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Resources/fxmlFiles/Partita.fxml"));
        Parent root=loader.load();
        PartitaController partitaController = loader.getController();
        partitaController.setCodiceTorneo(LblCodiceTorneo.getText());
        partitaController.carica(new File(partita.getCodice()+".csv"));
        partitaController.visualizzaCodicePartita();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);        
        stage.setScene(scene);
        stage.show();
    } 


    @FXML
    void buttonGioca1_1(ActionEvent event) throws IOException {
        Partita partita = m.getPartitaTorneo(0);
        caricaSchermataPartita(event, partita);
    }

    @FXML
    void buttonGioca2_1(ActionEvent event) throws IOException {
        Partita partita = m.getPartitaTorneo(1);
        caricaSchermataPartita(event, partita);
    }

    @FXML
    void buttonGioca3_1(ActionEvent event) throws IOException {
        Partita partita = m.getPartitaTorneo(2);
        caricaSchermataPartita(event, partita);
    }


    @FXML
    void buttonGioca1_2(ActionEvent event) throws IOException {
        Partita partita = m.getPartitaTorneo(3);
        caricaSchermataPartita(event, partita);
    }

    @FXML
    void buttonGioca2_2(ActionEvent event) throws IOException {
        Partita partita = m.getPartitaTorneo(4);
        caricaSchermataPartita(event, partita);
    }

    @FXML
    void buttonGioca3_2(ActionEvent event) throws IOException{
        Partita partita = m.getPartitaTorneo(5);
        caricaSchermataPartita(event, partita);
    }
    @FXML
    void buttonGioca1_3(ActionEvent event) throws IOException {
        Partita partita = m.getPartitaTorneo(6);
        caricaSchermataPartita(event, partita);
    }
    
    @FXML
    void buttonGioca2_3(ActionEvent event) throws IOException {
        Partita partita = m.getPartitaTorneo(7);
        caricaSchermataPartita(event, partita);
    }
    
    @FXML
    void buttonGioca3_3(ActionEvent event) throws IOException {
        Partita partita = m.getPartitaTorneo(8);
        caricaSchermataPartita(event, partita);
    }

    public void buttonGiocaSemi1(ActionEvent event) throws IOException {
        Partita partita = m.getPartitaTorneo(9);
        caricaSchermataPartita(event, partita);
    }

    public void buttonGiocaSemi2(ActionEvent event) throws IOException {
        Partita partita = m.getPartitaTorneo(10);
        caricaSchermataPartita(event, partita);
    }

    public void buttonGiocaSemi3(ActionEvent event) throws IOException {
        Partita partita = m.getPartitaTorneo(11);
        caricaSchermataPartita(event, partita);
    }

    @FXML
    void buttonGiocaFinale(ActionEvent event) throws IOException{
        Partita partita = m.getPartitaTorneo(m.getPartiteTorneo().size()-1);
        caricaSchermataPartita(event, partita);
    }

    public File getfile(){
        return fileTorneo;
    }

    public void setFile(String file){
        this.fileTorneo = new File(file+".csv");
    }

    public void caricaQuarti() throws IOException {
        boolean quartiFiniti=true;
        ArrayList<Button> arrayBottoni = new ArrayList<Button>();
        arrayBottoni.addAll(Arrays.asList(buttonGiocaQuarti1,buttonGiocaQuarti2,buttonGiocaQuarti3,buttonGiocaQuarti4,buttonGiocaQuarti5,buttonGiocaQuarti6,buttonGiocaQuarti7,buttonGiocaQuarti8,buttonGiocaQuarti9));
        for(int i=0; i<9; i++) {
            if (m.getPartitaTorneo(i).getMazzo().getMazzo().size() < 12 && m.getPartitaTorneo(i).getVincitore() == null) {
                arrayBottoni.get(i).setText("Riprendi");
                arrayBottoni.get(i).setFont(new Font("Arial", 10));
            }
        }
          for(int i=0; i<m.getPartiteTorneo().size(); i++) {
              if (m.getPartitaTorneo(i).getVincitore() == null)
                  quartiFiniti = false;
              else {
                  switch (i) {
                      case (0): {
                          LBLv1_1.setText(m.getPartitaTorneo(i).getVincitore().getUsername());
                          this.buttonGiocaQuarti1.setVisible(false);
                          break;
                      }
                      case (1): {
                          LBLv2_1.setText(m.getPartitaTorneo(i).getVincitore().getUsername());
                          this.buttonGiocaQuarti2.setVisible(false);
                          break;
                      }
                      case (2): {
                          LBLv3_1.setText(m.getPartitaTorneo(i).getVincitore().getUsername());
                          this.buttonGiocaQuarti3.setVisible(false);
                          break;
                      }
                      case (3): {
                          LBLv1_2.setText(m.getPartitaTorneo(i).getVincitore().getUsername());
                          this.buttonGiocaQuarti4.setVisible(false);
                          break;
                      }
                      case (4): {
                          LBLv2_2.setText(m.getPartitaTorneo(i).getVincitore().getUsername());
                          this.buttonGiocaQuarti5.setVisible(false);
                          break;
                      }
                      case (5): {
                          LBLv3_2.setText(m.getPartitaTorneo(i).getVincitore().getUsername());
                          this.buttonGiocaQuarti6.setVisible(false);
                          break;
                      }
                      case (6): {
                          LBLv1_3.setText(m.getPartitaTorneo(i).getVincitore().getUsername());
                          this.buttonGiocaQuarti7.setVisible(false);
                          break;
                      }
                      case (7): {
                          LBLv2_3.setText(m.getPartitaTorneo(i).getVincitore().getUsername());
                          this.buttonGiocaQuarti8.setVisible(false);
                          break;
                      }
                      case (8): {
                          LBLv3_3.setText(m.getPartitaTorneo(i).getVincitore().getUsername());
                          this.buttonGiocaQuarti9.setVisible(false);
                          break;
                      }
                  }
              }
          }

        ArrayList<Label> labelSemi = new ArrayList<Label>();
        labelSemi.addAll(Arrays.asList(LBLv1_1,LBLv2_1,LBLv3_1,LBLv1_2,LBLv2_2,LBLv3_2,LBLv1_3,LBLv2_3,LBLv3_3));


        boolean temp = true;
              for(int i=0; i<labelSemi.size(); i++){
                  if(labelSemi.get(i).getText().equalsIgnoreCase(""))
                      temp = false;
              }

              if(temp == true)
                  caricaSemi();



    }

    private void caricaSemi() throws IOException {

      buttonGiocaSemi1.setVisible(true);
      buttonGiocaSemi2.setVisible(true);
      buttonGiocaSemi3.setVisible(true);

      ArrayList<Button> arrayBottoni = new ArrayList<Button>();
        arrayBottoni.addAll(Arrays.asList(buttonGiocaSemi1,buttonGiocaSemi2,buttonGiocaSemi3));
        if(m.getPartiteTorneo().size()==12) {
            for (int i = 9; i < 12; i++) {
                if (m.getPartitaTorneo(i).getMazzo().getMazzo().size() < 12 && m.getPartitaTorneo(i).getVincitore() == null) {
                    arrayBottoni.get(i).setText("Riprendi");
                    arrayBottoni.get(i).setFont(new Font("Arial", 10));
                }
            }
        }

      Scanner scan=new Scanner(this.fileTorneo);
      int countRighe=0;
      while(scan.hasNextLine()){
          String s=scan.nextLine();
          if(s.equalsIgnoreCase("")==false)
          countRighe++;
      }



      if(countRighe<12){
          ArrayList<String> semifinalisti = new ArrayList<String>();

          for (int i = 0; i < m.getPartiteTorneo().size(); i++) {
              if (m.getPartiteTorneo().get(i).getVincitore().getUsername() != null)
                  semifinalisti.add(m.getPartiteTorneo().get(i).getVincitore().getUsername());
          }

          m.creaSemifinali(semifinalisti, this.LblCodiceTorneo.getText());
          PrintWriter pw=new PrintWriter(LblCodiceTorneo.getText()+".csv");

          System.out.println(m.getPartiteTorneo().size());
          for (int i = 0; i < m.getPartiteTorneo().size(); i++) {
              m.getPartitaTorneo(i).salvaPartita();
              pw.println(m.getPartitaTorneo(i).getCodice());
          }
          pw.close();
      }
      else{
          Scanner scan2=new Scanner(new File(LblCodiceTorneo.getText()+".csv"));
          int i=0;
          ArrayList<String> codici=new ArrayList<String>();
          while(scan2.hasNextLine())
              codici.add(scan2.nextLine());
              for(int k=9; k<codici.size(); k++){
                  String riga=codici.get(k);
                  Partita p=new Partita();
                  p.caricaPartita(new File(riga+".csv"));
          }
      }
      boolean semiFinite=true;

      for(int i=9; i<12; i++) {
            if (m.getPartitaTorneo(i).getVincitore() == null)
                semiFinite = false;
            else {
                switch (i) {
                    case (9): {
                        LBLv1.setText(m.getPartitaTorneo(i).getVincitore().getUsername());
                        this.buttonGiocaSemi1.setVisible(false);
                        break;
                    }
                    case (10): {
                        LBLv2.setText(m.getPartitaTorneo(i).getVincitore().getUsername());
                        this.buttonGiocaSemi2.setVisible(false);
                        break;
                    }
                    case (11): {
                        LBLv3.setText(m.getPartitaTorneo(i).getVincitore().getUsername());
                        this.buttonGiocaSemi3.setVisible(false);
                        break;
                    }
                    }
                }
      }
        if(semiFinite==true) {
            this.caricaFinale();
        }
    }

    private void caricaFinale() throws IOException {
        buttonGiocaFinale.setVisible(true);
        if(m.getPartiteTorneo().size()==13) {
          if (m.getPartitaTorneo(12).getMazzo().getMazzo().size() < 12 && m.getPartitaTorneo(12).getVincitore() == null) {
            buttonGiocaFinale.setText("Riprendi");
            buttonGiocaFinale.setFont(new Font("Arial", 10));
          }
        }
        Scanner scan=new Scanner(new File(this.LblCodiceTorneo.getText()+".csv"));
        int countRighe=0;
        while(scan.hasNextLine()){
            String s=scan.nextLine();
            if(s.equalsIgnoreCase("")==false)
                countRighe++;
        }

        if(countRighe<13){
        finalisti.addAll(Arrays.asList(LBLv1.getText(), LBLv2.getText(), LBLv3.getText()));
        m.creaFinale(finalisti, LblCodiceTorneo.getText());

        PrintWriter scrivo = new PrintWriter(this.LblCodiceTorneo.getText() + ".csv");
        for (int j = 0; j < m.getPartiteTorneo().size(); j++) {
            m.getPartitaTorneo(j).salvaPartita();
        }
        for (int i = 0; i < m.getPartiteTorneo().size(); i++) {
            scrivo.println(m.getPartitaTorneo(i).getCodice());
        }

        scrivo.close();
    }
        else{
            Scanner scan2=new Scanner(new File(LblCodiceTorneo.getText()+".csv"));
            int i=0;
            while(scan2.hasNextLine()){
                for(int j=0; j<12; j++)
                    scan2.nextLine();
                String riga=scan2.nextLine();
                Partita p=new Partita();
                p.caricaPartita(new File(riga+".csv"));
                m.aggiungiPartitaTorneo(p);
                m.getPartiteTorneo().get(i).salvaPartita();
                i++;
            }
        }

        if(m.getPartiteTorneo().get(m.getPartiteTorneo().size()-1).getVincitore()!=null){
            this.LBVINCITORE.setVisible(true);
            this.LBVINCITORE.setText(m.getPartiteTorneo().get(m.getPartiteTorneo().size()-1).getVincitore().getUsername());
            this.buttonGiocaFinale.setVisible(false);

            ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();

            Scanner scan3 = new Scanner(new File("LeaderBoardFile.csv"));
            while(scan3.hasNextLine()){
                String riga=scan3.nextLine();
                String[] str = riga.split(",");
                Giocatore g = new Giocatore(str[0], Integer.parseInt(str[1]));
                giocatori.add(g);
            }
            for(int i=0; i<giocatori.size(); i++){
                if(giocatori.get(i).getUsername().equalsIgnoreCase(m.getPartiteTorneo().get(m.getPartiteTorneo().size()-1).getVincitore().getUsername()))
                    giocatori.get(i).setTotalScore(giocatori.get(i).getTotalScore()+200);
            }

            scan3.close();

            Collections.sort(giocatori);
            PrintWriter scrivo = new PrintWriter("LeaderBoardFile.csv");

            for(int j=0; j<giocatori.size(); j++)
                scrivo.println(giocatori.get(j).getUsername()+","+giocatori.get(j).getTotalScore());

            scrivo.close();

            buttonVisualizzaLeaderBoard.fire();
        }

    }
    @FXML
    public void tornaIndietro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/fxmlFiles/PaginaIniziale.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();

        if(LBVINCITORE.getText().length()>0) {
            for (int i = 0; i < 13; i++) {
                String filePath = m.getPartitaTorneo(i).getCodice() + ".csv";

                try {
                    File file = new File(filePath);

                    if (file.exists()) {
                        file.delete();
                        System.out.println("File eliminato con successo.");
                    } else {
                        System.out.println("Impossibile eliminare il file. Controlla che esista e che tu abbia le autorizzazioni necessarie.");
                    }
                } catch (Exception e) {
                    System.err.println("Si è verificato un errore durante l'eliminazione del file: " + e.getMessage());
                }
            }
            String filePath2 = LblCodiceTorneo.getText() + ".csv";

            try {
                File file = new File(filePath2);

                if (file.exists()) {
                    file.delete();
                    System.out.println("File eliminato con successo.");
                } else {
                    System.out.println("Impossibile eliminare il file. Controlla che esista e che tu abbia le autorizzazioni necessarie.");
                }
            } catch (Exception e) {
                System.err.println("Si è verificato un errore durante l'eliminazione del file: " + e.getMessage());
            }
            Scanner scan = new Scanner(new File("PartiteETornei.csv"));

            ArrayList<String> codici=new ArrayList<String>();
            while(scan.hasNextLine()){
                String riga = scan.nextLine();
                codici.add(riga);
            }
            codici.remove(LblCodiceTorneo.getText());

            PrintWriter scrivo = new PrintWriter("PartiteETornei.csv");
            for(int i=0; i<codici.size(); i++) {
                scrivo.println(codici.get(i));
            }
            scrivo.close();
        }

    }

    public void visualizzaCodiceTorneo(String codice) {
        LblCodiceTorneo.setText(codice.substring(0,4));
    }


    public void inizializza() throws IOException {
        try {
            m.caricaTorneo(this.fileTorneo);
        }catch(IOException e) {
        e.getMessage();
        }
        labelArrayList.addAll(Arrays.asList(Lbl1_1_1,Lbl2_1_1,Lbl3_1_1,Lbl1_2_1,Lbl2_2_1,Lbl3_2_1,Lbl1_3_1,Lbl2_3_1,Lbl3_3_1,Lbl1_1_2,Lbl2_1_2,Lbl3_1_2,Lbl1_2_2,Lbl2_2_2,Lbl3_2_2,Lbl1_3_2,Lbl2_3_2,Lbl3_3_2,Lbl1_1_3,Lbl2_1_3,Lbl3_1_3,Lbl1_2_3,Lbl2_2_3,Lbl3_2_3,Lbl1_3_3,Lbl2_3_3,Lbl3_3_3));

        int indiceGiocatore=0;
        int indicePartitaTorneo=0;
        for(int i=2; i<labelArrayList.size(); i=i+3) {
            //per settare label di partite non ancora finite
                labelArrayList.get(i - 2).setText(m.getPartitaTorneo(indicePartitaTorneo).getGiocatori().get(indiceGiocatore).getUsername());
                indiceGiocatore++;
                labelArrayList.get(i - 1).setText(m.getPartitaTorneo(indicePartitaTorneo).getGiocatori().get(indiceGiocatore).getUsername());
                indiceGiocatore++;
                labelArrayList.get(i).setText(m.getPartitaTorneo(indicePartitaTorneo).getGiocatori().get(indiceGiocatore).getUsername());
                indiceGiocatore = 0;
                indicePartitaTorneo++;
            }

        this.caricaQuarti();
    }


    public void visualizzaLeaderBoard(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/fxmlFiles/LeaderBoard.fxml"));
        stage.setTitle("LeaderBoard");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}
