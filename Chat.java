import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;

public class Chat{
    JFrame frame = new JFrame();
    JPanel panel = new JPanel(new BorderLayout(5, 10));
    JPanel typing = new JPanel(new FlowLayout());
    JTextArea chat = new JTextArea();
    JTextField msg = new JTextField();
    String userName = "";
    JButton send = new JButton("Send");
    PrintWriter output;

    Chat(PrintWriter o){
        output = o;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 600);

        chat.setPreferredSize(new Dimension(500, 500));
        chat.setFocusable(false);

        msg.setPreferredSize(new Dimension(400, 30));

        send.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                send();
            }
        });

        typing.add(msg);
        typing.add(send);
        panel.add(chat, BorderLayout.CENTER);
        panel.add(typing, BorderLayout.SOUTH);
        
        //add empty panels for esthetics
        panel.add(new JPanel(), BorderLayout.NORTH);
        panel.add(new JPanel(), BorderLayout.EAST);
        panel.add(new JPanel(), BorderLayout.WEST);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void send(){
        output.println(userName + ": " + msg.getText());
    }

    public void listen(String str){
        chat.append(str + "\n");
    }

    public void setUserName(String name){
        userName = name;
    }
}