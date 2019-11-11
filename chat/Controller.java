package chat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.*;

public class Controller {
    public Controller(){
        System.out.println("Hi!");
    }

    @FXML
    private TextFlow textFlow;
    @FXML
    private TextField textField;
    @FXML
    private Button button;

    @FXML
    public void textSending(){
        Text temp = new Text(textField.getText()+"\n");
        textFlow.getChildren().add(temp);
        textField.clear();
    }



}
