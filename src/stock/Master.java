package stock;
// change the appereance of the system after finished with the functionality . 
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Master extends JFrame implements ActionListener {

    JButton check = new JButton("Check Stock");
    JButton purchase = new JButton("Purchase Item");
    JButton stock = new JButton("Administration");
    JButton quit = new JButton("Exit");

    JLabel masterLb = new JLabel("Manager");

    private static Master obj = null;

    public static void main(String[] args) {
        Master master = new Master();
    }

    public Master() {
        super("Master");
        setBounds(100, 100, 500, 400);
        setLocationRelativeTo(null);

        // close application only by clicking the quit button
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);

        masterLb.setBounds(200, 10, 100, 40);
        getContentPane().add(masterLb);
        masterLb.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        masterLb.setForeground(Color.white);
        
        check.setBounds(100, 100, 150, 80);
        getContentPane().add(check);
        check.setFont(new Font("Cambria", Font.BOLD, 16));
        check.setBackground(Color.darkGray);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);

        getContentPane().add(purchase);
        purchase.setBounds(270, 100, 150, 80);
        purchase.setFont(new Font("Cambria", Font.BOLD, 16));
        purchase.setBackground(Color.darkGray);
        purchase.setForeground(Color.WHITE);
        purchase.addActionListener(this);

        stock.setBounds(100, 190, 150, 80);
        getContentPane().add(stock);
        stock.setFont(new Font("Cambria", Font.BOLD, 16));
        stock.setBackground(Color.darkGray);
        stock.setForeground(Color.WHITE);
        stock.addActionListener(this);
        

        quit.setBounds(270, 190, 150, 80);
        getContentPane().add(quit);

        quit.setFont(new Font("Cambria", Font.BOLD, 16));
        quit.setBackground(Color.darkGray);
        quit.setForeground(Color.WHITE);
        quit.addActionListener(this);

        getContentPane().setBackground(Color.gray);
        setResizable(false);
        setVisible(true);
    }

    public static Master getObj() {

        if (obj == null) {
            obj = new Master();
        }
        return obj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == check) {
            CheckStock.getObj().setVisible(true);
            this.dispose();
        } else if (e.getSource() == quit) {
            StockData.close();
            System.exit(0);
        } else if (e.getSource() == purchase) {
            PurchaseItem.getObj().setVisible(true);
            this.dispose();
        } else if (e.getSource() == stock) {
            Pass.getObj().setVisible(true);

        }
    }
}
