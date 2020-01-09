package Server;

import Chat.MessageCloud;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;


public abstract class ServerConnection {

        private Consumer<MessageCloud> onReceiveCallBack;
        private ArrayList clients;
        private ObjectOutputStream out;

        ServerConnection(Consumer<MessageCloud> onReceiveCallBack){
            this.onReceiveCallBack = onReceiveCallBack;
        }
        public void start() {
            clients = new ArrayList();

            try{
                ServerSocket serverSocket = new ServerSocket(5555);

                while(true)
                {
                    Socket socket = serverSocket.accept();
                    out = new ObjectOutputStream(socket.getOutputStream());
                    clients.add(out);
                    Thread thread = new Thread(new ConnThread(socket));
                    thread.start();

                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        private void send(String data){
            System.out.println(data);
        }
        public void closeConnection(){}

        abstract int getPort();

        class ConnThread extends Thread{

            Socket socket;
            ObjectInputStream in;

            ConnThread(Socket s){
                try{
                    send("<< Polaczono z klientem o porcie :" + s.getPort());
                    socket = s;
                    in = new ObjectInputStream(socket.getInputStream());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void run(){
                MessageCloud data;
                ObjectOutputStream temp;
                try{
                    while(true){
                        data = (MessageCloud) in.readObject();
                        System.out.println(data.getImage()==null);
                        for (Object client : clients) {
                            temp = (ObjectOutputStream) client;
                            temp.writeObject(data);
                            temp.flush();
                        }
                    }
                }catch (Exception e){
                    System.out.println("ConnThread" + e);
                }
            }

        }
}
