// Create a gui wtih adding, updating and deleting stock with different buttons linked with it. 
package stock;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Administration extends JFrame implements ActionListener {

    JButton addB = new JButton("Add new Stock");
    JButton updateB = new JButton("Update Stock");
    JButton deleteB = new JButton("Delete Stock");
    JLabel adminLb = new JLabel("Add, Update and Delete Stock");

    ImageIcon backImg = new ImageIcon("back.png");
    JButton back = new JButton(backImg);

    private static Administration obj = null;

    private Administration() {

        super("Administration");
        setBounds(100, 100, 500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.gray);

        adminLb.setBounds(100, 30, 400, 40);
        getContentPane().add(adminLb);
        adminLb.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        adminLb.setForeground(Color.white);

        back.setBounds(10, 10, 27, 25);
        add(back);
        back.addActionListener(this);
        back.setToolTipText("Back");

        addB.setBounds(100, 100, 150, 80);
        getContentPane().add(addB);
        addB.setFont(new Font("Cambria", Font.BOLD, 16));
        addB.setBackground(Color.darkGray);
        addB.setForeground(Color.WHITE);
        addB.addActionListener(this);

        getContentPane().add(updateB);
        updateB.setBounds(270, 100, 150, 80);
        updateB.setFont(new Font("Cambria", Font.BOLD, 16));
        updateB.setBackground(Color.darkGray);
        updateB.setForeground(Color.WHITE);
        updateB.addActionListener(this);

        deleteB.setBounds(185, 190, 150, 80);
        getContentPane().add(deleteB);
        deleteB.setFont(new Font("Cambria", Font.BOLD, 16));
        deleteB.setBackground(Color.darkGray);
        deleteB.setForeground(Color.WHITE);
        deleteB.addActionListener(this);
    }

    public static Administration getObj() {

        if (obj == null) {
            obj = new Administration();
        }
        return obj;
    }

    public static void main(String[] args) {
        Administration.getObj().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.dispose();
            Master.getObj().setVisible(true);

        } else if (e.getSource() == updateB) {
            UpdateStock.getObj().setVisible(true);

        } else if (e.getSource() == addB) {
            NewStock.getObj().setVisible(true);

        } else if (e.getSource() == deleteB) {
            DeleteStock.getObj().setVisible(true);
        }

    }
}
