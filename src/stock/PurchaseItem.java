package stock;
//add search for the user to input first letter of thre item and finding the correct item. make the frame longer and less wide, on the left search bar and the list of items show up and user can choose.
//on the right hand side related buttons and the output with calculations.
// add basket in stage 6

//make the frame bigger and wider. 
//add to basket button needs to be clicked before purchasing, solve it. 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.*;
import javax.swing.text.Position;

public class PurchaseItem extends JFrame implements ActionListener, ChangeListener {

//updated
    JTextField purchaseKey = new JTextField(3);
    JTextField searchField = new JTextField(5);
    JTextField productKeyField = new JTextField(3);
    JTextField priceField = new JTextField(5);
    JTextField quantityField = new JTextField(5);

    TextArea purchaseInformation = new TextArea(10, 35);
    TextArea searchInformation = new TextArea(20, 25);
    JButton purchaseB = new JButton("Checkout");
    //Updated 

    JButton add = new JButton("Add to Basket");
    JButton emptyB = new JButton("Empty Basket");

    JLabel myBasket = new JLabel("My Basket");
    JLabel label = new JLabel("Enter a key* : ");
    JLabel label2 = new JLabel("Quantity* : ");
    JLabel productKey = new JLabel("Product Key :");
    JLabel quantityLabel = new JLabel("Quantity :");
    JLabel priceLabel = new JLabel("Price : ");
    JButton search = new JButton("Search");
    // adding a new spinner called itemsNo with an initial value of 0, minimum 1 and maximum 100.
    JSpinner itemsNo = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
    //updated   
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");
    ImageIcon backImg = new ImageIcon("back.png");
    JButton back = new JButton(backImg);

    private static PurchaseItem obj = null;
    List_Database lD = new List_Database();
    JPanel jListPanel;

    private PurchaseItem() {
        setLayout(new BorderLayout());
        setSize(900, 670);
        setTitle("Purchase Item");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel top = new JPanel();
        JPanel middle = new JPanel();
        JPanel bottom = new JPanel();
        JPanel east = new JPanel();
        JPanel west = new JPanel();

        //getContentPane().setLayout(null);
        back.setBounds(840, 10, 27, 25);
        add(back);
        back.addActionListener(this);
        back.setToolTipText("Back");

        //purchaseKey text included in new JLabel to input in the text box.
        add(searchField);
        searchField.setBounds(200, 45, 130, 25);
        searchField.setFont(new Font("Cambria", Font.BOLD, 15));

        add(label);
        label.setBounds(200, 450, 140, 40);
        label.setFont(new Font("Cambria", Font.BOLD, 18));
        label.setForeground(Color.WHITE);

        add(purchaseKey);
        purchaseKey.setBounds(338, 460, 100, 25);
        purchaseKey.setFont(new Font("Cambria", Font.BOLD, 15));

        add(label2);
        label2.setBounds(200, 500, 140, 40);
        label2.setFont(new Font("Cambria", Font.BOLD, 18));
        label2.setForeground(Color.WHITE);

        add(itemsNo);
        itemsNo.addChangeListener(this);
        itemsNo.setBounds(338, 510, 100, 25);

        add(add);
        add.addActionListener(this);
        add.setFont(new Font("Cambria", Font.BOLD, 14));
        add.setBackground(Color.darkGray);
        add.setForeground(Color.WHITE);
        add.setBounds(200, 570, 130, 40);

        add(emptyB);
        emptyB.addActionListener(this);
        emptyB.setFont(new Font("Cambria", Font.BOLD, 14));
        emptyB.setBackground(Color.darkGray);
        emptyB.setForeground(Color.WHITE);
        emptyB.setBounds(350, 570, 130, 40);

        add(search);
        search.setBounds(350, 40, 100, 40);
        search.addActionListener(this);
        search.setFont(new Font("Cambria", Font.BOLD, 14));
        search.setBackground(Color.darkGray);
        search.setForeground(Color.WHITE);

        add(purchaseB);
        purchaseB.setBounds(675, 570, 130, 40);
        purchaseB.addActionListener(this);
        purchaseB.setFont(new Font("Cambria", Font.BOLD, 18));
        purchaseB.setBackground(Color.darkGray);
        purchaseB.setForeground(Color.WHITE);

        add(productKey);
        productKey.setBounds(200, 100, 120, 100);
        productKey.setFont(new Font("Cambria", Font.BOLD, 16));
        productKey.setForeground(Color.WHITE);

        add(productKeyField);
        productKeyField.setBounds(350, 138, 100, 25);
        productKeyField.setFont(new Font("Cambria", Font.BOLD, 16));
        productKeyField.setEditable(false);

        add(quantityLabel);
        quantityLabel.setBounds(200, 200, 120, 100);
        quantityLabel.setFont(new Font("Cambria", Font.BOLD, 16));
        quantityLabel.setForeground(Color.WHITE);

        add(quantityField);
        quantityField.setBounds(350, 238, 100, 25);
        quantityField.setFont(new Font("Cambria", Font.BOLD, 16));
        quantityField.setEditable(false);

        add(priceLabel);
        priceLabel.setBounds(200, 300, 120, 100);
        priceLabel.setFont(new Font("Cambria", Font.BOLD, 16));
        priceLabel.setForeground(Color.WHITE);

        add(priceField);
        priceField.setBounds(350, 338, 100, 25);
        priceField.setFont(new Font("Cambria", Font.BOLD, 16));
        priceField.setEditable(false);

        add(lD);
        west.add(lD);

        add(purchaseInformation);
        purchaseInformation.setEditable(false);
        purchaseInformation.setBounds(550, 100, 250, 380);
        purchaseInformation.setBackground(Color.white);
        purchaseInformation.setFont(new Font("Serif", Font.ITALIC, 16));

        getContentPane().add(myBasket);
        myBasket.isOptimizedDrawingEnabled();
        myBasket.setBounds(550, 55, 100, 25);
        myBasket.setFont(new Font("Cambria", Font.BOLD, 18));
        myBasket.setForeground(Color.WHITE);

        add("North", top);
        add("South", bottom);
        add("Center", middle);
        add("East", east);
        add("West", west);

        setResizable(false);
        setVisible(true);
        top.setBackground(Color.gray);
        bottom.setBackground(Color.gray);
        middle.setBackground(Color.gray);
        east.setBackground(Color.gray);
        west.setBackground(Color.gray);

    }

