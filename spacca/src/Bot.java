import java.util.ArrayList;

public class Bot extends Giocatore{

    public Bot(String nome){
      super(nome);
    }
    public Bot(String nome,int punteggio, ArrayList<Carta> carte){
        super(nome, punteggio,carte);
    }

    public int giocaCartaMigliore(){
         return this.getCarte().indexOf(Partita.TrovaMaggiore(this.getCarte()));
    }

}
