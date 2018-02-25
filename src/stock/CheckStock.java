package stock; // Redo annotations/comments as things have implemented 

// importing the classes of the awt (Abstract Windows Toolkit) enabling GUI allowing such windowing and user interface.
// importing the classes of the swing enabling GUI components such as buttons
// BorderLayout is a default layout associating JFrame and consists Center, East, North, South and West
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
// ActionListener allows to add a method and perfom it which in this case buttons are declared below and put in action when pressed.  
import java.awt.event.ActionListener;
// adds a simple frame which allows modification to users specification. 
import javax.swing.JFrame;
// Allows the include and edit line of text 
import javax.swing.JTextField;
// Allows the insert text area of output 
import java.awt.TextArea;
// Allows events to occur suchlike buttons when being pressed.
import java.awt.event.ActionEvent;
// Allows to insert a decimal format in our way in order to output numbers(e.g. total cost).
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
//Allows to add a button which is functioned with ActionListeners and ActionPerformed.
import javax.swing.JButton;
// Allows to add labels which are purely text or image or both however in this case only text written below.
import javax.swing.JLabel;
//The content pane splitted in borders of Center, East, North, South and West which contains all the variables and objects within.
import javax.swing.JPanel;

//As JFrame is a subclass of CheckStock, we are allowing to use its features and 
//The content pane is extended and added the ActionListener which things can be implemented within the content pane. 
//The program will be able to listen the events created and perform the actions by ActionPerformed method in order to enable use of features. 
public class CheckStock extends JFrame implements ActionListener {

    // Objects declared regarding to where texts should be. 
    // stockNo has a single line and width area of 7 rows which is a suitable length to input item number to check stock based on the frame's size.
    JTextField stockNo = new JTextField(7);
    //The area of the text when an item is checked which is the output area. 3 columns and 50 rows within the frame of that box.
    TextArea information = new TextArea(3, 45);
    // adding a new button 'check' but called "Check Stock"
    JButton check = new JButton("Check Stock");
    // Outputs total cost in this decimal format with currency sign creating a variable 'pounds'.
    DecimalFormat pounds = new DecimalFormat("£#,##0.00");

    ImageIcon backImg = new ImageIcon("back.png");
    JButton back = new JButton(backImg);
    JLabel stockLabel = new JLabel("Enter Stock Number:");

    private static CheckStock obj = null;

    private KeyPad kp;

    // make search where text area shows all the database when opened then when searched only outputs the searched item. 
    // The constructor including statements of properties for the customised layout and behaviour within the program/application. 
    public CheckStock() { //show database help http://www.c-sharpcorner.com/UploadFile/fd0172/display-records-from-database-using-jtable-in-java/
        //https://docs.oracle.com/javase/tutorial/jdbc/basics/jdbcswing.html
        //http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#simple // this link has sorting and filtering within check it out!!!!!
        // TRY this defo https://www.youtube.com/watch?v=E5jX9nnN-tQ

       
        // the size and dimensions of the frame. 100 by 100 for the window to be located on the screen. 450 frames by 150 frames. 
        setBounds(300, 200, 750, 500);
        // Title of the application window
        setTitle("Check Stock");
        // Close operation to exit the application window. 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setLayout(null);

        setLocationRelativeTo(null);
        //a new object is created 'top' by JPanel constructor.

        //keypad class is added
        kp = new KeyPad();
        getContentPane().add(kp);
        kp.setBounds(450, 110, 260, 180);
        kp.setBackground(Color.gray);

        //back button to get to previous window
        back.setBounds(10, 10, 27, 25);
        add(back);
        back.addActionListener(this);
        back.setToolTipText("Back");

       
        getContentPane().add(stockLabel);
        stockLabel.setBounds(30, 30, 150, 80);
        stockLabel.setFont(new Font("Cambria", Font.BOLD, 15));
        stockLabel.setForeground(Color.white);
        stockLabel.setToolTipText("Only Numbers starting from '1' !");
        //Stock Number text included in object 'top' to input in the text box. 

        getContentPane().add(stockNo);
        stockNo.setBounds(180, 60, 100, 25);
        stockNo.setFont(new Font("Cambria", Font.BOLD, 15));
        //adding check button to the top 

        // ActionListener registers declared buttons (check) which performs and responds.
        check.setBounds(310, 50, 100, 40);
        getContentPane().add(check);
        check.addActionListener(this);
        check.setFont(new Font("Cambria", Font.BOLD, 12));
        check.setBackground(Color.darkGray);
        check.setForeground(Color.WHITE);

        //adding those variables within 'top' at the north of the border. 
        //A new method  'middle' added through a 'JPanel' constructor
        JPanel middle = new JPanel();
        //object 'middle' added which is whatever is linked with 'information' will be shown within the application.
        getContentPane().add(information);
        information.setBounds(30, 110, 400, 350);
        information.setEditable(false);
        information.setBackground(Color.white);
        information.setFont(new Font("Serif", Font.ITALIC, 20));
        // Including the variable linked to 'middle' are in the center of the program showing 'information' variable. 
        add("Center", middle);

        getContentPane().setBackground(Color.gray);
        //Not allowing to resize the screen. 
        setResizable(false);
        //The frame(program) is visible to the user
        setVisible(true);
    }

//opens the class only for once     
    public static CheckStock getObj() {

        if (obj == null) {
            obj = new CheckStock();
        }
        return obj;
    }

// override is needed for any duplication clash, to each variable have its own uses in different methods
    @Override
    // actionPerformed method with a ActionEvent e parameter and no return value includes the output statements and button recognisition when pressed
    // Also if and else statements included to output either of them based on an input
    public void actionPerformed(ActionEvent e) {
        //Allowing to check the stock by inputting the valid stock number by using method JTextField
        String key = stockNo.getText();
        //Checks the stockNo to ensure if it's a valid input then it gets the saved data to output. 
        String name = StockData.getName(key);
        // if statement when an invalid input is added then it shows a such string of text which is linked to information variable in order to work.
        if (e.getSource() == back) {
            information.setText("");
            this.dispose();
            Master.getObj().setVisible(true);
        }
           if (e.getSource() == check && name == null) {
                CheckStock.getObj().information.setText("No such item in stock");
            }
         // else statement if a valid input is added then following variables occur.
        else //Name of the input 
        {
            information.setText(name);
        }

//            //Total cost in a decimal format in 'middle' variable which is in the center of the frame.
        information.append("\nPrice: " + pounds.format(StockData.getPrice(key)));
//            //The quantity of stock is shown in 'middle' .
        information.append("\nNumber in stock: " + StockData.getQuantity(key));

    }

    public static void main(String[] args) {

        CheckStock.getObj().setVisible(true);
    }
}
