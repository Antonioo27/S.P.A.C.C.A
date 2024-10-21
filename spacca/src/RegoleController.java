import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class RegoleController {

    public void tornaIndietro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Resources/fxmlFiles/PaginaIniziale.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

}
