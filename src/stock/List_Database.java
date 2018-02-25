/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class List_Database extends JPanel {

    private static Connection connection;
    private static Statement stmt;

    JList list;

    final void FillList() {
        try {
            String sourceURL = "jdbc:derby://localhost:1527/"
                    + new File("UserDB").getAbsolutePath() + ";";
            connection = DriverManager.getConnection(sourceURL, "use", "use");
            stmt = connection.createStatement();
            String query = "SELECT * FROM STOCK ";
            ResultSet res = stmt.executeQuery(query);

            DefaultListModel dlm = new DefaultListModel();
            list = new JList(dlm);
            while (res.next()) {
                dlm.addElement(res.getString(2));

            }
            list.setModel(dlm);
//            JScrollPane list1scr = new JScrollPane(list);
//            list.setVisibleRowCount(8);
//            add(list1scr);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error");
        }
        add(list);
        //list.setSize(100,300);

    }

    public List_Database() {
        FillList();

        ListSelectionListener listSelectionListener = new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    String sourceURL = "jdbc:derby://localhost:1527/"
                            + new File("UserDB").getAbsolutePath() + ";";
                    connection = DriverManager.getConnection(sourceURL, "use", "use");
                    stmt = connection.createStatement();
                    String query = "SELECT * FROM STOCK WHERE STOCKNAME = '" + list.getSelectedValue() + "'";
                    ResultSet res = stmt.executeQuery(query);

                    while (res.next()) {
                        PurchaseItem.getObj().productKeyField.setText(res.getString(1));
                        PurchaseItem.getObj().priceField.setText("£" + res.getString(3));
                        PurchaseItem.getObj().quantityField.setText(res.getString(4));

                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "error");
                }
            }

        };

        list.addListSelectionListener(listSelectionListener);

    }

    public static void main(String[] args) {
        new List_Database();
    }
}
