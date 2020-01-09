package Chat;

import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class Connection extends Parent {
    private Consumer<MessageCloud> receiveData;
    private ConnectionThread cThread = new ConnectionThread();
    Connection(Consumer<MessageCloud> receiveData){
        this.receiveData = receiveData;
        cThread.setDaemon(true);
    }
    void start() {
        cThread.start();
    }
    void send(MessageCloud data) throws Exception{
        cThread.out.writeObject(data);
        //cThread.out.flush();
    }
    public void sendImage(Image image)throws Exception{
        cThread.out.writeObject(image);
    }
    public void closeConnection() throws Exception{
        cThread.socket.close();
    }

    abstract String getIP();
    abstract int getPort();
    abstract String getName();

    private class ConnectionThread extends Thread{
        private Socket socket;
        private ObjectOutputStream out;

        @Override
        public void run(){ // Laczenie z serwerem i odbieranie od niego danych
            try(Socket socket = new Socket(getIP(),getPort());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                this.socket = socket;
                this.out = out;
                while(true){
                    MessageCloud data = (MessageCloud) in.readObject();
                    if(!data.getName().equals(Connection.this.getName())){
                        receiveData.accept(data);
                    }
                }
            }catch (Exception e){
                MessageCloud fail = new MessageCloud("Server","Polaczenie przerwane",null);
                receiveData.accept(fail);
            }

        }
    }
}
