package stock;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Pass extends JFrame implements ActionListener {

    public static String password = "1234";
    public static String user1 = "student";

    JTextField user = new JTextField(10);
    private static final String username2 = "user name";
    private static final String passwordT = "*********";

    JLabel label = new JLabel("Password: ");
    JButton login = new JButton("Login");
    //JButton cancel = new JButton("Cancel");
    JPasswordField password1 = new JPasswordField(10);

    JLabel passLb;
    ImageIcon passImg;
    JLabel userLb;
    ImageIcon userImg;
    private static Pass obj = null;

    private Pass() {

        setLayout(new BorderLayout());
        setVisible(true);
        setBounds(100, 100, 250, 130);

        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JPanel center = new JPanel();

//focusListener to have a text on TextField when widnow is opened but when clicked on it removes the text
        user.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                user.setText("");
            }

            public void focusLost(FocusEvent e) {

            }
        });
        password1.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                password1.setText("");
            }

            public void focusLost(FocusEvent e) {

            }
        });

        password1.addActionListener(this);
        login.addActionListener(this);
        login.setFont(new Font("Cambria", Font.BOLD, 14));
        login.setBackground(Color.darkGray);
        login.setForeground(Color.WHITE);

        //cancel.addActionListener(this);
        add("North", top);
        add("South", bottom);
        add("Center", center);

        userImg = new ImageIcon("user.png");
        userLb = new JLabel(userImg);
        passImg = new ImageIcon("pass.png");
        passLb = new JLabel(passImg);
        center.add(passLb);

        //  top.add(username);
        top.add(userLb);
        top.add(user);
        user.setText(username2);
        //  center.add(label);

        center.add(password1);
        password1.setText(passwordT);
        bottom.add(login);
        // bottom.add(cancel);
        setResizable(false);

    }

    public static Pass getObj() {

        if (obj == null) {
            obj = new Pass();
        }
        return obj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        char[] pass = password1.getPassword();
        String p = new String(pass);

        String user2 = user.getText();
        String u = user2;

        if (p.equals(password) && u.equals(user1) && e.getSource() == login) {
            Administration.getObj().setVisible(true);
            password1.setText("");
            setVisible(false);
            Master.getObj().setVisible(false);

        } else if (!p.equals(password) || !u.equals(user1)) {
            JOptionPane.showMessageDialog(null, "Try Again!");
            user.setText("");
            password1.setText("");
        }

    }

    public static void main(String[] args) {
        Pass password = new Pass();
    }

}
