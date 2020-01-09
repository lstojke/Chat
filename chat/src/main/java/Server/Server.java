package Server;

import Chat.MessageCloud;

import java.util.function.Consumer;

public class Server extends ServerConnection {
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

