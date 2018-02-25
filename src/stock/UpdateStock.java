package stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/// Update doesnt change effect on when checked in check stock and in database. Do it! 
public class UpdateStock extends JFrame implements ActionListener, ChangeListener {

    JTextField stockKey = new JTextField(3);
    //JTextField stockNew = new JTextField(4);
    JSpinner stockNew = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));

    TextArea stockInformation = new TextArea();
    JButton updateStock = new JButton("Update Stock");
    JLabel label = new JLabel("Enter a key :");
    JLabel label2 = new JLabel("Quantity :");
    ImageIcon backImg = new ImageIcon("back.png");
    JButton back = new JButton(backImg);
    JButton showStock = new JButton("Show Stock");

    private static UpdateStock obj = null;

    StockData sd = new StockData();
    List_Database lD = new List_Database();

    UpdateStock() {

        setLayout(new BorderLayout());
        setBounds(300, 200, 700, 500);
        setTitle("Update Stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        back.setBounds(10, 10, 27, 25);
        add(back);
        back.addActionListener(this);
        back.setToolTipText("Back");

        getContentPane().add(label);
        label.setBounds(30, 30, 150, 80);
        label.setFont(new Font("Cambria", Font.BOLD, 15));
        label.setForeground(Color.white);

        getContentPane().add(stockKey);
        stockKey.setBounds(130, 60, 100, 25);
        stockKey.setFont(new Font("Cambria", Font.BOLD, 15));

        getContentPane().add(label2);
        label2.setBounds(260, 60, 100, 25);
        label2.setFont(new Font("Cambria", Font.BOLD, 15));
        label2.setForeground(Color.white);

        getContentPane().add(stockNew);
        stockNew.setBounds(350, 60, 70, 25);
        stockNew.addChangeListener(this);

        getContentPane().add(updateStock);
        updateStock.setBounds(500, 55, 130, 40);
        updateStock.setFont(new Font("Cambria", Font.BOLD, 14));
        updateStock.setBackground(Color.darkGray);
        updateStock.setForeground(Color.WHITE);
        updateStock.addActionListener(this);

        getContentPane().add(showStock);
        showStock.setBounds(500, 110, 130, 40);
        showStock.setFont(new Font("Cambria", Font.BOLD, 14));
        showStock.setBackground(Color.darkGray);
        showStock.setForeground(Color.WHITE);
        showStock.addActionListener(this);

        getContentPane().add(stockInformation);
        stockInformation.setBounds(30, 110, 420, 350);
        stockInformation.setEditable(false);
        stockInformation.setBackground(Color.white);
        stockInformation.setFont(new Font("Serif", Font.ITALIC, 16));

        getContentPane().setBackground(Color.gray);

        setResizable(false);
        setVisible(true);
    }

    public static UpdateStock getObj() {

        if (obj == null) {
            obj = new UpdateStock();
        }
        return obj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Checks the stockNo to ensure if it's a valid input then it gets the saved data to output. 
        if (e.getSource() == back) {
            stockInformation.setText("");
            this.dispose();
            Administration.getObj().setVisible(true);

        }
        if (e.getSource() == showStock) {

            Database_JFrame data = new Database_JFrame();
            data.setVisible(true);
            UpdateStock.getObj().setVisible(true);

        }
        String key = stockKey.getText();

        String name = StockData.getName(key);

        if (name == null) {
            stockInformation.setText("No such item in stock");
        } else if (key == null && updateStock.isSelected()) {
            stockInformation.setText("Enter a valid item key and quantity");
        } else if (e.getSource() == updateStock) {

            int stock = StockData.getQuantity(key);

            stockInformation.setText(name);
            int extra = Integer.parseInt("" + stockNew.getValue());
            stockInformation.append("\nThe current stock is : " + stock);

            int confirm = JOptionPane.showConfirmDialog(null, "You are about to add " + extra + " item(s), " + "Do you want to Continue?");
            switch (confirm) {
                case JOptionPane.YES_OPTION:
                    StockData.update(key, extra);
                    stockInformation.append("\n ");
                    int total = stock + extra;
                    stockInformation.append("\nTotal stock is now = " + total);
                    break;
                case JOptionPane.NO_OPTION:
                    stockInformation.setText(null);
                    break;
                case JOptionPane.CANCEL_OPTION:
                    break;
                default:
                    break;
            }

        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // getting the value for spinner in order to make it work 

        stockNew.getValue();

    }

    public static void main(String[] args) {
        UpdateStock updatestock = new UpdateStock();
    }
}
