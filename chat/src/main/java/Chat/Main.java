package Chat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader firstScene = new FXMLLoader(this.getClass().getResource("../starter.fxml"));
        Parent startScene = firstScene.load();
        Starter starter = firstScene.getController();
        starter.initialize(primaryStage);
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(startScene));
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent -> {
            primaryStage.close();
            Platform.exit();
            System.exit(0);
        });
    }
}


