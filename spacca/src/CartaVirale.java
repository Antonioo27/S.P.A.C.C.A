import javafx.scene.image.Image;
import java.util.ArrayList;

public class CartaVirale extends CartaImprevisto{

    public CartaVirale(String id, Image imm, String frase){
        super(id, imm, frase);
    }

    @Override
    public void calcolaEffetto(ArrayList<Giocatore> giocatori, int indice, ArrayList<Carta> carteInCampo){
        giocatori.get(indice).setTotalScore(giocatori.get(indice).getTotalScore()+80);
    }

}
