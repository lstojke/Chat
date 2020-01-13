package Chat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    TextField textField;
    private Stage stage;
    private int port;

    void initialize(Stage stage, int port) {
        this.stage = stage;
        this.port = port;
    }

    @FXML
    public void setName() throws IOException {
        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(this.getClass().getResource("../mainScene2.fxml"));
        Parent mainScene = mainLoader.load();
        Controls controls = mainLoader.getController();
        controls.initialize(textField.getText(), port);
        stage.setScene(new Scene(mainScene));
        stage.show();

    }


}
