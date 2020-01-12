package Chat;

import javafx.scene.Parent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.function.Consumer;

abstract class Connection extends Parent {
    private Consumer<MessageCloud> receiveData;
    private ConnectionThread cThread = new ConnectionThread();

    Connection(Consumer<MessageCloud> receiveData) {
        this.receiveData = receiveData;
        cThread.setDaemon(true);
    }

    void start() {
        cThread.start();
    }

    void send(MessageCloud data) throws IOException {
        cThread.out.writeObject(data);
    }

    abstract String getIP();

    abstract int getPort();

    abstract String getName();

    private class ConnectionThread extends Thread {
        private ObjectOutputStream out;

        @Override
        public void run() {
            try (Socket socket = new Socket(getIP(), getPort());
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                this.out = out;
                while (true) {
                    MessageCloud data = (MessageCloud) in.readObject();
                    if (!data.getName().equals(Connection.this.getName())) {
                        receiveData.accept(data);
                    }
                }
            } catch (Exception e) {
                MessageCloud fail = new MessageCloud("Server", "Polaczenie przerwane", null);
                receiveData.accept(fail);
            }

        }
    }
}
