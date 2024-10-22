import javafx.scene.image.Image;

public class CartaInfluencer  implements Carta{
    String nome;
    public Image immagine;
    public int follower;

    //follower, nome e immagine vanno in base alle vari
    //influnecer che nadreamo a crare(ogni inluencer è una classe)
    public CartaInfluencer(){
        this.nome="Da definire in base all' influencer";
        this.follower=0;
        this.immagine=null;
    }
    public CartaInfluencer(Image imm){
        this.nome="Da definire in base all' influencer";
        this.follower=0;
        this.immagine=imm;
    }
    public CartaInfluencer(String nome, int follower, Image imm){
        this.nome=nome;
        this.follower=follower;
        this.immagine=imm;
    }

    //ogni influencer avra una stringa con il proprio nome
    public String getNome(){
        return this.nome;
    }

    @Override
    public Image getImage() {
        return immagine;
    }

    public int getFollower(){ return this.follower;}

    @Override
    public boolean equals(Image imm) {
        if(imm==this.immagine)
        return true;
        else
            return false;
    }


    @Override
    public String toString() {
        return nome+"\t"+this.follower+",\t";
    }
}
