package Chat;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;

class ChatBubble extends Parent {
    private Label label;
    private TextArea textArea;
    private byte[] imageBuffer;
    private HBox hBox;

    ChatBubble(String name, MessageCloud messageCloud) {
        label = new Label();
        label.setText(name);
        textArea = new TextArea();
        ImageView imageView;
        VBox vBox;
        if (messageCloud.getBuffer() == null) {
            textArea.setText(messageCloud.getText());
            vBox = new VBox(1, label, textArea);

        } else {
            imageView = new ImageView();
            imageBuffer = messageCloud.getBuffer();
            imageView.setImage(new Image(new ByteArrayInputStream(imageBuffer)));
            vBox = new VBox(1, label, imageView);
        }
        vBox.setPrefSize(150, 60);
        hBox = new HBox(vBox);
    }


    public HBox gethBox() {
        return hBox;
    }
}
