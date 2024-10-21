import javafx.animation.TranslateTransition;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
import javafx.animation.PauseTransition;

public class PartitaController {
    public Button ButtonSalvaPartita;
    @FXML
    private Label Giocatore1Score;
    @FXML
    private Label Giocatore2Score;
    @FXML
    private Label Giocatore3Score;
    @FXML
    private Button ButtonTornaHome;
    @FXML
    private Button giocaButton;
    @FXML
    private Label labelCodicePartita;
    @FXML
    private Label labelGiocatore1;
    @FXML
    private Label labelGiocatore2;
    @FXML
    private Label labelGiocatore3;
    @FXML
    private ImageView Imm1G1;
    @FXML
    private ImageView Imm1G2;
    @FXML
    private ImageView Imm1G3;
    @FXML
    private ImageView Imm2G1;
    @FXML
    private ImageView Imm2G2;
    @FXML
    private ImageView Imm2G3;
    @FXML
    private ImageView Imm3G1;
    @FXML
    private ImageView Imm3G2;
    @FXML
    private ImageView Imm3G3;
    @FXML
    private Label labelCheckSave;
    @FXML
    private ImageView cartaSelG1;
    @FXML
    private ImageView cartaSelG2;
    @FXML
    private ImageView cartaSelG3;
    @FXML
    private Label labelAllert;
    @FXML
    private ImageView ImmMazzo1;
    @FXML
    private ImageView ImmMazzo2;
    @FXML
    private ImageView ImmMazzo3;
    @FXML
    private ImageView ImmMazzo;
    @FXML
    private Label labelCarteMazzo;
    @FXML
    private Label LabelVincitore;
    @FXML
    private Label LabelCodiceTorneo;
    @FXML
    private Button buttonVisualizzaLeaderBoard;

    private File fileTorneoo;

    private Stage stage;

    private Scene scene;

    private Parent root;
    private String vincitore;
    MainEngine m = new MainEngine();


    public void carica(File file) throws FileNotFoundException {

        m.caricaPartita(file);

        labelGiocatore1.setText(m.getGiocatori().get(0).getUsername());
        labelGiocatore2.setText(m.getGiocatori().get(1).getUsername());
        labelGiocatore3.setText(m.getGiocatori().get(2).getUsername());
        Giocatore1Score.setText(""+m.getGiocatori().get(0).getTotalScore());
        Giocatore2Score.setText(""+m.getGiocatori().get(1).getTotalScore());
        Giocatore3Score.setText(""+m.getGiocatori().get(2).getTotalScore());

        //carte coperte in mano giocatore 1
        copriCarte(Imm1G1,Imm2G1,Imm3G1,0);

        // carte coperte in mano del giocatore 2
        copriCarte(Imm1G2,Imm2G2,Imm3G2,1);

        // carte coperte in mano del giocatore 3
        copriCarte(Imm1G3,Imm2G3,Imm3G3,2);

        labelAllert.setText("Turno di:\t " + labelGiocatore1.getText());
        labelCarteMazzo.setText(""+m.getMazzo().getMazzo().size());
    }

    public void copriCarte(ImageView carta1, ImageView carta2, ImageView carta3, int indiceGiocatore){
        if(m.getCarteGiocatore(indiceGiocatore).get(0)!=null)
            carta1.setImage(new Image(getClass().getClassLoader().getResourceAsStream("Resources/Images/backCard2.jpeg")));
        if(m.getCarteGiocatore(indiceGiocatore).get(1)!=null)
            carta2.setImage(new Image(getClass().getClassLoader().getResourceAsStream("Resources/Images/backCard2.jpeg")));
        if(m.getCarteGiocatore(indiceGiocatore).get(2)!=null)
            carta3.setImage(new Image(getClass().getClassLoader().getResourceAsStream("Resources/Images/backCard2.jpeg")));
    }

    public void scopriCarte(ImageView carta1,ImageView carta2, ImageView carta3,int indiceGiocatore) {
        if (m.getCarteGiocatore(indiceGiocatore).get(0) != null)
            carta1.setImage(m.getCarteGiocatore(indiceGiocatore).get(0).getImage());
        else
            carta1.setImage(null);
        if (m.getCarteGiocatore(indiceGiocatore).get(1) != null)
            carta2.setImage(m.getCarteGiocatore(indiceGiocatore).get(1).getImage());
        else
            carta2.setImage(null);
        if (m.getCarteGiocatore(indiceGiocatore).get(2) != null)
            carta3.setImage(m.getCarteGiocatore(indiceGiocatore).get(2).getImage());
        else
            carta3.setImage(null);
    }


