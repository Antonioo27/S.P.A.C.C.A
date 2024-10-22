import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Partita {

    private int turno=1;
    private Giocatore vincitore=null;
    private String codice;
    private ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>(3);
    private Mazzo mazzo;
    private ArrayList<Carta> carteInCampo = new ArrayList<Carta>(3);

    public Partita(ArrayList<String> giocatoriNomi, String codice) {
        this.mazzo = new Mazzo();
        this.codice = codice;
        for (int i = 0; i < giocatoriNomi.size(); i++) {
            this.giocatori.add(new Giocatore(giocatoriNomi.get(i), 0, new ArrayList<Carta>(Arrays.asList(null, null, null))));
            for (int j = 0; j < 3; j++)
                this.giocatori.get(i).pesca(this.mazzo);
        }
    }

    public Partita() {
        this.codice = null;
        this.mazzo = new Mazzo();
        this.giocatori = new ArrayList<Giocatore>();
    }

    public Partita(ArrayList<Giocatore> giocatori, String codice, LinkedList<Carta> mazzoRimanente) {
        this.codice = codice;
        this.mazzo.setMazzo(mazzoRimanente);
        this.giocatori = giocatori;
    }

    public void salvaPartita() throws FileNotFoundException {
        PrintWriter scrivo = new PrintWriter(codice + ".csv");
        if(vincitore!=null){// se esiste vincitore
            scrivo.println(this.vincitore.getUsername()+","+this.vincitore.getTotalScore());
            for (int i = 0; i < giocatori.size(); i++) {
                scrivo.println(giocatori.get(i).toString());
            }
        }
        else {
            scrivo.println("."); // non c'è vincitore
            scrivo.println(mazzo.toString() + "\n");
            for (int i = 0; i < giocatori.size(); i++) {
                scrivo.println(giocatori.get(i).toString());
            }
        }
        scrivo.close();
    }

    public void caricaPartita(File file) throws FileNotFoundException {
        Scanner scan2 = new Scanner(file);
           String su = scan2.nextLine();
        if(su.equalsIgnoreCase(".")){//non c'e un vincitore

            this.codice=file.getName().substring(0,4);
            File file2=file;
            Scanner scan = new Scanner(file2);
            Mazzo nuovo = new Mazzo();
            scan.nextLine();
            String riga=scan.nextLine();

            String[] RimanenzeFile = riga.split(",");

            LinkedList<Carta> mazzoRimanente = new LinkedList<Carta>();
            for (int i = 0; i < RimanenzeFile.length; i++) {
                for (int j = 0; j < nuovo.getMazzo().size(); j++) {
                    Carta c = nuovo.getMazzo().get(j);
                    if (c.getNome().equalsIgnoreCase(RimanenzeFile[i])) {
                        mazzoRimanente.add(c);
                        nuovo.getMazzo().remove(c);
                        break;
                    }
                }
            }

            this.mazzo.setMazzo(mazzoRimanente);
            scan.nextLine();
            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                if (s != "") {
                    String[] str = s.split(",");
                    ArrayList<Carta> mano = new ArrayList<Carta>();
                    for (int i = 2; i < str.length; i++) {
                        for (int j = 0; j < nuovo.getMazzo().size(); j++) {
                            Carta c = nuovo.getMazzo().get(j);
                            if (c.getNome().equalsIgnoreCase(str[i])) {
                                mano.add(c);
                            }

                        }
                    }
                             if(mano.size()==2) {
                             mano.add(null);
                             }
                             else if(mano.size()==1){
                                 mano.add(null);
                                 mano.add(null);
                             }
                    this.giocatori.add(new Giocatore(str[0], Integer.parseInt(str[1]), mano));
                }
            }
        }
        else{ // se esiste vincitore
            this.codice=file.getName().substring(0,4);
           Scanner scan=new Scanner(file);
           String riga = scan.nextLine();
           String[] str=riga.split(",");
           this.vincitore=new Giocatore(str[0],Integer.parseInt(str[1]));
           while(scan.hasNextLine()){
               String riga2 = scan.nextLine();
               if (riga2 != "") {
                    String[] str2 = riga2.split(",");
                    Giocatore g = new Giocatore(str2[0], Integer.parseInt(str2[1]));
                    this.giocatori.add(g);
               }
           }
        }
    }


    public void prossimoTurno(){
        if(turno==3)
            turno=1;
        else
            turno++;
    }

    public int getTurno(){
        return this.turno;
      }

    public void muoviCarta(int IndiceGiocatore, int IndiceCarta){ //indice partendo da 0
        this.carteInCampo.add(this.getCarteGiocatore(IndiceGiocatore).get(IndiceCarta));
        this.getCarteGiocatore(IndiceGiocatore).set(IndiceCarta,null);
    }

    public ArrayList<Carta> getCarteGiocatore(int indiceGiocatore) {
        ArrayList<Carta> carte=new ArrayList<Carta>();
        switch(indiceGiocatore){

            case(0): carte = this.getGiocatori().get(0).getCarte();break;
            case(1): carte = this.getGiocatori().get(1).getCarte();break;
            case(2): carte = this.getGiocatori().get(2).getCarte();break;
        }
        return carte;
    }

    public Mazzo getMazzo(){
        return this.mazzo;
    }

    public ArrayList<Carta> getCarteInCampo(){
        return this.carteInCampo;
    }

    public ArrayList<Giocatore> getGiocatori(){
        return this.giocatori;
    }

    public String getCodice(){
        return this.codice;
    }


    public void CalcolaPunteggio() throws FileNotFoundException{

        int indiceCartaMaggiore = carteInCampo.indexOf(TrovaMaggiore(carteInCampo));

        for(int i=0; i<carteInCampo.size(); i++) {
            if (carteInCampo.get(i) instanceof CartaImprevisto) {
                ((CartaImprevisto) carteInCampo.get(i)).calcolaEffetto(this.giocatori, i, this.carteInCampo);
            }
        }
        if(indiceCartaMaggiore > -1)
            giocatori.get(indiceCartaMaggiore).setTotalScore(giocatori.get(indiceCartaMaggiore).getTotalScore() + carteInCampo.get(indiceCartaMaggiore).getFollower());

        this.carteInCampo.clear();
    }

    //Metodo per trovare la carta maggiore tra quelle schierate in campo
    public static CartaInfluencer TrovaMaggiore(ArrayList<Carta> list){

        CartaInfluencer magg = new CartaInfluencer();
        if(list.get(0) instanceof CartaInfluencer)
            magg = (CartaInfluencer) list.get(0);
        else if(list.get(1) instanceof CartaInfluencer)
            magg = (CartaInfluencer) list.get(1);
        else if (list.get(2) instanceof CartaInfluencer) {
            magg = (CartaInfluencer) list.get(2);
        }

        for(int i=0; i<list.size(); i++) {
            if (list.get(i) instanceof CartaInfluencer && list.get(i).getFollower() > magg.getFollower())
                magg = (CartaInfluencer) list.get(i);
        }
        return magg;
    }

    public void giocatoriPescano(){
        if(this.mazzo.getMazzo().size()>0) {
            this.giocatori.get(0).pesca(this.mazzo);
            this.giocatori.get(1).pesca(this.mazzo);
            this.giocatori.get(2).pesca(this.mazzo);
        }
    }

    public int indiceCartaNullaGiocatore(int indiceGiocatore){
       return this.getGiocatori().get(indiceGiocatore).getCarte().indexOf(null);
    }

    public void trovaVincitore(){
        Giocatore g= this.giocatori.get(0);
        for(int i=1; i<this.giocatori.size(); i++){
            if(g.getTotalScore()<this.giocatori.get(i).getTotalScore())
                g=this.giocatori.get(i);
        }
        this.vincitore=g;
    }

    public Giocatore getVincitore(){
        return vincitore;
    }

    public void setCodice(String nuovo){
        this.codice=nuovo;
    }

}
