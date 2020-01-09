package Chat;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class Controller {

    @FXML
    TextField textField;
    public boolean done;
    public void initialize()
    {
        done = false;
    }

    @FXML
    void setname() {
        System.out.println(textField.getText());
        System.out.println(done);
        done = true;
    }

    @FXML
    public String getname() {
        return textField.getText();
    }

    public boolean isSet(){
        return done;
    }


}
