package client;


import java.io.Serializable;
import java.util.function.Consumer;

public class User extends Connection{
    private int port;
    private String IP;

    public User(String IP, int port, Consumer<String> onReceiveCallBack, Consumer<String> onReceiveCallBack2) {
        super(onReceiveCallBack, onReceiveCallBack2);
        this.port = port;
        this.IP = IP;
    }

    @Override
    protected int getPort(){
        return port;
    }
}
