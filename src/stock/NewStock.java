package stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.derby.drda.NetworkServerControl;

public class NewStock extends JFrame implements ActionListener {

    StockData sData = new StockData();
    private static Connection connection;
    private static Statement stmt;
    JTextArea sOutput = new JTextArea();

    JTextField productKey = new JTextField();
    JTextField productName = new JTextField();
    JTextField quantity = new JTextField();
    JTextField price = new JTextField();

    JLabel productKeyLabel = new JLabel("Product Key* : ");
    JLabel productNameLabel = new JLabel("Product Name* : ");
    JLabel quantityLabel = new JLabel("Quantity* : ");
    JLabel priceLbl = new JLabel("Price* : ");
    ImageIcon backImg = new ImageIcon("back.png");
    JButton back = new JButton(backImg);

    JButton writeBtn = new JButton("Add to Database");

    StockData sD = new StockData();

    Database data = new Database();

    public static void main(String[] args) {
        new NewStock();
    }
    private static NewStock obj = null;

    public NewStock() {
        super("Database");
        setLayout(new BorderLayout());

        data = new Database();
        getContentPane().add(data);
        data.setBounds(280, 60, 380, 340);

        back.setBounds(10, 10, 27, 25);
        add(back);
        back.addActionListener(this);
        back.setToolTipText("Back");

        getContentPane().add(priceLbl);
        priceLbl.setBounds(30, 60, 100, 25);
        priceLbl.setFont(new Font("Cambria", Font.BOLD, 15));
        priceLbl.setForeground(Color.white);

        getContentPane().add(price);
        price.setBounds(155, 60, 100, 25);
        price.setFont(new Font("Cambria", Font.BOLD, 15));
        // price.setText("£");

        getContentPane().add(productKeyLabel);
        productKeyLabel.setBounds(30, 100, 150, 80);
        productKeyLabel.setFont(new Font("Cambria", Font.BOLD, 15));
        productKeyLabel.setForeground(Color.white);

        getContentPane().add(productKey);
        productKey.setBounds(155, 130, 100, 25);
        productKey.setFont(new Font("Cambria", Font.BOLD, 15));

        getContentPane().add(productNameLabel);
        productNameLabel.setBounds(30, 165, 150, 80);
        productNameLabel.setFont(new Font("Cambria", Font.BOLD, 15));
        productNameLabel.setForeground(Color.white);

        getContentPane().add(productName);
        productName.setBounds(155, 195, 100, 25);
        productName.setFont(new Font("Cambria", Font.BOLD, 15));

        getContentPane().add(quantityLabel);
        quantityLabel.setBounds(30, 230, 150, 80);
        quantityLabel.setFont(new Font("Cambria", Font.BOLD, 15));
        quantityLabel.setForeground(Color.white);

        getContentPane().add(quantity);
        quantity.setBounds(155, 260, 100, 25);
        quantity.setFont(new Font("Cambria", Font.BOLD, 15));

//        getContentPane().add(displayBtn);
        getContentPane().add(writeBtn);
        writeBtn.setBounds(30, 320, 140, 40);
        writeBtn.setFont(new Font("Cambria", Font.BOLD, 13));
        writeBtn.setBackground(Color.darkGray);
        writeBtn.setForeground(Color.WHITE);
        writeBtn.addActionListener(this);
//
//        getContentPane().add(displayBtn);
//        displayBtn.setBounds(30, 370, 140, 40);
//        displayBtn.setFont(new Font("Cambria", Font.BOLD, 13));
//        displayBtn.setBackground(Color.darkGray);
//        displayBtn.setForeground(Color.WHITE);
//        displayBtn.addActionListener(this);

//        getContentPane().add(sOutput);
//        sOutput.setBounds(280, 60, 370, 380);
//        sOutput.setEditable(false);
//        sOutput.setBackground(Color.white);
//        sOutput.setFont(new Font("Serif", Font.BOLD, 16));
        setBounds(300, 200, 700, 500);
        getContentPane().setBackground(Color.gray);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

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

    public static NewStock getObj() {

        if (obj == null) {
            obj = new NewStock();
        }
        return obj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String key = productKey.getText();

        String key2 = StockData.getKey(key);
        String name = StockData.getName(key);

        String sK = productKey.getText();
        String sN = productName.getText();
        String sP = price.getText();
        String sQ = quantity.getText();

        if (e.getSource() == back) {
            Administration.getObj().setVisible(true);
            this.dispose();

        } else if (sK.equals("") || sN.equals("") || sP.equals("") || sQ.equals("")) {
            showMessageDialog(this, "Please fill out all Fields marked by asterisks (*) !");

        } else if (key2 != null) {

            showMessageDialog(this, "Duplicate key " + sK);
            productKey.setText("");
        } else if (e.getSource() == writeBtn) {

            if (key2 == null) {
                int confirm = JOptionPane.showConfirmDialog(null, "You are about to add " + sN + ", Do you want to Continue?");

                if (confirm == JOptionPane.YES_OPTION) {

                    try {
                        String writeString = "INSERT INTO Stock(stockKey, stockName, stockPrice, stockQuantity) VALUES(?,?,?,?)";
                        PreparedStatement pst = connection.prepareStatement(writeString);

                        pst.setString(1, productKey.getText());
                        pst.setString(2, productName.getText());
                        pst.setString(3, price.getText());
                        pst.setString(4, quantity.getText());

                        pst.execute();

                        showMessageDialog(this, " Stock added! ");
                        productKey.setText(" ");
                        productName.setText(" ");
                        price.setText(" ");
                        quantity.setText(" ");

                        pst.close();
                    } catch (Exception ex) {

                        ex.printStackTrace();
                    }
                }

                if (confirm == JOptionPane.NO_OPTION) {
                    showMessageDialog(this, "Please Try Again !");
//                }
                }

            }

        }
        //if (e.getSource() == writeBtn) {
//            String sK = productKey.getText();
//            String sN = productName.getText();
//            String sP = price.getText();
//            String sQ = quantity.getText();
//
//            // if any field is blank, signal an error
//            if (sK.equals("") || sN.equals("") || sP.equals("") || sQ.equals("")) {
//                showMessageDialog(this, "Please fill out all Fields marked by asterisks (*) !");
//                return;
//            }
//            boolean ok = sD.write(sK, sN, sP, sQ);
//            productKey.setText("");
//            if (!ok) {
//                showMessageDialog(this, "Duplicate key " + sK);
//            } else {
//                productName.setText("");
//                price.setText("");
//                quantity.setText("");
//                showMessageDialog(this, "Stock Added !");
//
//            }
//        } 
    }
}
