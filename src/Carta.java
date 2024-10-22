import javafx.scene.image.Image;

public interface Carta {

    public  String getNome();

    public String toString();

    public Image getImage();

    public boolean equals(Image imm);

    public int getFollower();

}
