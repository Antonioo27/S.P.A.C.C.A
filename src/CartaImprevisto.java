import javafx.scene.image.Image;
import java.util.ArrayList;

public abstract class CartaImprevisto implements Carta {
    String frase;
    Image immagine;
    String id;

    public CartaImprevisto(){
        this.frase=null;
        this.immagine=null;
        this.id="";
    }

    public CartaImprevisto(Image imm){
        this.frase=null;
        this.immagine=imm;
        this.id="";
    }

    public CartaImprevisto(String frase, String id){
        this.frase=frase;
        this.immagine=null;
        this.id=id;
    }

   public CartaImprevisto(String id, Image imm,String frase){
        this.frase=frase;
        this.immagine=imm;
        this.id=id;
    }
    @Override
    public int getFollower(){
        return 0;
    }

    public String getFrase() {
        return this.frase;
    }

    @Override
    public Image getImage() {
        return this.immagine;
    }

    @Override
    public boolean equals(Image imm) {
        if(imm==this.immagine)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return this.frase;
    }
    @Override
    public String getNome() {
        return this.id;
    }

    public abstract void calcolaEffetto(ArrayList<Giocatore> giocatori, int indice, ArrayList<Carta> carteInCampo);
}
