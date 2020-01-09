package Chat;

import java.util.ArrayList;
import java.util.function.Consumer;

public class User extends Connection {
    private int port;
    private String IP;
    private String name;
    ArrayList<ChatBubble> messages;

    public User(String IP, int port, Consumer<MessageCloud> receiveData) {
        super(receiveData);
        this.port = port;
        this.IP = IP;
        messages = new ArrayList<>();
    }


    public void addMessage(ChatBubble chatBubble){
        messages.add(0,chatBubble);
    }
    public ChatBubble getMessage(int i){ return messages.get(i-1);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    protected String getIP(){
        return IP;
    }
    @Override
    protected int getPort(){
        return port;
    }
}
