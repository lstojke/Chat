package Chat;

import java.io.Serializable;

public class MessageCloud implements Serializable {
    private boolean toDelete;
    private int position;
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

    byte[] getBuffer() {
        return forImages;
    }

    String getText() {
        return text;
    }

    String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isToDelete() {
        return toDelete;
    }

    public void setToDelete(boolean toDelete) {
        this.toDelete = toDelete;
    }
}
