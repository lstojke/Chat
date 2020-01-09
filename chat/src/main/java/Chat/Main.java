package Chat;

import Server.Server;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static boolean isServer = false;
    @Override
    public void start(Stage primaryStage) throws Exception {
        if(!isServer) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("../sample.fxml"));
            Parent subScene = loader.load();
            Controller controller = loader.getController();
            controller.initialize();
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(this.getClass().getResource("../mainScene2.fxml"));
            Parent mainScene = loader1.load();
            Controls controls = loader1.getController();
            primaryStage.setTitle("Chat");
            primaryStage.setScene(new Scene(subScene));
            primaryStage.show();
            controller.textField.setOnAction(event -> { //Gdy wpisane bedzie imie, wlacz glowne okno
                controls.initialize(controller.getname());
                primaryStage.setScene(new Scene(mainScene));
            });
            primaryStage.setOnCloseRequest(windowEvent -> {
                primaryStage.close();
                Platform.exit();
                System.exit(0);
            });
        }
        else {
            Server server = createServer();
            server.start();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    private Server createServer() {
        return new Server(5000, data -> {});
    }
}


