package stock;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class KeyPad extends JPanel implements ActionListener {

    private JButton buttonR = new JButton("Reset");
    private JButton button0 = new JButton("0");
    private JButton buttonE = new JButton("Enter");
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");

    public KeyPad() {

        JFrame f = new JFrame();
        f.setTitle("Keypad");

        setLayout(new GridLayout(4, 3, 4, 4));
        JButton[] digit = new JButton[10];
        for (int i = 1; i < 10; i++) {

            digit[i] = new JButton("" + i);
            add(digit[i]);
            digit[i].addActionListener(this);

        }

        add(buttonR);
        add(button0);
        add(buttonE);
        button0.addActionListener(this);
        buttonR.addActionListener(this);
        buttonE.addActionListener(this);

        buttonR.setBackground(Color.DARK_GRAY);
        buttonE.setBackground(Color.darkGray);
        buttonR.setForeground(Color.white);
        buttonE.setForeground(Color.white);

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String s = CheckStock.getObj().stockNo.getText();
        String key = CheckStock.getObj().stockNo.getText();
  
        String name = StockData.getName(key);

        switch (e.getActionCommand()) {
            case "1":
                CheckStock.getObj().stockNo.setText(s + "1");
                break;
            case "2":
                CheckStock.getObj().stockNo.setText(s + "2");
                break;
            case "3":
                CheckStock.getObj().stockNo.setText(s + "3");
                break;
            case "4":
                CheckStock.getObj().stockNo.setText(s + "4");
                break;
            case "5":
                CheckStock.getObj().stockNo.setText(s + "5");
                break;
            case "6":
                CheckStock.getObj().stockNo.setText(s + "6");
                break;
            case "7":
                CheckStock.getObj().stockNo.setText(s + "7");
                break;
            case "8":
                CheckStock.getObj().stockNo.setText(s + "8");
                break;
            case "9":
                CheckStock.getObj().stockNo.setText(s + "9");
                break;

        }
        if (e.getSource() == button0) {

            CheckStock.getObj().stockNo.setText(s + "0");

        }
       
        if (e.getSource() == buttonR) {
            CheckStock.getObj().stockNo.setText("");
            CheckStock.getObj().information.setText("");

        } if (e.getSource() == buttonE && name == null) {
          CheckStock.getObj().information.setText("No such item in stock");
        
        }else if (e.getSource() == buttonE) {
            CheckStock.getObj().information.setText(name);

            CheckStock.getObj().information.append("\nPrice: " + pounds.format(StockData.getPrice(key)));
            CheckStock.getObj().information.append("\nNumber in stock: " + StockData.getQuantity(key));

        }
        
    }

    public static void main(String[] args) {
        new CheckStock();
    }
}
