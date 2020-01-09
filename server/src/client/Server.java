package client;

import Chat.MessageCloud;

import java.io.Serializable;
import java.util.function.Consumer;

public class Server extends Connection {
    private int port;

    public Server (int port, Consumer<MessageCloud> onReceiveCallBack) {
        super(onReceiveCallBack);
        this.port = port;
    }


    @Override
    protected int getPort(){
        return port;
    }
}

