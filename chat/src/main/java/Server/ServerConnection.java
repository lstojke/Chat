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
        private class ConnectionThread extends Thread{

            private Socket socket;
            private Socket socket2;
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
                    this.socket2 = socket4;
                    this.out2 = out4;
                    /*while(true){
                        MessageCloud data = (MessageCloud) in3.readObject();
                        onReceiveCallBack.accept(data);
                        System.out.println(data.getText());
                        this.out2.writeObject(data);
                    }*/
                }catch (Exception e){
                    MessageCloud fail = new MessageCloud("Server","Polaczenie przerwane",null);
                    onReceiveCallBack.accept(fail);
                }
            }
        }
}
