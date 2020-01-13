package Chat;

import Server.Server;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter {
    private Stage stage;
    private boolean isServer;
    @FXML
    private Button clientButton;

    @FXML
    private TextField setPort;

    @FXML
    private Button serverButton;

    void initialize(Stage stage) {
        this.stage = stage;
        stage.show();
    }

    @FXML
    void setPort() throws IOException {
        int port = Integer.parseInt(setPort.getText());
        FXMLLoader loader = new FXMLLoader();
        if (isServer) {
            stage.close();
            Server server = new Server(port);
            System.out.println("<< Utworzono serwer.\n<< Port serwera : " + port);
            server.start();
        } else {
            loader.setLocation(this.getClass().getResource("../sample.fxml"));
        }
        Parent subScene = loader.load();
        Controller controller = loader.getController();
        controller.initialize(stage, port);
        stage.setScene(new Scene(subScene));
        stage.show();
    }

    @FXML
    void chooseClient() {
        setPort.setEditable(true);
        isServer = false;
    }

    @FXML
    void chooseServer() {
        setPort.setEditable(true);
        isServer = true;
    }

}
