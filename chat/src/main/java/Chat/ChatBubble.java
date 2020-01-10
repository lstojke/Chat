package Chat;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ChatBubble extends Parent {
    Label label;
    TextArea textArea;
    ImageView imageView;
    VBox vBox;
    HBox hBox;
    ChatBubble(String name, MessageCloud messageCloud){
        label = new Label();
        label.setText(name);
        if(messageCloud.getImage() == null){
            textArea = new TextArea();
            textArea.setText(messageCloud.getText());
            imageView = new ImageView();
            vBox = new VBox(1,label,textArea);

        }
        else {
            imageView = new ImageView();
            imageView.setImage(messageCloud.getImage());
            vBox = new VBox(1,label,imageView);
        }
        vBox.setPrefSize(150,60);
        hBox = new HBox(vBox);
    }
    public MessageCloud getData(){
        return new MessageCloud(label.getText(),textArea.getText(),imageView.getImage());
    }


}
