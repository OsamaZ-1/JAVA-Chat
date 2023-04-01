import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Settings extends JFrame{
    private Chat chat;
    private JPanel pan1;
    private JLabel userLabel;
    private JTextField userName;
    private JButton applyUserName;

    public Settings(Chat chat){
        this.chat = chat;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(200, 200);

        pan1 = new JPanel(new FlowLayout());

        userLabel = new JLabel("User Name: ");

        userName = new JTextField();
        userName.setPreferredSize(new Dimension(150, 30));

        applyUserName = new JButton("Apply");
        applyUserName.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                chat.setUserName(userName.getText());
                dispose();
            }
        });

        pan1.add(userLabel);
        pan1.add(userName);
        pan1.add(applyUserName);

        add(pan1);
        setVisible(true);
    }
}
