package Chat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;


public class Controls {

    private User user;
    @FXML
    private VBox vlist;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    public TextArea textArea;
    @FXML
    private TextField textField;
    @FXML
    private TextField textField2;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView chooseImage;
    @FXML
    private FileChooser fileChooser;

    public Controls() {
    }

    void initialize(String name) {
        user = createClient();
        user.setName(name);
        user.start();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
    }
    @FXML
    public void sendImage() throws Exception {
        File selectedFile = fileChooser.showOpenDialog(null);
        Image image = null;
        if(selectedFile != null){
            image = new Image(selectedFile.toURI().toString());
        }
        MessageCloud temp = new MessageCloud(user.getName(),null, image);
        ChatBubble chatBubble = new ChatBubble(user.getName(),temp);
        user.addMessage(chatBubble);
        vlist.getChildren().add(user.getMessage(1));
        user.send(temp);
    }
    @FXML
    public void undo() throws Exception {
        if(!user.messages.isEmpty()){
            for(int i=1; i<=user.messages.size(); i++){
                MessageCloud del = new MessageCloud(user.getMessage(i).label.getText(),user.getMessage(i).textArea.getText(),user.getMessage(i).imageView.getImage());
                if(del.getName().equals(user.getName())){
                    del.todelete = true;
                    del.pos=i;
                    user.send(del);
                    vlist.getChildren().remove(user.messages.size()-i);
                    user.messages.remove(i-1);
                    return;
                }
            }
        }
    }
    @FXML
    public void send(){
        String message = textField2.getText();
        textField2.clear();
        if(message.equals("")){return;}
        MessageCloud temp = new MessageCloud(user.getName(),message,null);
        ChatBubble chatBubble = new ChatBubble(user.getName(),temp);
        chatBubble.hBox.setAlignment(Pos.CENTER_LEFT);
        user.addMessage(chatBubble);
        vlist.getChildren().add(user.getMessage(1).hBox);
        scrollPane.vvalueProperty().bind(vlist.heightProperty());
        try {
            user.send(temp);
        }catch (Exception e){ e.printStackTrace(); }
    }
    private User createClient() {
        return new User("127.0.0.1",5555,data-> Platform.runLater(() -> {
            ChatBubble chatBubble = new ChatBubble(data.getName(),data);
            if(data.todelete && !data.getName().equals(user.getName())){
                vlist.getChildren().remove(user.messages.size()-data.pos);
                user.messages.remove(data.pos -1);
            }
            else {
                chatBubble.hBox.setAlignment(Pos.CENTER_RIGHT);
                user.addMessage(chatBubble);
                vlist.getChildren().add(user.getMessage(1).hBox);
            }
            scrollPane.vvalueProperty().bind(vlist.heightProperty());
        })
        );
    }
}
