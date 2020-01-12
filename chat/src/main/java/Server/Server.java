package Server;


public class Server extends ServerConnection {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}

