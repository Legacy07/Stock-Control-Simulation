package stock;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class User extends JFrame implements ActionListener {

    JLabel nameLabel = new JLabel("Name * :  ");
    JLabel addressLabel = new JLabel("Address * : ");
    JLabel postCode = new JLabel("Post Code * : ");
    JLabel payment = new JLabel("Payment Method * :  ");
    JLabel userInfo = new JLabel("User Information");
    JLabel bankInfo = new JLabel("Billing Information");
    JLabel cardNumber = new JLabel("Account Number * :");
    JLabel sortCodeLabel = new JLabel("Sort Code * : ");
    JLabel securityLabel = new JLabel("Security Code * :");

    JTextField nameField = new JTextField(6);
    JTextField addressField = new JTextField(10);
    JTextField addressField2 = new JTextField(10);
    JTextField postCodeField = new JTextField(8);
    JTextField cardNumberField = new JTextField(16);
    JTextField sortCodeField = new JTextField(6);
    JTextField securityField = new JTextField(3);

    Font font = new Font("Courier", Font.BOLD, 14);
    ButtonGroup cards = new ButtonGroup();
    JRadioButton visaOpt = new JRadioButton("", true);
    JRadioButton masterCardOpt = new JRadioButton("", false);
    JButton validateB = new JButton("Validate");
    JButton cancelB = new JButton("Cancel");

    JLabel visaImg;
    JLabel masterCardImg;
    ImageIcon visa2;
    ImageIcon masterCard2;
    //document filter for uppercase class
    DocumentFilter filter = new UppercaseDocumentFilter();

    private static User obj = null;

    private User() {
        setBounds(100, 100, 350, 380);
        setTitle("Guest Checkout");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        getContentPane().setLayout(null);

        cards.add(visaOpt);
        cards.add(masterCardOpt);
        add(visaOpt);
        visaOpt.setBounds(25, 200, 22, 13);
        add(masterCardOpt);
        masterCardOpt.setBounds(140, 200, 22, 13);

        visaOpt.addActionListener(this);
        masterCardOpt.addActionListener(this);

        visa2 = new ImageIcon("visaS.png");
        visaImg = new JLabel(visa2);
        add(visaImg);
        visaImg.setBounds(10, 160, 180, 100);

        masterCard2 = new ImageIcon("masterCard.png");
        masterCardImg = new JLabel(masterCard2);
        add(masterCardImg);
        masterCardImg.setBounds(130, 160, 180, 100);

        validate();
        userInfo.setFont(font);
        userInfo.setBounds(25, 1, 200, 40);
        add(userInfo);
        bankInfo.setFont(font);
        bankInfo.setBounds(25, 150, 200, 40);
        add(bankInfo);

        nameLabel.setBounds(25, 30, 200, 40);
        add(nameLabel);
        nameField.setBounds(105, 40, 100, 20);
        add(nameField);
        ((AbstractDocument) nameField.getDocument()).setDocumentFilter(filter);

        addressLabel.setBounds(25, 60, 200, 40);
        add(addressLabel);
        addressField.setBounds(105, 75, 200, 20);
        add(addressField);
        ((AbstractDocument) addressField.getDocument()).setDocumentFilter(filter);
        addressField2.setBounds(105, 100, 200, 20);
        add(addressField2);
        ((AbstractDocument) addressField2.getDocument()).setDocumentFilter(filter);

        postCode.setBounds(25, 115, 200, 40);
        add(postCode);
        postCodeField.setBounds(105, 125, 100, 20);
        add(postCodeField);
        ((AbstractDocument) postCodeField.getDocument()).setDocumentFilter(filter);

        cardNumber.setBounds(25, 230, 200, 40);
        add(cardNumber);
        cardNumberField.setBounds(140, 243, 100, 20);
        add(cardNumberField);
        ((AbstractDocument) cardNumberField.getDocument()).setDocumentFilter(filter);

        sortCodeLabel.setBounds(25, 260, 200, 40);
        add(sortCodeLabel);
        sortCodeField.setBounds(105, 270, 50, 20);
        add(sortCodeField);
        ((AbstractDocument) sortCodeField.getDocument()).setDocumentFilter(filter);

        securityLabel.setBounds(170, 260, 200, 40);
        add(securityLabel);
        securityField.setBounds(270, 270, 30, 20);
        add(securityField);
        ((AbstractDocument) securityField.getDocument()).setDocumentFilter(filter);
        securityLabel.setToolTipText("Last 3 Digits of Security number, back of the card");

        add(validateB);
        validateB.addActionListener(this);
        validateB.setBounds(65, 305, 90, 30);
        validateB.setFont(new Font("Cambria", Font.BOLD, 14));
        validateB.setBackground(Color.darkGray);
        validateB.setForeground(Color.WHITE);

        add(cancelB);
        cancelB.addActionListener(this);
        cancelB.setBounds(200, 305, 90, 30);
        cancelB.setFont(new Font("Cambria", Font.BOLD, 14));
        cancelB.setBackground(Color.darkGray);
        cancelB.setForeground(Color.WHITE);

        setResizable(false);
        setVisible(true);

    }

    public static User getObj() {

        if (obj == null) {
            obj = new User();
        }
        return obj;

    }

    // uppercase class
    class UppercaseDocumentFilter extends DocumentFilter {

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text,
                AttributeSet attr) throws BadLocationException {

            fb.insertString(offset, text.replaceAll("\\D++", ""), attr);
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
                AttributeSet attrs) throws BadLocationException {

            fb.replace(offset, length, text.replaceAll("\\D++", ""), attrs);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cancelB) {
            int cancelConfirm = JOptionPane.showConfirmDialog(null, "Do you want to Go back?");

            if (cancelConfirm == JOptionPane.YES_OPTION) {
                this.dispose();
                PurchaseItem.getObj().setVisible(true);
            } else {
            }

        } else if (e.getSource() == validateB && nameField.getText().equals("") || addressField.getText().equals("")
                || postCodeField.getText().equals("") || cardNumberField.getText().equals("")
                || sortCodeField.getText().equals("") || securityField.getText().equals("") || visaOpt.isSelected() && masterCardOpt.isSelected()) {

            JOptionPane.showMessageDialog(null, "Please fill out all Fields marked by asterisks (*) !");
        }
        if (e.getSource() == validateB) {
// timer
            new Thread() {

                @Override
                public void run() {

                    if (e.getSource() == validateB && !nameField.getText().equals("") || !addressField.getText().equals("")
                            || !postCodeField.getText().equals("") || !cardNumberField.getText().equals("")
                            || !sortCodeField.getText().equals("") || !securityField.getText().equals("") || !visaOpt.isSelected() & !masterCardOpt.isSelected()) {
                        System.out.println("Connecting...");

                    }
                }
            }.start();

            try {

                Thread.sleep(3000);

            } catch (InterruptedException ie) {

                System.out.println(ie.getMessage());

            }

            JOptionPane.showMessageDialog(null, "Item Purchased", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        User user = new User();
    }

}
