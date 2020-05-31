package Chat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;


public class Controls {

    private User user;
    @FXML
    private VBox vlist;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField textField2;
    @FXML
    private FileChooser fileChooser;

    public Controls() {
    }

    void initialize(String name, int port) {
        user = createClient(port);
        user.setName(name);
        user.start();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
    }

    @FXML
    public void sendImage() throws Exception {
        File selectedFile = fileChooser.showOpenDialog(null);
        byte[] buffer = Files.readAllBytes(selectedFile.toPath());
        MessageCloud temp = new MessageCloud(user.getName(), null, buffer);
        ChatBubble chatBubble = new ChatBubble(user.getName(), temp);
        user.addMessage(temp);
        vlist.getChildren().add(chatBubble.gethBox());
        user.send(temp);
    }

    @FXML
    public void undo() throws Exception {
        if (!user.isEmpty()) {
            for (int i = 1; i <= user.getSize(); i++) {
                MessageCloud del = new MessageCloud(user.getMessage(i).getName(), user.getMessage(i).getText(), user.getMessage(i).getBuffer());

                if (del.getName().equals(user.getName())) {
                    del.setToDelete(true);
                    del.setPosition(i);
                    user.send(del);
                    vlist.getChildren().remove(user.getSize() - i);
                    user.deleteMessage(i);
                    return;
                }
            }
        }
    }

    @FXML
    public void send() {
        String message = textField2.getText();
        textField2.clear();
        if (message.equals("")) {
            return;
        }
        MessageCloud temp = new MessageCloud(user.getName(), message, null);
        ChatBubble chatBubble = new ChatBubble(user.getName(), temp);
        chatBubble.gethBox().setAlignment(Pos.CENTER_LEFT);
        user.addMessage(temp);
        vlist.getChildren().add(chatBubble.gethBox());
        scrollPane.vvalueProperty().bind(vlist.heightProperty());
        try {
            user.send(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User createClient(int port) {
        return new User("127.0.0.1", port, data -> Platform.runLater(() -> {
            ChatBubble chatBubble = new ChatBubble(data.getName(), data);
            if (data.isToDelete() && !data.getName().equals(user.getName())) {
                vlist.getChildren().remove(user.getSize() - data.getPosition());
                user.deleteMessage(data.getPosition());
            } else {
                chatBubble.gethBox().setAlignment(Pos.CENTER_RIGHT);
                user.addMessage(data);
                vlist.getChildren().add(chatBubble.gethBox());
            }
            scrollPane.vvalueProperty().bind(vlist.heightProperty());
        })
        );
    }
}