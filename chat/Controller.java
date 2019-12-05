package client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Controller {

    @FXML
    private TextFlow textFlow;

    @FXML
    private TextField textField;


    @FXML
    void textSending() {
        Text temp = new Text(textField.getText()+"\n");
        textFlow.getChildren().add(temp);
        textField.clear();

    }

}
