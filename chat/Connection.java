package client;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public abstract class Connection {
    private Consumer<String> onReceiveCallBack;
    private Consumer<String> onReceiveCallBack2;
    ArrayList klients;
    ObjectOutputStream out;


    private ConnectionThread cThread;
    private ConnectionThread cThread2;


    public Connection(Consumer<String> onReceiveCallBack, Consumer<String> onReceiveCallBack2){
        this.onReceiveCallBack = onReceiveCallBack;
        this.onReceiveCallBack2 = onReceiveCallBack2;
        //cThread.setDaemon(true);
        //cThread2.setDaemon(true);
    }
    public void start() {
        klients = new ArrayList();
        try{
            ServerSocket serverSocket = new ServerSocket(5000);

        while(true)
        {
            Socket socket = serverSocket.accept();
            out = new ObjectOutputStream(socket.getOutputStream());
            klients.add(out);
            Thread thread = new Thread(new ConnThread(socket));
            thread.start();

        }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void send(String data) throws Exception{
        //cThread.out.writeObject(data);
        //cThread2.out.writeObject(data);
    }
    public void closeConnection() throws Exception{
        //cThread.socket.close();
        //cThread2.socket2.close();
    }

    abstract int getPort();

    class ConnThread extends Thread{

        Socket socket;
        ObjectInputStream in;

        public ConnThread(Socket s){
            try{
                    send("<< Polaczono.");
                    socket = s;
                    in = new ObjectInputStream(socket.getInputStream());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @Override
        public void run(){
            String data;
            ObjectOutputStream temp;
            try{
                while((data = (String) in.readObject()) != null){
                    Iterator iterator = klients.iterator();
                    while(iterator.hasNext()){
                        temp = (ObjectOutputStream) iterator.next();
                        temp.writeObject(data);
                        temp.flush();
                    }
                }
            }catch (Exception e){

            }
        }

    }



















    private class ConnectionThread extends Thread{

        private Socket socket;
        private Socket socket2;
        private ObjectOutputStream out;
        private ObjectOutputStream out2;
        @Override
        public void run(){
            try(ServerSocket serverSocket = new ServerSocket(getPort());
                Socket socket3 = serverSocket.accept();
                ObjectOutputStream out3 = new ObjectOutputStream(socket3.getOutputStream());
                ObjectInputStream in3 = new ObjectInputStream(socket3.getInputStream());
                Socket socket4 = serverSocket.accept();
                ObjectOutputStream out4 = new ObjectOutputStream(socket4.getOutputStream());
                ObjectInputStream in4 = new ObjectInputStream(socket4.getInputStream())
            ) {

                this.socket = socket3;
                this.out = out3;
                this.socket2 = socket4;
                this.out2 = out4;
                while(true){
                        String data = (String) in3.readObject();
                        onReceiveCallBack.accept(data);
                        this.out2.writeObject(data);
                        //String data2 = (String) in4.readObject();
                        //onReceiveCallBack2.accept(data2);
                        //out2.writeObject(data2);
                }
            }catch (Exception e){
                onReceiveCallBack.accept("Polacznie przerwane");
                //onReceiveCallBack2.accept("Polacznie przerwane");
            }

        }
    }
}
