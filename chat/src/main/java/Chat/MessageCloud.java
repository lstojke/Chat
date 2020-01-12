package Chat;

import java.io.Serializable;

public class MessageCloud implements Serializable {
    boolean toDelete;
    int position;
    private String text;
    private String name;
    private byte[] forImages;

    MessageCloud(String name, String text, byte[] buf) {
        this.name = name;
        this.text = text;
        this.forImages = buf;
        toDelete = false;
        position = 0;
    }

    byte[] getBuffer() { return forImages; }

    String getText() { return text; }

    String getName() { return name; }
}
