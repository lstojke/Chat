package client;

import java.io.Serializable;
import java.util.function.Consumer;

public class Server extends Connection {
    private int port;

    public Server (int port, Consumer<String> onReceiveCallBack, Consumer<String> onReceiveCallBack2) {
        super(onReceiveCallBack, onReceiveCallBack2);
        this.port = port;
    }


    @Override
    protected int getPort(){
        return port;
    }
}

