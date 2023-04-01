import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private final static int PORT = 1234;
    private static ServerSocket servSock;
    private ServerThread[] clients = new ServerThread[10];
    private static int num = 1;
    
    public void runServer(){
        try{
            servSock = new ServerSocket(PORT);

            while(true){
                Socket link = servSock.accept();
                System.out.println("new connection...\n");
                ServerThread t = new ServerThread("thread" + num, link, this);
                t.start();
                clients[num-1] = t;
                ++num;
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void sendToClients(String message){
        for (int i = 0; i < num-1; ++i){
            clients[i].sendMessage(message);
        }
    }

    public static void main(String args[]){
        Server s = new Server();
        s.runServer();
    }
}

class ServerThread extends Thread{
    private String msg;
    private Scanner input;
    private PrintWriter output;
    private Socket link;
    private Server serv;

    public ServerThread() {}
    public ServerThread(String s, Socket link, Server serv) {
        super(s);
        this.link = link;
        this.serv = serv;
    }

    public void run(){
        try{
            input = new Scanner(link.getInputStream());           
            output = new PrintWriter(link.getOutputStream(), true);

            msg = input.nextLine(); 
            while (!msg.equals("***c***")){
                System.out.println(msg);
                serv.sendToClients(msg);
                msg = input.nextLine();
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void sendMessage(String message){
        output.println(message);
    }
}