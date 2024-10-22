import javafx.scene.image.Image;

import java.util.ArrayList;



public class CartaAnonymous extends CartaImprevisto{

    public CartaAnonymous(String id, Image imm, String frase){
        super(id, imm, frase);
    }

    @Override
    public void calcolaEffetto(ArrayList<Giocatore> giocatori, int indice, ArrayList<Carta> carteInCampo) {
        int vincitore = carteInCampo.indexOf(Partita.TrovaMaggiore(carteInCampo));
        if (vincitore == -1)
            giocatori.get(indice).setTotalScore(giocatori.get(indice).getTotalScore() + 0);
        else {
            giocatori.get(vincitore).setTotalScore(giocatori.get(vincitore).getTotalScore() - Partita.TrovaMaggiore(carteInCampo).getFollower() / 2);
            giocatori.get(indice).setTotalScore(giocatori.get(indice).getTotalScore() + Partita.TrovaMaggiore(carteInCampo).getFollower() / 2);
        }
    }
}
