package Chat;

import java.util.Stack;
import java.util.function.Consumer;

public class User extends Connection {
    Stack<ChatBubble> messages;
    private int port;
    private String IP;
    private String name;

    User(String IP, int port, Consumer<MessageCloud> receiveData) {
        super(receiveData);
        this.port = port;
        this.IP = IP;
        messages = new Stack<>();
    }


    void addMessage(ChatBubble chatBubble) {
        messages.push(chatBubble);
    }

    ChatBubble getMessage(int i) {
        return messages.get(messages.size() - i);
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    protected String getIP() {
        return IP;
    }

    @Override
    protected int getPort() {
        return port;
    }
}
