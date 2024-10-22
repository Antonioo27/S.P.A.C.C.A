import javafx.scene.image.Image;

import java.util.ArrayList;

public class CartaProdDifettoso extends CartaImprevisto{

    public CartaProdDifettoso(String id, Image imm,String frase){
        super(id, imm, frase);
    }

    @Override
    public void calcolaEffetto(ArrayList<Giocatore> giocatori, int indice, ArrayList<Carta> carteInCampo){
        if(indice<2)
            giocatori.get(indice+1).setTotalScore(giocatori.get(indice+1).getTotalScore()/2);
        else
            giocatori.get(0).setTotalScore(giocatori.get(0).getTotalScore()/2);

    }

}
