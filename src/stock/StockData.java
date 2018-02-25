//package stock;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class StockData {
//
//    
//
//
//    private static class Item {
//
//        Item(String n, double p, int q) {
//            name = n;
//            price = p;
//            quantity = q;
//        }
//
//        // get methods
//        public String getName() {
//            return name;
//        }
//
//        public double getPrice() {
//            return price;
//        }
//
//        public int getQuantity() {
//            return quantity;
//        }
//
//        // instance variables 
//        private String name;
//        private double price;
//        private int quantity;
//    }
//
//    // with a Map you use put to insert a key, value pair 
//    // and get(key) to retrieve the value associated with a key
//    // You don't need to understand how this works!
//    private static Map<String, Item> stock = new HashMap<String, Item>();
//
//    static {
//        // if you want to have extra stock items, put them in here
//        // use the same style - keys should be Strings
//        stock.put("0", new Item("Bath towel", 5.50, 10));
//        stock.put("1", new Item("Plebney light", 20.00, 5));
//        stock.put("2", new Item("Gorilla suit", 30.00, 7));
//        stock.put("3", new Item("Whizz games console", 50.00, 8));
//        stock.put("4", new Item("Oven", 200.00, 4));
//        stock.put("5", new Item("Refrigerator", 400.00, 3));
//        stock.put("6", new Item("Bluv Smartphone", 180.00, 25));  
//        stock.put("7", new Item("Lix Tablet", 200.00, 4));
//        stock.put("8", new Item("Wooden Bookcase", 30.00, 10));
//        stock.put("9", new Item("Fitness Gloves", 10.00, 53)); 
//        stock.put("10", new Item("My Wallet", 20.00, 15));
//        stock.put("11", new Item("Critic 32GB RAM", 200.00, 3));
//        stock.put("12", new Item("Tech Wireless Keyboard", 40.00, 9));
//        stock.put("13", new Item("Ken Conditioner", 5.00, 58));
//        stock.put("14", new Item("34 Inches Ips Curved TV", 1000.00, 2));
//        stock.put("15", new Item("Bon Mop Kit", 23.00, 20));
//        stock.put("16", new Item("Hons Ear drops", 7.00, 120));
//        stock.put("17", new Item("IR Thermometer", 250.00, 3));
//        stock.put("18", new Item("Dishwasher", 100.00, 12));
//        stock.put("19", new Item("Network Adapter", 50.00, 4));
//                 
//    }
//
//    public static String getName(String key) {
//        Item item = stock.get(key);
//        if (item == null) {
//            return null; // null means no such item
//        } else {
//            return item.getName();
//        }
//    }
//
//    public static double getPrice(String key) {
//        Item item = stock.get(key);
//        if (item == null) {
//            return -1.0; // negative price means no such item
//        } else {
//            return item.getPrice();
//        }
//    }
//
//    public static int getQuantity(String key) {
//        Item item = stock.get(key);
//        if (item == null) {
//            return -1; // negative quantity means no such item
//        } else {
//            return item.getQuantity();
//        }
//    }
//
//    // update stock levels
//    // extra is +ve if adding stock
//    // extra is -ve if selling stock
//    public static void update(String key, int extra) {
//        Item item = stock.get(key);
//        if (item != null) {
//            item.quantity += extra;
//        }
//    }
//
//    public static void close() {
//        // Does nothing for this static version.
//        // Write a statement to close the database when you are using one
//    }
//
//}
package stock;

// Skeleton version of StockData.java that links to a database.
// NOTE: You should not have to make any changes to the other
// Java GUI classes for this to work, if you complete it correctly.
// Indeed these classes shouldn't even need to be recompiled
import java.sql.*; // DB handling package
import java.io.*;
import org.apache.derby.drda.NetworkServerControl;

public class StockData {

    private static Connection connection;
    private static Statement stmt;

    static {
        // standard code to open a connection and statement to access database
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

    // You could make methods getName, getPrice and getQuantity simpler by using an auxiliary
    // private String method getField(String key, int fieldNo) to return the appropriate field as a String
    public static String getName(String key) {
        try {
            // Need single quote marks ' around the key field in SQL. This is easy to get wrong!
            // For instance if key was "11" the SELECT statement would be:
            // SELECT * FROM Stock WHERE stockKey = '11'
            ResultSet res = stmt.executeQuery("SELECT * FROM Stock WHERE stockKey = '" + key + "'");
            if (res.next()) { // there is a result
                // the name field is the second one in the ResultSet
                // Note that with  ResultSet we count the fields starting from 1
                return res.getString(2);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

 public static String getKey(String key) {
        try {
            
            ResultSet res = stmt.executeQuery("SELECT * FROM Stock WHERE stockKey = '" + key + "'");
            if (res.next()) { 
                
                return res.getString(1);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }


    public static double getPrice(String key) {
        // Similar to getName. If no result, return -1.0
        try {
            // Need single quote marks ' around the key field in SQL. This is easy to get wrong!
            // For instance if key was "11" the SELECT statement would be:
            // SELECT * FROM Stock WHERE stockKey = '11'
            ResultSet res = stmt.executeQuery("SELECT * FROM Stock WHERE stockKey = '" + key + "'");
            if (res.next()) { // there is a result

                return res.getDouble(3);
            } else {
                return -1.0;
            }
        } catch (SQLException e) {
            System.out.println(e);

        }
        return 0;
    }

    public static int getQuantity(String key) {
        // Similar to getName. If no result, return -1

        try {
            // Need single quote marks ' around the key field in SQL. This is easy to get wrong!
            // For instance if key was "11" the SELECT statement would be:
            // SELECT * FROM Stock WHERE stockKey = '11'
            ResultSet res = stmt.executeQuery("SELECT * FROM Stock WHERE stockKey = '" + key + "'");
            if (res.next()) { // there is a result
                // the name field is the second one in the ResultSet
                // Note that with  ResultSet we count the fields starting from 1
                return res.getInt(4);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }
    }
//adding new stock line 
    //4 variables are assigned with the textFields in newStock class to add new data. 
//    public boolean write(String sK, String sN, String sP, String sQ) {
//        String writeString
//                = "INSERT INTO Stock(stockKey, stockName, stockPrice, stockQuantity) VALUES('"
//                + sK + "', '" + sN + "', '" + sP + "', ' " + sQ + " ')";
//        try {
//            stmt.execute(writeString);
//        } catch (SQLException sqle) {
//            return false; // duplicate key
//        }
//        return true; // inserted OK
//    }
//    
   

    // update stock levels
    // extra is +ve if adding stock
    // extra is -ve if selling stock
    public static void update(String key, int extra) {
        // SQL UPDATE statement required. For instance if extra is 5 and stockKey is "11" then updateStr is
        // UPDATE Stock SET stockQuantity = stockQuantity + 5 WHERE stockKey = '11'
        String updateStr = "UPDATE Stock SET stockQuantity = stockQuantity + " + extra + "  WHERE stockKey = '" + key + "'";
        System.out.println(updateStr);
        try {
            stmt.executeUpdate(updateStr);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

// close the database
    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            // this shouldn't happen
            System.out.println(e);
        }
    }
}
