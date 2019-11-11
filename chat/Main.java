package chat;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("FX.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/FX.fxml"));

        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane,400,600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}