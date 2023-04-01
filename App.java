import java.io.*;
import java.net.*;
import java.util.*;

public class App {
    private static InetAddress host;
    private static final int PORT = 1234;
    public static void main(String args[]){

        try{
            host = InetAddress.getLocalHost();
            Socket link = new Socket(host, PORT);

            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            Chat chat = new Chat(output);
            Settings setting = new Settings(chat);

            String msg = input.nextLine();
            while (!msg.equals("***c***")){
                chat.listen(msg);
                msg = input.nextLine();
            }

            link.close();
        }
        catch(UnknownHostException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