    public static PurchaseItem getObj() {

        if (obj == null) {
            obj = new PurchaseItem();
        }
        return obj;
    }

    @Override

    public void actionPerformed(ActionEvent e) {
//updated

        String keys = purchaseKey.getText();

        String name = StockData.getName(keys);

        int numberOfItems = Integer.parseInt("" + itemsNo.getValue());
        double cost = numberOfItems * StockData.getPrice(keys);

        if (e.getSource() == emptyB) {
            purchaseInformation.setText(null);
            StockData.update(keys, +numberOfItems);

        }

        if (e.getSource() == back) {
            purchaseInformation.setText("");
            this.dispose();
            Master.getObj().setVisible(true);

        } else if (name == null && e.getSource() == emptyB) {
            JOptionPane.showMessageDialog(this, "No Products in basket", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (purchaseInformation == null && e.getSource() == purchaseB) {
            JOptionPane.showMessageDialog(null, "Basket is empty!", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (name == null && e.getSource() == add) {
            JOptionPane.showMessageDialog(this, "Invalid Item Key", "Information", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == add) {
            if (numberOfItems <= StockData.getQuantity(keys)) {

                purchaseInformation.setText("\n ");
                purchaseInformation.setText(numberOfItems + " x " + name + "\nSubtotal Cost: " + pounds.format(cost));

                StockData.update(keys, -numberOfItems);

                purchaseInformation.append("\nAdded to basket");
                // make a loop for multiple items to add into basket 
//                purchaseInformation.append ("\nTotal cost is = " + total);

            } else {
                purchaseInformation.setText("Too many items requested : " + StockData.getQuantity(keys) + " " + name + " left");
            }
        } /*else if (e.getSource() == basket) {
           
                double total = numberOfItems * StockData.getPrice(keys);
       
                                 purchaseInformation.append(total + "");
         */ else if (e.getSource() == purchaseB) {

            int confirm = JOptionPane.showConfirmDialog(null, "You Selected " + name + "\nThe total cost is " + pounds.format(cost) + "\nDo you want to continue?");

            if (confirm == JOptionPane.YES_OPTION) {
                User.getObj().setVisible(true);
                this.dispose();
            } else if (confirm == JOptionPane.NO_OPTION) {
                purchaseInformation.setText("");

                JOptionPane.showMessageDialog(null, "Please Try Again!");
                //double purchase = numberOfItems * StockData.getPrice(keys);

            }

        }
        //searching through jlist
        if (e.getSource() == search) {

            int result = lD.list.getNextMatch(searchField.getText(), 0, Position.Bias.Forward);

            lD.list.setSelectedIndex(result);

        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // getting the value for spinner in order to make it work 

        itemsNo.getValue();

    }

    public static void main(String[] args) {
        PurchaseItem purchaseItem = new PurchaseItem();
    }

}
