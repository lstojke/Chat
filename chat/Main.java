package client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.NetPermission;

public class Main extends Application {


    private TextArea messages = new TextArea();
    private Connection connection = createServer();

    private Parent createContent() {
        messages.setPrefHeight(550);
        TextField input = new TextField();
        /*input.setOnAction(event -> {
            String message = "Server: ";
            message += input.getText();
            input.clear();
            messages.appendText(message + "\n");
            try {
                connection.send(message);
            }catch (Exception e){
                messages.appendText("Wyslanie nieudane!\n");
            }
        });*/
        VBox root = new VBox(20,messages,input);
        root.setPrefSize(600,600);
        return root;
    }
    public void init() throws Exception{
        connection.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("client.fxml"));

        Parent stackPane = loader.load();
        Scene scene = new Scene(stackPane,400,600);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            primaryStage.close();
            Platform.exit();
            System.exit(0);
        });
    }

    public void stop() throws Exception{
        connection.closeConnection();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private Server createServer() {
        return new Server(5000, data -> {
            Platform.runLater(() -> {
                messages.appendText(data.toString() + "\n");
            });
        }
                , data2 -> {
            Platform.runLater(() -> {
                messages.appendText(data2.toString() + "\n");
            });
        });
    }

}


