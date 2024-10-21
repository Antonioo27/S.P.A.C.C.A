import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginAmministratoreController {

    @FXML
    private PasswordField passwordLogin;
    @FXML
    private TextField username_field;

    private String password = "admin";
    private String username = "admin";
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void checkLogin(ActionEvent event) throws IOException {
        String username = username_field.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/fxmlFiles/Home.fxml"));
        root= loader.load();

        HomeController homeController = loader.getController();
        homeController.displayName(username);

        if(passwordLogin.getText().equals(this.password) && username_field.getText().equalsIgnoreCase(username)){

            //Andiamo nella home
             // Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setTitle("HOME PAGE");
            stage.show();
        }
        else {
            Allert.showAlert(Alert.AlertType.INFORMATION, "Errore", "Password o nome utente non corretti");
        }

    }

    public void clearLogin(ActionEvent event) {
        passwordLogin.setText("");
        username_field.setText("");
    }

    public void tornaIndietro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/Resources/fxmlFiles/PaginaIniziale.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = Main.class.getResource("/Resources/fxmlFiles/Css/Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

}