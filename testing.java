// STEP: Import required packages
import java.sql.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.File;

import java.io.*; 
import java.util.*; 
import java.util.Formatter;

public class testing {
    private Connection c = null;
    private String dbName;
    private boolean isConnected = false;

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
    private void createViews() {
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("Creating views..");

    
        try {
            System.out.printf("%-10s %-20s %20s %20s\n",
            "maker", "product", "minPrice", "maxPrice");

            String sqlPrint = "select * "+
                         "from PriceRange " +
                         "order by maker asc, type asc"; //its printing in ascending order of maker but not maxPrice...

            //PreparedStatement stmt = c.prepareStatement(sql);            
            ResultSet rsPrint = c.prepareStatement(sqlPrint).executeQuery();

            while (rsPrint.next()) {
                
                String maker = rsPrint.getString("maker");
                String product = rsPrint.getString("type");
                int minPrice = rsPrint.getInt("minPrice");
                int maxPrice = rsPrint.getInt("maxPrice");
                //formatter.format("%10s %-40s %10s %10s %10s\n", wId,wName,wCap,sId,nId);
                System.out.format("%-10s %-20s %20s %20s\n", maker, product, minPrice, maxPrice);
                //System.out.printf("%10d %10d\n", model, price);
            }


            //formatter.close();
            // STEP: Clean-up environment
            rsPrint.close();
            //stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("++++++++++++++++++++++++++++++++++");
    }


    //how do i make it so that it doesnt populate each time i compile
    private void populatePriceRange() {
        /*
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("Populate PriceRange");


        System.out.println("++++++++++++++++++++++++++++++++++");
        */


        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("Populate table");
      

        try {
            //SQL QUERY FOR DATA
            int n = 2;
            int nn = n*n;
            
            Vector<String> productMaker = new Vector<String>(1);
            Vector<String> productType = new Vector<String>(1);
            Vector<Integer> minPrice = new Vector<Integer>(1);
            Vector<Integer> maxPrice = new Vector<Integer>(1); 



 
            
           // for(int i = 0; i < 200; i++){ //only change i to set amount
            String sql = "select p1.maker as makers, p1.type as product_type, min(pc1.price) as min_price, max(pc1.price) as max_price " +
                "from Product p1, PC pc1 " +
                "where p1.model = pc1.model " + 
                       "group by p1.maker " + 
                       "UNION " + 
                "select distinct p1.maker as makers, p1.type as product_type, min(pr.price) as min_price, max(pr.price) as max_price " +
                       "from Product p1, Printer pr " +
                       "where p1.model = pr.model " + 
                       "group by p1.maker " +
                       "UNION " +
                "select distinct p1.maker as makers, p1.type as product_type, min(lp.price) as min_price, max(lp.price) as max_price " +
                "from Product p1, Laptop lp " +
                "where p1.model = lp.model " + 
                "group by p1.maker;";
               
            // STEP: Execute both queries
            //PreparedStatement stmt = c.prepareStatement(sql);
            //LEFT OFF HERE
           // stmt.setString(1, _maker);
            ResultSet rs = c.prepareStatement(sql).executeQuery();
            

            // STEP: Extract data from result set
             
 
            
            while (rs.next()) {

                productMaker.add(rs.getString("makers"));
                productType.add(rs.getString("product_type"));
                minPrice.add(rs.getInt("min_price"));
                maxPrice.add(rs.getInt("max_price"));            
                
            }

            // Boolean tableSame = true;
            // int count = 0;
            // for (int i = 0; i < productMaker.size();i++){
               
            //     for(int j = 0; j < copProductMaker.size(); j++){
            //         if(copProductMaker.get(i) != productMaker.get(j) &&
            //            copProductType.get(i) != productType.get(j) &&
            //            copMinPrice.get(i) != minPrice.get(j) &&
            //            copMaxPrice.get(i) != maxPrice.get(j)){

            //                 count++;
            //         }
            //     }
            // }



            // STEP: Clean-up environment
            rs.close();
 
            //stmt.close();

        //}
        //STEP: INSERT DATA FROM VECTORS INTO WAREHOUSE TABLE
        String sql2 = "INSERT INTO PriceRange VALUES(?, ?, ?, ?)";
        PreparedStatement stmt2 = c.prepareStatement(sql2);
        for(int k = 0; k < productMaker.size(); k++){


            stmt2.setString(1, productMaker.get(k));
            stmt2.setString(2, productType.get(k));
            stmt2.setInt(3, minPrice.get(k));
            stmt2.setInt(4, maxPrice.get(k));
            stmt2.addBatch();
        }

        // STEP: Execute batch
        stmt2.executeBatch();


         // STEP: Commit transaction
         c.commit();

        //STEP: CLOSE AND PRINT SUCCESS IF MADE IT TO END
         stmt2.close();
         System.out.println("success");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private void printPriceRange() {
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("Print PriceRange");

    
        try {
            System.out.printf("%-10s %-20s %20s %20s\n",
            "maker", "product", "minPrice", "maxPrice");

            String sqlPrint = "select * "+
                         "from PriceRange " +
                         "order by maker asc, type asc"; //its printing in ascending order of maker but not maxPrice...

            //PreparedStatement stmt = c.prepareStatement(sql);            
            ResultSet rsPrint = c.prepareStatement(sqlPrint).executeQuery();

            while (rsPrint.next()) {
                
                String maker = rsPrint.getString("maker");
                String product = rsPrint.getString("type");
                int minPrice = rsPrint.getInt("minPrice");
                int maxPrice = rsPrint.getInt("maxPrice");
                //formatter.format("%10s %-40s %10s %10s %10s\n", wId,wName,wCap,sId,nId);
                System.out.format("%-10s %-20s %20s %20s\n", maker, product, minPrice, maxPrice);
                //System.out.printf("%10d %10d\n", model, price);
            }


            //formatter.close();
            // STEP: Clean-up environment
            rsPrint.close();
            //stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("++++++++++++++++++++++++++++++++++");
    }

    private void insertPC(String _maker, int _model, double _speed,
        //I pc F 1101 3.2 8192 1024 948
        int _ram, int _hd, int _price) {
        System.out.println("++++++++++++++++++++++++++++++++++");

            try {

                //Print what we will be doing in this function
                System.out.printf("Insert PC (%s, %d, %f, %d, %d, %d)\n",
                _maker, _model, _speed, _ram, _hd, _price);
                    

                //check if model already exists
                Vector<String> pMak = new Vector<String>(0);
                Vector<Integer> pMod = new Vector<Integer>(0);
                Vector<String> pTyp = new Vector<String>(0);

                
                //PreparedStatement stmtCheckProds = c.prepareStatement(sqlSelectAllProducts);
               

                
                //Insert into Product Table
                String sqlInsProd = "INSERT INTO Product VALUES(?, ?, ?)";
                PreparedStatement stmtInsProd = c.prepareStatement(sqlInsProd);
               

                    stmtInsProd.setString(1, _maker);
                    stmtInsProd.setInt(2, _model);
                    stmtInsProd.setString(3, "pc");
                    //stmtInsProd.addBatch();
                
                stmtInsProd.executeUpdate();
                
                
                //Insert into PC Table
                String sqlInsPC = "INSERT INTO PC VALUES(?, ?, ?, ?, ?)";
                PreparedStatement stmtInsPC = c.prepareStatement(sqlInsPC);
                
                    stmtInsPC.setInt(1, _model);
                    stmtInsPC.setDouble(2, _speed);
                    stmtInsPC.setInt(3, _ram);
                    stmtInsPC.setInt(4, _hd);
                    stmtInsPC.setInt(5, _price);
                    stmtInsPC.addBatch();
                
                stmtInsPC.executeUpdate();
                

                

                c.commit();
                // STEP: Clean-up environment


                stmtInsProd.close();
                stmtInsPC.close();



                         // STEP: Commit transaction
                
                

         //STEP: CLOSE AND PRINT SUCCESS IF MADE IT TO END
          
          System.out.println("success");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }


        System.out.println("++++++++++++++++++++++++++++++++++");
    }


    private void updatePrinter(int _model, int _price) {
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.printf("Update Printer (%d, %d)\n", _model, _price);

        System.out.println("++++++++++++++++++++++++++++++++++");
    }

    private void deleteLaptop(int _model) {
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.printf("Delete Laptop (%d)\n", _model);

        System.out.println("++++++++++++++++++++++++++++++++++");
    }


    public static void main(String args[]) {
        testing sj = new testing();
        
        sj.openConnection("gr_app.sqlite");

        
        

        try {
            File fn = new File("input.in");
            FileReader reader = new FileReader(fn);
            BufferedReader in = new BufferedReader(reader);

            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);

                String[] tok = line.split("[ ]");
                if (tok[0].equals(new String("I"))) {
                    sj.insertPC(tok[2], Integer.parseInt(tok[3]),
                        Double.parseDouble(tok[4]), Integer.parseInt(tok[5]),
                        Integer.parseInt(tok[6]), Integer.parseInt(tok[7]));
                }
                else if (tok[0].equals(new String("U"))) {
                    sj.updatePrinter(Integer.parseInt(tok[2]),
                        Integer.parseInt(tok[3]));
                }
                else if (tok[0].equals(new String("D"))) {
                    sj.deleteLaptop(Integer.parseInt(tok[2]));
                }

                sj.printPriceRange();
            }

            in.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        sj.closeConnection();
    }
}
