// STEP: Import required packages
import java.sql.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
public class testingG {
    private Connection c = null;
    private String dbName;
    private boolean isConnected = false;
    Scanner myScanner = new Scanner(System.in); 
    private void openConnection(String _dbName) {
        dbName = _dbName;

        if (false == isConnected) {
            System.out.println("++++++++++++++++++++++++++++++++++");
            System.out.println("Open database: " + _dbName);

            try {
                String connStr = new String("jdbc:sqlite:");
                connStr = connStr + _dbName;

                // STEP: Register JDBC driver
                Class.forName("org.sqlite.JDBC");

                // STEP: Open a connection
                c = DriverManager.getConnection(connStr);

                // STEP: Diable auto transactions
                c.setAutoCommit(false);

                isConnected = true;
                System.out.println("success");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

            System.out.println("++++++++++++++++++++++++++++++++++");
        }
    }
    private int createBookID() {
        System.out.println("Creating new bookID...");
        //user chooses lang code from list by entering a number
        int newID = 1;
    
        try {

            String sqlBID = "select max(b_bookID)+1 as newID "+
                            "from books; ";

        
            ResultSet rsBID = c.prepareStatement(sqlBID).executeQuery();
            
            while (rsBID.next()) {
                newID = rsBID.getInt("newID");
            }
            // STEP: Clean-up environment
            rsBID.close();
            //stmt.close();
            System.out.println("Successfully created book id: " + newID);
            return newID;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
    private int authorCount() {
        System.out.println("Checking to see if author already exists...");
        //user chooses lang code from list by entering a number
        int authCount = 1;
        
        try {

            String sqlBID = "select count(a_name) " +
                            "from authors " +
                            "where a_name = 'habibatu karim mboka'; ";

        
            ResultSet rsBID = c.prepareStatement(sqlBID).executeQuery();
            
            while (rsBID.next()) {
                authCount = rsBID.getInt("count(a_name)");
            }
            System.out.println(authCount);
            // STEP: Clean-up environment
            rsBID.close();
            //stmt.close();
           // System.out.println("Successfully created book id: " + newID);
            return authCount;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
    private void printLangCode() {
        
        //user chooses lang code from list by entering a number
        String lang = "";
        
        try {

            String sqlBID = "select b_langcode " +
                            "from books; ";

        
            ResultSet rsBID = c.prepareStatement(sqlBID).executeQuery();
            
            while (rsBID.next()) {
                lang = rsBID.getString("b_langcode");
                System.out.println(lang);
            }
            
            // STEP: Clean-up environment
            rsBID.close();
            //stmt.close();
           // System.out.println("Successfully created book id: " + newID);
          
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
    
    }
    private int insertNewBook() {
        //System.out.println("Creating new bookID...");
        int newID = createBookID();
        System.out.print("Enter title: ");
        String title = myScanner.nextLine();
        //String title = "Mboka: The Way of Life in a Congo Village (A Congo Memoir)";
        System.out.print("Enter author(s)--seperate multiple authors with a forward slash: ");
        String authors = myScanner.nextLine();
        //String authors = "Lona B. Kenney";
        System.out.print("Enter book publisher: ");
        String publisher = myScanner.nextLine();
        //String publisher = "Crown Publishers";
        System.out.print("Enter isbn: ");
        String isbn = myScanner.nextLine();
        //String isbn = "051750037X";     
        System.out.print("Enter page count: ");
        int pagecnt = myScanner.nextInt();           
       // int pagecnt = 264;
       System.out.print("Enter language code--enter \"see list\" to see available codes: ");
       String lang = myScanner.nextLine();
       //String lang = "eng";
        if(lang == "see list" || lang == "See list"){
            printLangCode();
            System.out.print("Enter language code: ");
            lang = myScanner.nextLine();
        }



        try {
            //insert to books
            String sql = "INSERT INTO books (b_bookID, b_title, b_langcode, b_numpages, b_isbn) "+
                         "VALUES(?,?,?,?,?); ";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, newID);
            stmt.setString(2, title);
            stmt.setString(3, lang);
            stmt.setInt(4, pagecnt);
            stmt.setString(5, isbn);
            stmt.addBatch();
            stmt.executeBatch();
            //insert to authored
            String sql2 = "INSERT INTO authored (ad_name, ad_bookID) "+
                         "VALUES(?,?); ";
            PreparedStatement stmt2 = c.prepareStatement(sql2);
            stmt2.setString(1,authors);
            stmt2.setInt(2, newID);
            stmt2.addBatch();
            stmt2.executeBatch();
            //insert to authors
            //ensure that author does not exist
            PreparedStatement stmt3;
            if(authorCount() == 0){
                String sql3 = "INSERT INTO authors (a_name) "+
                            "VALUES(?); ";
                stmt3 = c.prepareStatement(sql3);
                stmt3.setString(1,authors);
                stmt3.addBatch();
                stmt3.executeBatch();
                c.commit();
                stmt3.close();          
            } else{
                System.out.println("Author already exists!");
            }
            //insert into publisher
            String sql4 = "INSERT INTO publisher (p_name, p_bookID, p_authname) "+
                         "VALUES(?,?,?); ";
            PreparedStatement stmt4 = c.prepareStatement(sql4);
            stmt4.setString(1,publisher);
            stmt4.setInt(2, newID);
            stmt4.setString(3, authors);
            stmt4.addBatch();
            stmt4.executeBatch();
            //comit changes
            c.commit();
            // STEP: Clean-up environment
            stmt.close();
            stmt2.close();
            stmt4.close();
            
            System.out.println("Successfully created book: " + title);
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
    private void closeConnection() {
        if (true == isConnected) {
            System.out.println("++++++++++++++++++++++++++++++++++");
            System.out.println("Close database: " + dbName);

            try {
                // STEP: Close connection
                c.close();

                isConnected = false;
                dbName = "";
                System.out.println("success");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

            System.out.println("++++++++++++++++++++++++++++++++++");
        }
    }

    private void create_View1() {
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("Create V1");
        try {
            Statement stmt = c.createStatement();

            // STEP: Execute update statement

            String sql = "CREATE view V1 (" + "c_custkey, c_name, c_address, c_phone, c_acctbal, c_mktsegment, c_comment, c_nation, c_region) as "
                    + "select c_custkey, c_name, c_address, c_phone, c_acctbal, c_mktsegment, c_comment, n_name, r_name "
                    + "from nation, region, customer " 
                    + "where c_nationkey = n_nationkey AND "
                    + "n_regionkey = r_regionkey;";
            stmt.execute(sql);

            // STEP: Commit transaction
            c.commit();

            stmt.close();
            System.out.println("success");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            } catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }

        System.out.println("++++++++++++++++++++++++++++++++++");
    }
   
    public static void main(String args[]) {
        testingG sj = new testingG();
        
        sj.openConnection("data/grapp.sqlite");
        sj.insertNewBook();
        //sj.createBookID();
        //sj.closeConnection();
    }
}