    public void visualizzaCodicePartita() {
        labelCodicePartita.setText("Codice Partita : "+MainEngine.getPartita().getCodice());
    }

    public void visualizzaLeaderBoard(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/fxmlFiles/LeaderBoard.fxml"));

        stage.setTitle("LeaderBoard");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void SalvaPartita(ActionEvent event) throws FileNotFoundException {
        m.salvaPartita();
        labelCheckSave.setVisible(true);
        Timer timer=new Timer();
        TimerTask tm=new TimerTask() {
            @Override
            public void run() {
                labelCheckSave.setVisible(false);
            }
        };
        timer.schedule(tm, 1500);
    }

    public void muoviCarta1G1(MouseEvent mouseEvent) {
        if(m.getTurno()==1  && m.getCarteGiocatore(0).get(0)!=null) {
            //metodo per non cliccare carta in mano al giocatore nulla
            traslaCartaDaGiocatoreAlCampo(Imm1G1, 0, cartaSelG1);
            m.muoviCarta(0, 0);
            scopriCarte(Imm1G2, Imm2G2, Imm3G2, 1);
            copriCarte(Imm1G1, Imm2G1, Imm3G1, 0);
            m.prossimoTurno();
            labelAllert.setText("Turno di\t: "+labelGiocatore2.getText());
            System.out.println(labelGiocatore2.getText().substring(0,3).equalsIgnoreCase("bot"));
            checkBot(1);
        }
    }

    public void muoviCarta2G1(MouseEvent mouseEvent) {
        if(m.getTurno()==1&& m.getCarteGiocatore(0).get(1)!=null) {
            traslaCartaDaGiocatoreAlCampo(Imm2G1, 0, cartaSelG1);
            m.muoviCarta(0, 1);
            scopriCarte(Imm1G2, Imm2G2, Imm3G2, 1);
            copriCarte(Imm1G1, Imm2G1, Imm3G1, 0);
            m.prossimoTurno();
            labelAllert.setText("Turno di\t: "+labelGiocatore2.getText());
            checkBot(1);
        }
    }

    public void muoviCarta3G1(MouseEvent mouseEvent) {
        if(m.getTurno()==1&& m.getCarteGiocatore(0).get(2)!=null) {
            traslaCartaDaGiocatoreAlCampo(Imm3G1, 0, cartaSelG1);
            m.muoviCarta(0, 2);
            scopriCarte(Imm1G2, Imm2G2, Imm3G2, 1);
            copriCarte(Imm1G1, Imm2G1, Imm3G1, 0);
            m.prossimoTurno();
            labelAllert.setText("Turno di\t: "+labelGiocatore2.getText());
            checkBot(1);
        }
    }

    public void muoviCarta1G2(MouseEvent mouseEvent) {
        if (m.getTurno() == 2&& m.getCarteGiocatore(1).get(0)!=null){
            traslaCartaDaGiocatoreAlCampo(Imm1G2, 1, cartaSelG2);
            m.muoviCarta(1, 0);
            scopriCarte(Imm1G3, Imm2G3, Imm3G3, 2);
            copriCarte(Imm1G2, Imm2G2, Imm3G2, 1);
            m.prossimoTurno();
            labelAllert.setText("Turno di\t: "+labelGiocatore3.getText());
            checkBot(2);
         }
    }

    public void muoviCarta2G2(MouseEvent mouseEvent) {
        if(m.getTurno()==2&&m.getCarteGiocatore(1).get(1)!=null) {
            traslaCartaDaGiocatoreAlCampo(Imm2G2, 1, cartaSelG2);
            m.muoviCarta(1, 1);
            scopriCarte(Imm1G3, Imm2G3, Imm3G3, 2);
            copriCarte(Imm1G2, Imm2G2, Imm3G2, 1);
            m.prossimoTurno();
            labelAllert.setText("Turno di\t: "+labelGiocatore3.getText());
            checkBot(2);
        }
    }

    public void muoviCarta3G2(MouseEvent mouseEvent) {
        if (m.getTurno() == 2&&m.getCarteGiocatore(1).get(2)!=null){
            traslaCartaDaGiocatoreAlCampo(Imm3G2, 1, cartaSelG2);
        m.muoviCarta(1, 2);
        scopriCarte(Imm1G3, Imm2G3, Imm3G3, 2);
        copriCarte(Imm1G2, Imm2G2, Imm3G2, 1);
        m.prossimoTurno();
            labelAllert.setText("Turno di\t: "+labelGiocatore3.getText());
            checkBot(2);
        }
    }


    public void muoviCarta1G3(MouseEvent mouseEvent) throws FileNotFoundException, IOException{
        if(m.getTurno()==3&&m.getCarteGiocatore(2).get(0)!=null) {
            traslaCartaDaGiocatoreAlCampo(Imm1G3, 2, cartaSelG3);
            m.muoviCarta(2, 0);
            m.CalcolaPunteggio();
            aggiornaPunteggio();
            if (m.getMazzo().getMazzo().size() > 0) {
                redistribuisciCarteMazzo();
                checkBot(0);
            }else {
                scopriCarte(Imm1G1, Imm2G1, Imm3G1, 0);
                copriCarte(Imm1G2, Imm2G2, Imm3G2, 1);
                copriCarte(Imm1G3, Imm2G3, Imm3G3, 2);
            }
           this.checkVincitore();
            m.prossimoTurno();
            if(m.getGiocatori().get(2).getCarte().size()>0)
                checkBot(0);
            labelAllert.setText("Turno di\t: "+labelGiocatore1.getText());
        }
    }

    public void muoviCarta2G3(MouseEvent mouseEvent) throws FileNotFoundException,IOException{
        if(m.getTurno()==3&&m.getCarteGiocatore(2).get(1)!=null) {
            traslaCartaDaGiocatoreAlCampo(Imm2G3, 2, cartaSelG3);
            m.muoviCarta(2, 1);
            m.CalcolaPunteggio();
            aggiornaPunteggio();
            if (m.getMazzo().getMazzo().size() > 0) {
                redistribuisciCarteMazzo();

        } else {
                scopriCarte(Imm1G1, Imm2G1, Imm3G1, 0);
                copriCarte(Imm1G2, Imm2G2, Imm3G2, 1);
                copriCarte(Imm1G3, Imm2G3, Imm3G3, 2);
            }

            this.checkVincitore();
            m.prossimoTurno();
            if(m.getGiocatori().get(2).getCarte().size()>0)
                checkBot(0);
            labelAllert.setText("Turno di\t: "+labelGiocatore1.getText());
        }
    }


    public void muoviCarta3G3(MouseEvent mouseEvent) throws FileNotFoundException,IOException {
        if(m.getTurno()==3&&m.getCarteGiocatore(2).get(2)!=null){
        traslaCartaDaGiocatoreAlCampo(Imm3G3, 2, cartaSelG3);
        m.muoviCarta(2, 2);
        m.CalcolaPunteggio();
        aggiornaPunteggio();
        if (m.getMazzo().getMazzo().size() > 0) {
            redistribuisciCarteMazzo();

        } else {
            scopriCarte(Imm1G1, Imm2G1, Imm3G1, 0);
            copriCarte(Imm1G2, Imm2G2, Imm3G2, 1);
            copriCarte(Imm1G3, Imm2G3, Imm3G3, 2);
        }
        this.checkVincitore();
        m.prossimoTurno();
        if(m.getGiocatori().get(2).getCarte().size()>0)
            checkBot(0);
        labelAllert.setText("Turno di\t: " + labelGiocatore1.getText());
        }
    }

    public void gioca(ActionEvent event) {
        giocaButton.setVisible(false);
        scopriCarte(Imm1G1,Imm2G1,Imm3G1,0);
        copriCarte(Imm1G2,Imm2G2,Imm3G2,1);
        copriCarte(Imm1G3,Imm2G3,Imm3G3,2);
        checkBot(0);
    }

    public void checkBot(int giocatoredopo){
        if(m.getGiocatori().get(giocatoredopo).getUsername().length()>3&&m.getGiocatori().get(giocatoredopo).getUsername().substring(0,3).equalsIgnoreCase("bot") ) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.7));
            pause.setOnFinished(event -> {
                int indice = -1;
                switch (giocatoredopo) {
                    case(0): if(labelGiocatore1.getText().substring(0,3).equalsIgnoreCase("bot")||labelGiocatore2.getText().substring(0,3).equalsIgnoreCase("bot") || labelGiocatore3.getText().substring(0,3).equalsIgnoreCase("bot"))
                        indice = (m.getGiocatori().get(giocatoredopo)).giocaCartaMigliore();
                        switch (indice) {
                            case (0): {Imm1G1.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, Imm1G1.getLayoutX(), Imm1G1.getLayoutY(), 0, 0, null, 1, true, true, true, true, true, true, true, true, true, true, null));};break;
                            case (1): {Imm2G1.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, Imm2G1.getLayoutX(), Imm2G1.getLayoutY(), 0, 0, null, 1, true, true, true, true, true, true, true, true, true, true, null));};break;
                            case (2): {Imm3G1.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, Imm3G1.getLayoutX(), Imm3G1.getLayoutY(), 0, 0, null, 1, true, true, true, true, true, true, true, true, true, true, null));}}break;
                    case (1): if (labelGiocatore2.getText().substring(0,3).equalsIgnoreCase("bot") || labelGiocatore2.getText().substring(0,3).equalsIgnoreCase("bot"))
                        indice = (m.getGiocatori().get(giocatoredopo)).giocaCartaMigliore();
                        switch (indice) {
                            case (0): {Imm1G2.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, Imm1G2.getLayoutX(), Imm1G2.getLayoutY(), 0, 0, null, 1, true, true, true, true, true, true, true, true, true, true, null));};break;
                            case (1): {Imm2G2.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, Imm2G2.getLayoutX(), Imm2G2.getLayoutY(), 0, 0, null, 1, true, true, true, true, true, true, true, true, true, true, null));};break;
                            case (2): {Imm3G2.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, Imm3G2.getLayoutX(), Imm3G2.getLayoutY(), 0, 0, null, 1, true, true, true, true, true, true, true, true, true, true, null));}}break;
                    case (2): if (labelGiocatore3.getText().substring(0,3).equalsIgnoreCase("bot") || labelGiocatore3.getText().substring(0,3).equalsIgnoreCase("bot"))
                        indice = (m.getGiocatori().get(giocatoredopo)).giocaCartaMigliore();
                        switch (indice) {
                            case (0): {Imm1G3.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, Imm1G3.getLayoutX(), Imm1G3.getLayoutY(), 0, 0, null, 1, true, true, true, true, true, true, true, true, true, true, null));};break;
                            case (1): {Imm2G3.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, Imm2G3.getLayoutX(), Imm2G3.getLayoutY(), 0, 0, null, 1, true, true, true, true, true, true, true, true, true, true, null));};break;
                            case (2): {Imm3G3.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, Imm3G3.getLayoutX(), Imm3G3.getLayoutY(), 0, 0, null, 1, true, true, true, true, true, true, true, true, true, true, null));}break;}
                }
            });
            pause.play();
        }
    }


    public void traslaCartaDaGiocatoreAlCampo(ImageView carta, int indiceGiocatore, ImageView cartaInCampo){
          int X = (int) carta.getLayoutX();
          int Y = (int) carta.getLayoutY();

        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.3), carta);
         switch (indiceGiocatore){
             case(0): {transition1.setByX(cartaSelG1.getLayoutX()-X); transition1.setByY(cartaSelG1.getLayoutY()-Y);}break;
             case(1): {transition1.setByX(cartaSelG2.getLayoutX()-X); transition1.setByY(cartaSelG2.getLayoutY()-Y);}break;
             case(2): {transition1.setByX(cartaSelG3.getLayoutX()-X); transition1.setByY(cartaSelG3.getLayoutY()-Y);}break;
         }
        TranslateTransition transition1Back = new TranslateTransition(Duration.seconds(0.2), carta);
        transition1.setOnFinished(event -> {
            cartaInCampo.setImage(carta.getImage());
            transition1Back.setByX(-transition1.getByX());
            transition1Back.setByY(-transition1.getByY());
            carta.setImage(null);
            transition1Back.play();
        });
         transition1.play();
    }


    private void aggiornaPunteggio() { //questo metodo aggiorna in front-end il punteggio dei giocatori, e fa sparire le carte in campo una volte schierate tutte e calcolato il punteggio
        Giocatore1Score.setText("" + m.getGiocatori().get(0).getTotalScore());
        Giocatore2Score.setText("" + m.getGiocatori().get(1).getTotalScore());
        Giocatore3Score.setText("" + m.getGiocatori().get(2).getTotalScore());

        Timer timer=new Timer();TimerTask tm=new TimerTask() {
            @Override
            public void run() {

                TranslateTransition transition1 = createTranslateTransition(cartaSelG1, 700);
                TranslateTransition transition2 = createTranslateTransition(cartaSelG2, 700);
                TranslateTransition transition3 = createTranslateTransition(cartaSelG3, 700);

                // Aggiungi una pausa di mezzo secondo prima di iniziare il ripristino
                PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.3));

                // Ripristina la posizione originale
                TranslateTransition resetTransition1 = createTranslateTransition(cartaSelG1, -700);
                TranslateTransition resetTransition2 = createTranslateTransition(cartaSelG2, -700);
                TranslateTransition resetTransition3 = createTranslateTransition(cartaSelG3, -700);

                // Imposta la sequenza di transizioni
                transition1.setOnFinished(event -> {

                    pauseTransition.play();
                    pauseTransition.setOnFinished(pauseEvent -> {
                         cartaSelG1.setImage(null);
                         cartaSelG2.setImage(null);
                         cartaSelG3.setImage(null);
                         resetTransition1.setDuration(Duration.millis(200));
                        resetTransition2.setDuration(Duration.millis(200));
                        resetTransition3.setDuration(Duration.millis(200));
                        resetTransition1.play();
                        resetTransition2.play();
                        resetTransition3.play();
                    });
                });
                transition1.play();
                transition2.play();
                transition3.play();
            }
        };
       timer.schedule(tm, 1000);
    }

    private TranslateTransition createTranslateTransition (ImageView imageView,double deltaX){
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.4), imageView);
            transition.setByX(deltaX); // Sposta di deltaX pixel verso destra o sinistra
            return transition;
    }

    public void redistribuisciCarteMazzo(){
        // Crea un'animazione per spostare imageView1
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.5), ImmMazzo1);

        // Crea un'animazione per spostare imageView2
        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(0.5), ImmMazzo2);

        // Crea un'animazione per spostare imageView3
        TranslateTransition transition3 = new TranslateTransition(Duration.seconds(0.5), ImmMazzo3);

        switch(m.indiceCartaNullaGiocatore(0)){
            case(0):{transition1.setToX(-75);transition1.setToY(-169);}break;
            case(1):{transition1.setToX(6);transition1.setToY(-198);}break;
            case(2):{transition1.setToX(81);transition1.setToY(-225);}break;
        }
        switch(m.indiceCartaNullaGiocatore(1)){
            case(0):{transition2.setToX(458);transition2.setToY(-235);}break;
            case(1):{transition2.setToX(537);transition2.setToY(-198);}break;
            case(2):{transition2.setToX(625);transition2.setToY(-169);}break;
        }
        switch(m.indiceCartaNullaGiocatore(2)){
            case(0):{transition3.setToX(180);transition3.setToY(58);}break;
            case(1):{transition3.setToX(264);transition3.setToY(58);}break;
            case(2):{transition3.setToX(345);transition3.setToY(58);}break;
        }
        transition1.setOnFinished(event -> {
             ImmMazzo1.setImage(null);
             ImmMazzo2.setImage(null);
             ImmMazzo3.setImage(null);
             m.giocatoriPescano();
             scopriCarte(Imm1G1,Imm2G1,Imm3G1, 0);
             copriCarte(Imm1G2,Imm2G2,Imm3G2,1);
             copriCarte(Imm1G3,Imm2G3,Imm3G3,2);
             this.returnImageViewsToOriginalPosition();
             if(m.getMazzo().getMazzo().size()!=0)
             labelCarteMazzo.setText(""+m.getMazzo().getMazzo().size());
             else{
                 labelCarteMazzo.setText(""+m.getMazzo().getMazzo().size());
                 labelCarteMazzo.setVisible(false);
             }
        });
        // Avvia le animazioni
        transition1.play();
        transition2.play();
        transition3.play();
    }

    private void returnImageViewsToOriginalPosition() { // metodo per far ritornare le carte distribuite dal mazzo ai giocatori al mazzo
        // Crea le animazioni di ritorno veloce
        TranslateTransition returnTransition1 = new TranslateTransition(Duration.seconds(0.7), ImmMazzo1);
        returnTransition1.setToX(ImmMazzo.getLayoutX() - ImmMazzo1.getLayoutX());
        returnTransition1.setToY(ImmMazzo.getLayoutY() - ImmMazzo1.getLayoutY());

        TranslateTransition returnTransition2 = new TranslateTransition(Duration.seconds(0.7), ImmMazzo2);
        returnTransition2.setToX(ImmMazzo.getLayoutX() - ImmMazzo2.getLayoutX());
        returnTransition2.setToY(ImmMazzo.getLayoutY() - ImmMazzo2.getLayoutY());

        TranslateTransition returnTransition3 = new TranslateTransition(Duration.seconds(0.7), ImmMazzo3);
        returnTransition3.setToX(ImmMazzo.getLayoutX() - ImmMazzo3.getLayoutX());
        returnTransition3.setToY(ImmMazzo.getLayoutY() - ImmMazzo3.getLayoutY());

        // Quando le animazioni di ritorno sono completate
        returnTransition1.setOnFinished(event -> {
            if(m.getMazzo().getMazzo().size()!=0) {
                ImmMazzo1.setImage(new Image(getClass().getClassLoader().getResourceAsStream("Resources/Images/backCard2.jpeg")));
                ImmMazzo2.setImage(new Image(getClass().getClassLoader().getResourceAsStream("Resources/Images/backCard2.jpeg")));
                ImmMazzo3.setImage(new Image(getClass().getClassLoader().getResourceAsStream("Resources/Images/backCard2.jpeg")));
            }
            else  ImmMazzo.setImage(null) ;
            });
        // Avvia le animazioni di ritorno veloce
        returnTransition1.play();
        returnTransition2.play();
        returnTransition3.play();
    }

    public void checkVincitore() throws IOException{
        LabelVincitore.setVisible(false);
        File file=new File("LeaderBoardFile.csv");
        if (m.getGiocatori().get(2).getCarte().get(0) == null && m.getGiocatori().get(2).getCarte().get(1) == null && m.getGiocatori().get(2).getCarte().get(2) == null){
            if(file.exists()) {
                ModificaLeaderBoard.aggiornaLeaderBoard(m.getPartita());
            }
            labelAllert.setVisible(false);
            PauseTransition pause=new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                LabelVincitore.setVisible(true);
                buttonVisualizzaLeaderBoard.fire();
            });

            m.trovaVincitore();

            LabelVincitore.setText("VINCITORE : "+ m.getVincitore().getUsername());
            vincitore = m.getVincitore().getUsername();
            System.out.println(m.getVincitore());
            m.salvaPartita();
            pause.play();
        }
    }

    public void tornaIndietro(ActionEvent event) throws IOException {

        if(m.getPartita().getCarteInCampo().size()==0) {
            if (Integer.parseInt(m.getPartita().getCodice()) < 2000) {

                if (m.getPartita().getVincitore() == null)  // se la partita non è ancora finita
                    m.salvaPartita();


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/fxmlFiles/TorneoGioca.fxml"));
                Parent root = loader.load();
                TorneoGiocaController torneoGiocaController = loader.getController();
                torneoGiocaController.setFile(this.LabelCodiceTorneo.getText());
                torneoGiocaController.visualizzaCodiceTorneo(this.LabelCodiceTorneo.getText());
                torneoGiocaController.inizializza();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
                scene.getStylesheets().add(css);
                stage.setScene(scene);
                stage.show();
            }
            else {
                if (m.getPartita().getVincitore() == null)
                    m.salvaPartita();


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/fxmlFiles/PaginaIniziale.fxml"));
                Parent root = loader.load();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
                scene.getStylesheets().add(css);
                stage.setScene(scene);
                stage.show();
                if (m.getPartita().getVincitore() != null) {
                    String filePath = m.getPartita().getCodice() + ".csv";
                      //System.out.println("codice partita"+m.getPartita().getCodice());
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

                Scanner scan = new Scanner(new File("PartiteETornei.csv"));
                 ArrayList<String> codici=new ArrayList<String>();
                 while(scan.hasNextLine()){
                      String riga = scan.nextLine();
                      codici.add(riga);
                  }
                 System.out.println(codici.remove(m.getPartita().getCodice()));

                  PrintWriter scrivo = new PrintWriter("PartiteETornei.csv");
                    for(int i=0; i<codici.size(); i++) {
                        System.out.println(codici.get(i));
                        scrivo.println(codici.get(i));
                    }
                    scrivo.close();
            }
        }
        else
            Allert.showAlert(Alert.AlertType.INFORMATION, "Errore", "Non puoi tornare indietro e salvare se prima non termina la mano");
    }

    public void setCodiceTorneo(String codiceTorneo){
        this.LabelCodiceTorneo.setText(codiceTorneo);
    }
}