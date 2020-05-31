package Chat;

import java.util.Stack;
import java.util.function.Consumer;

public class User extends Connection {
    private Stack<MessageCloud> messages;
    private int port;
    private String IP;
    private String name;

    User(String IP, int port, Consumer<MessageCloud> receiveData) {
        super(receiveData);
        this.port = port;
        this.IP = IP;
        messages = new Stack<>();
    }


    void addMessage(MessageCloud chatBubble) {
        messages.push(chatBubble);
    }

    MessageCloud getMessage(int i) {
        return messages.get(messages.size() - i);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize(){return messages.size();}

    public boolean isEmpty(){return messages.isEmpty();}

    public void deleteMessage(int position){messages.remove(messages.size()-position);}

    @Override
    protected String getIP() {
        return IP;
    }

    @Override
    protected int getPort() {
        return port;
    }
}
