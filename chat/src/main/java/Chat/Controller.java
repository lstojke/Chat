package Chat;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    TextField textField;

    String getName() {
        return textField.getText();
    }


}
