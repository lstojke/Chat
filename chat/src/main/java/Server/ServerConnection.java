package Server;

import Chat.MessageCloud;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public abstract class ServerConnection {

    private ArrayList<ObjectOutputStream> clients;

    public void start() {
        clients = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(getPort());

            while (true) {
                Socket socket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                clients.add(out);
                Thread thread = new Thread(new ConnThread(socket));
                thread.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    abstract int getPort();

    private void send(String data) {
        System.out.println(data);
    }

    class ConnThread extends Thread {

        Socket socket;
        ObjectInputStream in;

        ConnThread(Socket s) {
            try {
                send("<< Polaczono z klientem o porcie :" + s.getPort());
                socket = s;
                in = new ObjectInputStream(socket.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            MessageCloud data;
            ObjectOutputStream temp;
            try {
                while (true) {
                    data = (MessageCloud) in.readObject();
                    for (Object client : clients) {
                        temp = (ObjectOutputStream) client;
                        temp.writeObject(data);
                        temp.flush();
                    }
                }
            } catch (Exception e) { //Brak obsługi wyjątku - oznacza on rozłączenie z serwerem
                send("<< Klient o porcie " + socket.getPort() + " rozlaczył się");
            }
        }

    }
}
