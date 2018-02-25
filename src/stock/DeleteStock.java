package stock;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.derby.drda.NetworkServerControl;

public class DeleteStock extends JFrame implements ActionListener {

    JTextArea output = new JTextArea();

    JTextField productKey = new JTextField();

    JLabel productKeyLabel = new JLabel("Product Key : ");
    JLabel deleteStockLabel = new JLabel("Delete Stock line in database; ");

    ImageIcon backImg = new ImageIcon("back.png");
    JButton back = new JButton(backImg);

    JButton deleteBtn = new JButton("Delete Stock");

    public static void main(String[] args) {
        new DeleteStock();
    }
    private static DeleteStock obj = null;
    private static Connection connection;
    private static Statement stmt;

    static {
        // standard code to open a connection and statement to an Access database
        try {
            NetworkServerControl server = new NetworkServerControl();
            server.start(null);
            // Load JDBC driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Establish a connection
            String sourceURL = "jdbc:derby://localhost:1527/"
                    + new File("UserDB").getAbsolutePath() + ";";
            connection = DriverManager.getConnection(sourceURL, "use", "use");
            stmt = connection.createStatement();
        } // The following exceptions must be caught
        catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe);
        } catch (SQLException sqle) {
            System.out.println(sqle);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public DeleteStock() {
        super("Delete Stock");

        back.setBounds(10, 10, 27, 25);
        add(back);
        back.addActionListener(this);
        back.setToolTipText("Back");

        getContentPane().add(deleteStockLabel);
        deleteStockLabel.setFont(new Font("Cambria", Font.BOLD, 15));
        deleteStockLabel.setBounds(30, 150, 230, 80);
        deleteStockLabel.setForeground(Color.white);

        getContentPane().add(productKeyLabel);
        productKeyLabel.setBounds(30, 40, 100, 80);
        productKeyLabel.setFont(new Font("Cambria", Font.BOLD, 15));
        productKeyLabel.setForeground(Color.white);

        getContentPane().add(productKey);
        productKey.setBounds(155, 65, 100, 25);
        productKey.setFont(new Font("Cambria", Font.BOLD, 15));

        getContentPane().add(deleteBtn);

        getContentPane().add(deleteBtn);
        deleteBtn.setBounds(30, 230, 140, 40);
        deleteBtn.setFont(new Font("Cambria", Font.BOLD, 13));
        deleteBtn.setBackground(Color.darkGray);
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.addActionListener(this);

        getContentPane().add(output);
        output.setBounds(280, 65, 370, 205);
        output.setEditable(false);
        output.setBackground(Color.white);
        output.setFont(new Font("Serif", Font.ITALIC, 18));

        setBounds(300, 200, 700, 350);
        getContentPane().setBackground(Color.gray);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

    }

    public static DeleteStock getObj() {

        if (obj == null) {
            obj = new DeleteStock();
        }
        return obj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String key = productKey.getText();
        String name = StockData.getName(key);

        if (name == null) {
            output.setText("No such item in stock");
        }
        if (e.getSource() == back) {
            Administration.getObj().setVisible(true);
            this.dispose();

        } else if (productKey.equals(" ")) {
            JOptionPane.showMessageDialog(null, "Fill in the text field!");

        }
        if (e.getSource() == deleteBtn) {

            if (name != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "You are about to delete " + name + ", Do you want to Continue?");
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        String query = "DELETE FROM Stock WHERE STOCKKEY = '" + productKey.getText() + "'";
                        stmt.execute(query);

                        JOptionPane.showMessageDialog(null, "Successfully Deleted!");
                    } catch (SQLException ex) {
                        System.out.println(ex);

                    }

                }
                if (confirm == JOptionPane.NO_OPTION) {
                    output.setText("Please Try Again to delete data in Stock!");
                }
            }
        }
    }
}
