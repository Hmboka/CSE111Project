// STEP: Import required packages
import java.sql.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class test2 {
    private Connection c = null;
    private String dbName;
    private boolean isConnected = false;
    
    Scanner myScanner = new Scanner(System.in); 
    private void openConnection(String _dbName) {
        dbName = _dbName;

        if (false == isConnected) {
            //System.out.println("++++++++++++++++++++++++++++++++++");
            //System.out.println("Open database: " + _dbName);

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
                //System.out.println("success");
            } catch (Exception e) {
                System.out.println("\nUnfortunately, we were unable to connect to your Personal Library Tracker.");
                System.out.println("See error message below: ");
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.out.println();
                System.exit(0);
            }

            System.out.println("++++++++++++++++++++++++++++++++++");
        }
    }
    private int userIntro() {
        System.out.println("Welcome to Your Personal Library Tracker");
        System.out.println("Sponsored by GoodReads");
		System.out.println("Enter 1 if you like to open GoodReads Database. Enter 2 if you like to open your Personal Library. Enter 0 to quit. ");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("1. GoodReads Library Database");
		System.out.println("2. Personal Library Database");
        System.out.println("0. Quit");
        int input = myScanner.nextInt();
        try {

            if(input < 0 || input > 2){
                System.out.println("You have entered an invalid input, please try again...");
                System.out.println();
                userIntro();
            }
            if(input == 1){
                //System.out.print("GoodReads Library Database ");
                goodReads();
            }
            else if (input == 2){
                //System.out.print("Personal Library Database ");
                personal();
            }
            
            else{
                System.out.println("Exiting Library...");
                System.out.println("Goodbye!");
                System.out.println("End of Library Program");
                end();
            }
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }

    private int goodReads() {
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("This is your GoodReads Database.");
		System.out.println("What would you like to do?");
		System.out.println("1. Search books from general GoodReads Database?");		//done
		System.out.println("2. Would you like to add, delete, or edit books from the general GoodReads Database?");
		System.out.println("3. Would you like to add a book from this general GoodReads DatabaseFavorite Database?");
		System.out.println("4. Would you like to search books written by on auhtors?");		//done
		System.out.println("5. Would you like to search books with a certain rating?");
		System.out.println("6. What book has a rating of at least (your input of rating vaule), written in (insert language), and has at least (insert number of pages) pages?");
		System.out.println("0. Back");
        int selectInput = myScanner.nextInt();
        try {

            if(selectInput < 0 || selectInput > 6){
                System.out.println("Invalid Entry. Please enter an option listed above.");
                goodReads();
            }
            else if(selectInput == 1){
                System.out.println("Please enter the name of the book:");
                myScanner.nextLine();
                String inputBookName1 = myScanner.nextLine();
                goodReads1(inputBookName1);
    
            }
            else if(selectInput == 2){
                System.out.println("What would you like to do?");
                System.out.println("1. Add book");
                System.out.println("2. Delete Book");
                    int selectInput2 = myScanner.nextInt();
                    if(selectInput2 < 0 || selectInput2 > 2){
                        System.out.println("Invalid Entry. Please enter an option listed above.");
                        goodReads();
                    }
                    else if(selectInput2 == 1){
                            //System.out.println("testing connection jhere");
                            //System.out.println(selectInput2);						
                            insertNewBook();
                            //System.out.println("testing connection jhere");
                        }
                        else {
                            //System.out.println(selectInput2);	
                            // System.out.println("Enter Name of Book you want to Delete:");
                            // myScanner.nextLine();
                            // String inputBookName2_2 = myScanner.nextLine();
                            // goodreads2_2(inputBookName2_2);
                        }
			}
			
			if(selectInput == 4){
				System.out.println("Please enter the name of the author?");
				myScanner.nextLine();
				String inputBookName4 = myScanner.nextLine();
				System.out.println("++++++++++++++++++++++++++++++++++");
				goodreads4(inputBookName4);
	
			}
			if(selectInput == 0){
				userIntro();
			}
	
			else if(selectInput == 6){
				//userOption6();
			}
			goodReads();
    
           
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
    private int personal() {
        System.out.println("++++++++++++++++++++++++++++++++++");
		System.out.println("This is your Personal Library Tracker");
		System.out.println("What would you like to do?");
		System.out.println("1. Search books from general GoodReads Database?");
		System.out.println("2. Would you like to add, delete, or edit books from the general GoodReads Database?");
		System.out.println("3. Search books from your Favorite Database?");
		System.out.println("4. Would you like to add, delete, or edit your Favorite Database?");
		System.out.println("5. Would you like to search books based on Authors from either Database?");
		System.out.println("6. Would you like to search books based on Rating from either Database?");
		System.out.println("0. Back");
        int selectInput = myScanner.nextInt();
        try {

            if(selectInput < 0 || selectInput > 6){
                System.out.println("Invalid Entry. Please enter an option listed above.");
                personal();
            }
            else if(selectInput == 1){
                System.out.println("Please enter the name of the book?");
                myScanner.nextLine();
                String inputBookName = myScanner.nextLine();
    
            }
    
            else if(selectInput == 6){
                
            }
            else{
                userIntro();
            }
            personal();
                  
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
    private int goodReads1(String inputBookName1) {
        //searches book based on title entered
        // b_bookID, b_title, a_name, r_avgbookrating, b_langcode, b_numpages, b_isbn 
        try {
             String sql  = "select b_bookID, b_title, a_name, r_avgbookrating, b_langcode, b_numpages, b_isbn " + 
                            "from books, authors, authored, rating " + 
                            "where b_bookID = ad_bookID AND ad_name = a_name AND r_bookID = b_bookID AND b_title LIKE "+ "'%" + inputBookName1 + "%'";
             ResultSet rs = c.prepareStatement(sql).executeQuery();
             while (rs.next()) {
				String b_bookID = rs.getString("b_bookID");
                String b_title = rs.getString("b_title");
                String a_name = rs.getString("a_name");
                double r_avgbookrating = rs.getDouble("r_avgbookrating");
                String b_langcode = rs.getString("b_langcode");
                int b_numpages = rs.getInt("b_numpages");
                String b_isbn = rs.getString("b_isbn");
                System.out.println(b_bookID + "\t" + b_title + "\t" + a_name + r_avgbookrating + "\t" + "\t" + b_langcode + "\t" + b_numpages + "\t" + b_isbn + "\n");
            }
            rs.close();
  
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }


    
    private int goodreads4(String inputBookName4){
    
        try{
            
            String sql = "SELECT b_title FROM books WHERE b_bookID IN (SELECT ad_bookID FROM authored " + "where ad_name LIKE " + "'%" + inputBookName4 + "%')";
            ResultSet rs = c.prepareStatement(sql).executeQuery();
            while(rs.next()){
            
                String b_title = rs.getString("b_title");
                System.out.println(b_title);
            }
            rs.close();
    
        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }





    private int createBookID() {
        System.out.println("++++++++++++++++++++++++++++++++++");
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
    private int authorCount(String inputAuthName) {
        System.out.println("Checking to see if author already exists...");
        //user chooses lang code from list by entering a number
        int authCount = 1;
        
        try {

            String sqlBID = "select count(a_name) " +
                            "from authors " +
                            "where a_name = " + "'" + inputAuthName + "'";

        
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
        System.out.print("Enter book title: ");
        myScanner.nextLine();
        String title = myScanner.nextLine();
        //System.out.println("Title = " + title);
            //String title = "Mboka: The Way of Life in a Congo Village (A Congo Memoir)";
    //System.out.print("Enter author(s)--Seperate multiple authors with a forward slash: ");
    //String authors = myScanner.nextLine();
            //String authors = "Lona B. Kenney";
        //System.out.println("Title = " + title);
        //System.out.println("author = " + authors);
    //System.out.print("Enter book publisher: ");
    //String publisher = myScanner.nextLine();
            //String publisher = "Crown Publishers";
        System.out.print("Enter isbn: ");
        String isbn = myScanner.nextLine();
            //String isbn = "051750037X";     
        System.out.print("Enter page count: ");
        int pagecnt = myScanner.nextInt();           
            // int pagecnt = 264;
       System.out.print("Enter language code--enter \"see list\" to see available codes: ");
       myScanner.nextLine();
       String lang = myScanner.nextLine();
            //String lang = "eng";
        // if(lang == "see list" || lang == "See list"){
        //     printLangCode();
        //     System.out.print("Enter language code: ");
        //     lang = myScanner.nextLine();
        // }



        try {
            String sql = "INSERT INTO books VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, newID);
            stmt.setString(2, title);
            stmt.setString(3, lang);
            stmt.setInt(4, pagecnt);
            stmt.setString(5, isbn);
                //stmtInsProd.addBatch();
            
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            //insert to books
            // String sql = "INSERT INTO books "+
            //              "VALUES( "+ newID + ", '" + title + "','" + lang + "','" + pagecnt + "','" + isbn + "');";
            // PreparedStatement stmt = c.prepareStatement(sql);
            // // stmt.setInt(1, newID);
            // // stmt.setString(2, title);
            // // stmt.setString(3, lang);
            // // stmt.setInt(4, pagecnt);
            // // stmt.setString(5, isbn);
            // //stmt.addBatch();
            // stmt.executeUpdate();
            // c.commit();
            // //insert to authored
            // String sql2 = "INSERT INTO authored (ad_name, ad_bookID) "+
            //              "VALUES(?,?); ";
            // PreparedStatement stmt2 = c.prepareStatement(sql2);
            // stmt2.setString(1,authors);
            // stmt2.setInt(2, newID);
            // stmt2.addBatch();
            // stmt2.executeBatch();
            // c.commit();
            // //insert to authors
            // //ensure that author does not exist
            // PreparedStatement stmt3;
            // if(authorCount(authors) == 0){
            //     String sql3 = "INSERT INTO authors (a_name) "+
            //                 "VALUES(?); ";
            //     stmt3 = c.prepareStatement(sql3);
            //     stmt3.setString(1,authors);
            //     stmt3.addBatch();
            //     stmt3.executeBatch();
            //     c.commit();
            //     stmt3.close();          
            // } else{
            //     System.out.println("Author already exists!");
            // }
            // //insert into publisher
            // String sql4 = "INSERT INTO publisher (p_name, p_bookID, p_authname) "+
            //              "VALUES(?,?,?); ";
            // PreparedStatement stmt4 = c.prepareStatement(sql4);
            // stmt4.setString(1,publisher);
            // stmt4.setInt(2, newID);
            // stmt4.setString(3, authors);
            // stmt4.addBatch();
            // stmt4.executeBatch();
            // //comit changes
            // c.commit();
            // // STEP: Clean-up environment
            // stmt.close();
            // stmt2.close();
            // stmt4.close();
            
            System.out.println("Successfully created book: " + title);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
    private void end(){
		myScanner.close();
		System.exit(0);
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

   
    public static void main(String args[]) {
        test2 sj = new test2();
        
        sj.openConnection("data/grapp.sqlite");
        sj.userIntro();
        //sj.createBookID();
        //sj.closeConnection();
    }
}








// // STEP: Import required packages
// import java.sql.*;
// import java.io.FileWriter;
// import java.io.PrintWriter;
// import java.util.Scanner;
// import java.io.*;

// public class test2 {
// 	public static Connection connect;
// 	public static Scanner scan = new Scanner(System.in);
// 	public static Scanner myScanner = new Scanner(System.in);

// 	public static void main(String[] args) {

//         userintro();
		
// 	}

// 	public static void userintro(){
//         System.out.println("Welcome to You Personal Library Tracker");
//         System.out.println("Sponsored by GoodReads");
// 		System.out.println("Enter 1 if you like to open GoodReads Database. Enter 2 if you like to open your Personal Library. Enter 0 to quit. ");
// 		System.out.println("1. GoodReads Library Database");
// 		System.out.println("2. Personal Library Database");
// 		System.out.println("0. Quit");
// 		int input = scan.nextInt();
// 		if(input < 0 || input > 2){
// 			System.out.println("You have entered an invalid input, please try again...");
// 			System.out.println();
// 			userintro();
// 		}
// 		if(input == 1){
// 			System.out.println("GoodReads Library Database ");
// 			goodreads();
// 		}
// 		if(input == 2){
// 			System.out.println("Personal Library Database ");
// 			personal();
// 		}
		
// 		else{
// 			System.out.println("Exiting Library...");
// 			System.out.println("Goodbye!");
// 			System.out.println("End of Library Program");
// 			end();
// 		}
		
// 	}
	
// 	public static void end(){
// 		scan.close();
// 		System.exit(0);
// 	}

	
// 	public static void goodreads(){
// 		System.out.println("This is your Goodreads Library Tracker");
// 		System.out.println("What would you like to do?");
// 		System.out.println("1. Search books from general GoodReads Database?");  // done
// 		System.out.println("2. Would you like to add or delete from the general GoodReads Database?");
// 		System.out.println("3. Would you like to add a book from this general GoodReads Database to your Favorite Database?");
// 		System.out.println("4. Would you like to search books written by certain auhtors?"); //done
// 		System.out.println("5. Would you like to search books with a certain rating?");
// 		System.out.println("6. What book has a rating of at least (your input of rating vaule), written in (insert language), and has at least (insert number of pages) pages?");
// 		System.out.println("0. Back");

// 		int selectInput = scan.nextInt();
// 		if(selectInput < 0 || selectInput > 6){
// 			System.out.println("Invalid Entry. Please enter an option listed above.");
// 			goodreads();
// 		}
// 		if(selectInput == 1){
// 			System.out.println("Please enter the name of the book?");
// 			scan.nextLine();
// 			String inputBookName = scan.nextLine();
// 			goodreads1(inputBookName);

// 		}
// 		if(selectInput == 2){
// 			System.out.println("What would you like to do?");
// 			System.out.println("1. Add book");
// 			System.out.println("2. Delete Book");
// 				int selectInput2 = scan.nextInt();
// 				if(selectInput2 < 0 || selectInput2 > 2){
// 				System.out.println("Invalid Entry. Please enter an option listed above.");
// 				goodreads();
// 				}

// 				///// can insert the query from the one habi did
// 					if(selectInput2 == 1){
// 						System.out.println("testing connection jhere");
// 						System.out.println(selectInput2);						
// 						insertNewBook();
// 						System.out.println("testing connection jhere");
// 					}
// 					else {
// 						System.out.println(selectInput2);	
// 						System.out.println("Enter Name of Book you want to Delete:");
// 						scan.nextLine();
// 						String inputBookName2_2 = scan.nextLine();
// 						goodreads2_2(inputBookName2_2);
// 					}

// 		}
// 		if(selectInput == 3){
// 			System.out.println("Please enter the name of the Book?");
// 			scan.nextLine();
// 			String inputBookName3 = scan.nextLine();
// 			goodreads4(inputBookName3);

// 		}

// 		if(selectInput == 4){
// 			System.out.println("Please enter the name of the author?");
// 			scan.nextLine();
// 			String inputBookName4 = scan.nextLine();
// 			goodreads4(inputBookName4);

// 		}
// 		if(selectInput == 0){
// 			userintro();
// 		}

// 		else if(selectInput == 6){
// 			//userOption6();
// 		}
// 		goodreads();
		
// 	}
	

// 	public static void personal(){
// 		System.out.println("This is your Personal Library Tracker");
// 		System.out.println("What would you like to do?");
// 		System.out.println("1. Search books from general GoodReads Database?");
// 		System.out.println("2. Would you like to add, delete, or edit books from the general GoodReads Database?");
// 		System.out.println("3. Search books from your Favorite Database?");
// 		System.out.println("4. Would you like to add, delete, or edit your Favorite Database?");
// 		System.out.println("5. Would you like to search books based on Authors from either Database?");
// 		System.out.println("6. Would you like to search books based on Rating from either Database?");
// 		System.out.println("0. Back");
// 		int selectInput = scan.nextInt();
// 		if(selectInput < 0 || selectInput > 6){
// 			System.out.println("Invalid Entry. Please enter an option listed above.");
// 			personal();
// 		}
// 		if(selectInput == 1){
// 			System.out.println("Please enter the name of the book?");
// 			scan.nextLine();
// 			String inputBookName = scan.nextLine();

// 		}

// 		else if(selectInput == 6){
			
// 		}
// 		else{
// 			userintro();
// 		}
// 		personal();
		
// 	}



// 	public static void goodreads1(String inputBookName){

// 		Connection connect;
// 		Statement stmt;
// 		String query;

// 		try{
			
// 			Class.forName("org.sqlite.JDBC");
// 			connect = DriverManager.getConnection("jdbc:sqlite:data.sqlite");
// 			stmt = connect.createStatement();
// 			query = "SELECT * " + "FROM books " + "where b_title LIKE " + "'%" + inputBookName + "%'";
// 			ResultSet rs = stmt.executeQuery(query);
// 			while(rs.next()){
// 				String b_bookID = rs.getString("b_bookID");
// 				String b_title = rs.getString("b_title");
//                 String b_langcode = rs.getString("b_langcode");
//                 String b_numpages = rs.getString("b_numpages");
//                 String b_isbn = rs.getString("b_isbn");
// 				System.out.println(b_bookID + "\t" + b_title + "\t" + b_langcode + "\t" + b_numpages + "\t" + b_isbn);
// 			}
// 			rs.close();
// 			stmt.close();
// 			connect.close();
// 			System.out.println();
// 		}
// 		catch(Exception e){
// 			//System.out.print("There was a problem connecting to the database...");
// 			System.err.println(e.getMessage());
// 			System.out.println("Program Ending...");
// 			System.exit(0);
// 		}
// 	}

// 	//// need to fix this one

// 	public static void goodreads2_2(String inputBookName2_2){

// 		Connection connect;
// 		Statement stmt;
// 		String query;

// 		try{
			
// 			Class.forName("org.sqlite.JDBC");
// 			connect = DriverManager.getConnection("jdbc:sqlite:data.sqlite");
// 			stmt = connect.createStatement();
// 			query = "DELETE FROM books " + "where b_title = '" + inputBookName2_2 + "';";
// 			// ResultSet rs = stmt.executeQuery(query);
// 			// while(rs.next()){
				
// 			// 	String b_title = rs.getString("b_title");
// 			// 	System.out.println( b_title + "Is deleted from the Database");
// 			// }
// 			// rs.close();
// 			stmt.close();

// 			System.out.println( inputBookName2_2 + "Is deleted from the Database");

// 			connect.close();
// 			System.out.println();
// 		}
// 		catch(Exception e){
// 			//System.out.print("There was a problem connecting to the database...");
// 			System.err.println(e.getMessage());
// 			System.out.println("Program Ending...");
// 			System.exit(0);
// 		}
// 	}



// 	/// works 

// public static void goodreads4(String inputBookName4){

// 	Connection connect;
// 	Statement stmt;
// 	String query;

// 	try{
		
// 		Class.forName("org.sqlite.JDBC");
// 		connect = DriverManager.getConnection("jdbc:sqlite:data.sqlite");
// 		stmt = connect.createStatement();
// 		query = "SELECT b_title FROM books WHERE b_bookID IN (SELECT ad_bookID FROM authored " + "where ad_name LIKE " + "'%" + inputBookName4 + "%')";
// 		ResultSet rs = stmt.executeQuery(query);
// 		while(rs.next()){
		
// 			String b_title = rs.getString("b_title");
// 			System.out.println(b_title);
// 		}
// 		rs.close();
// 		stmt.close();
// 		connect.close();
// 		System.out.println();
// 	}
// 	catch(Exception e){
// 		//System.out.print("There was a problem connecting to the database...");
// 		System.err.println(e.getMessage());
// 		System.out.println("Program Ending...");
// 		System.exit(0);
// 	}
// }




// public static void goodreads3(String inputBookName3){

// 	Connection connect;
// 	Statement stmt;
// 	String query;

// 	try{
		
// 		Class.forName("org.sqlite.JDBC");
// 		connect = DriverManager.getConnection("jdbc:sqlite:data.sqlite");
// 		stmt = connect.createStatement();
// 		query = "INSERT INTO hasread (hr_bookID, hr_name) Select b_bookID, b_title FROM books " + "where b_title = " + "'%" + inputBookName3 + "%'";
// 		stmt.executeQuery(query);
// 		// while(rs.next()){
// 		// 	String b_bookID = rs.getString("b_bookID");
// 		// 	String b_title = rs.getString("b_title");
// 		// 	String b_langcode = rs.getString("b_langcode");
// 		// 	String b_numpages = rs.getString("b_numpages");
// 		// 	String b_isbn = rs.getString("b_isbn");
// 		// 	System.out.println(b_bookID + "\t" + b_title + "\t" + b_langcode + "\t" + b_numpages + "\t" + b_isbn);
// 		// }
// 		// rs.close();
// 		connect.commit();
// 		stmt.close();
// 		connect.close();
// 		System.out.println();
// 	}
// 	catch(Exception e){
// 		//System.out.print("There was a problem connecting to the database...");
// 		System.err.println(e.getMessage());
// 		System.out.println("Program Ending...");
// 		System.exit(0);
// 	}
// }







// // inserting new book in Goodreads -- #2

// public static int createBookID() {
// 	System.out.println("Creating new bookID...");
// 	//user chooses lang code from list by entering a number
// 	int newID = 1;
	

// 	try {

// 		String sqlBID = "select max(b_bookID)+1 as newID "+
// 						"from books; ";

	
// 		ResultSet rsBID = connect.prepareStatement(sqlBID).executeQuery();
		
// 		while (rsBID.next()) {
// 			newID = rsBID.getInt("newID");
// 		}
// 		// STEP: Clean-up environment
// 		rsBID.close();
// 		//stmt.close();
// 		System.out.println("Successfully created book id: " + newID);
// 		return newID;
	
// 	} catch (Exception e) {
// 		System.err.println(e.getClass().getName() + ": " + e.getMessage());
// 	}
	
// 	System.out.println("++++++++++++++++++++++++++++++++++");
// 	return 0;
// }
// public static int authorCount() {
// 	System.out.println("Checking to see if author already exists...");
// 	//user chooses lang code from list by entering a number
// 	int authCount = 1;
	
	
// 	try {

// 		String sqlBID = "select count(a_name) " +
// 						"from authors " +
// 						"where a_name = 'habibatu karim mboka'; ";

	
// 		ResultSet rsBID = connect.prepareStatement(sqlBID).executeQuery();
		
// 		while (rsBID.next()) {
// 			authCount = rsBID.getInt("count(a_name)");
// 		}
// 		System.out.println(authCount);
// 		// STEP: Clean-up environment
// 		rsBID.close();
// 		//stmt.close();
// 	   // System.out.println("Successfully created book id: " + newID);
// 		return authCount;
// 	} catch (Exception e) {
// 		System.err.println(e.getClass().getName() + ": " + e.getMessage());
// 	}
	
// 	System.out.println("++++++++++++++++++++++++++++++++++");
// 	return 0;
// }
// // public static void printLangCode() {
	
// // 	//user chooses lang code from list by entering a number
// // 	String lang = "";
	
	
// // 	try {
		

// // 		String sqlBID = "select b_langcode " +
// // 						"from books; ";

	
// // 		ResultSet rsBID = connect.prepareStatement(sqlBID).executeQuery();
		
// // 		while (rsBID.next()) {
// // 			lang = rsBID.getString("b_langcode");
// // 			System.out.println(lang);
// // 		}
		
// // 		// STEP: Clean-up environment
// // 		rsBID.close();
// // 		//stmt.close();
// // 	   // System.out.println("Successfully created book id: " + newID);
// // 	   connect.close(); 
// // 	} catch (Exception e) {
// // 		System.err.println(e.getClass().getName() + ": " + e.getMessage());
// // 	}
	
// // 	System.out.println("++++++++++++++++++++++++++++++++++");

// // }
// public static void insertNewBook() {
// 	//System.out.println("Creating new bookID...");
// 	int newID = createBookID();
// 	System.out.print("Enter title: ");
// 	String title = myScanner.nextLine();
// 	//String title = "Mboka: The Way of Life in a Congo Village (A Congo Memoir)";
// 	System.out.print("Enter author(s)--seperate multiple authors with a forward slash: ");
// 	String authors = myScanner.nextLine();
// 	//String authors = "Lona B. Kenney";
// 	System.out.print("Enter book publisher: ");
// 	String publisher = myScanner.nextLine();
// 	//String publisher = "Crown Publishers";
// 	System.out.print("Enter isbn: ");
// 	String isbn = myScanner.nextLine();
// 	//String isbn = "051750037X";     
// 	System.out.print("Enter page count: ");
// 	int pagecnt = myScanner.nextInt();           
//    // int pagecnt = 264;
// //    System.out.print("Enter language code--enter \"see list\" to see available codes: ");
// //    String lang = myScanner.nextLine();
//    //String lang = "eng";
// 	// if(lang == "see list" || lang == "See list"){
// 	// 	printLangCode();
// 	// 	System.out.print("Enter language code: ");
// 	// 	lang = myScanner.nextLine();
// 	// }



// 	try {
// 		//insert to books
// 		String sql = "INSERT INTO books (b_bookID, b_title, b_numpages, b_isbn) "+
// 					 "VALUES(?,?,?,?); ";
// 		PreparedStatement stmt = connect.prepareStatement(sql);
// 		stmt.setInt(1, newID);
// 		stmt.setString(2, title);
// 		//stmt.setString(3, lang);
// 		stmt.setInt(4, pagecnt);
// 		stmt.setString(5, isbn);
// 		stmt.addBatch();
// 		stmt.executeBatch();
// 		//insert to authored
// 		String sql2 = "INSERT INTO authored (ad_name, ad_bookID) "+
// 					 "VALUES(?,?); ";
// 		PreparedStatement stmt2 = connect.prepareStatement(sql2);
// 		stmt2.setString(1,authors);
// 		stmt2.setInt(2, newID);
// 		stmt2.addBatch();
// 		stmt2.executeBatch();
// 		//insert to authors
// 		//ensure that author does not exist
// 		PreparedStatement stmt3;
// 		if(authorCount() == 0){
// 			String sql3 = "INSERT INTO authors (a_name) "+
// 						"VALUES(?); ";
// 			stmt3 = connect.prepareStatement(sql3);
// 			stmt3.setString(1,authors);
// 			stmt3.addBatch();
// 			stmt3.executeBatch();
// 			connect.commit();
// 			stmt3.close();          
// 		} else{
// 			System.out.println("Author already exists!");
// 		}
// 		//insert into publisher
// 		String sql4 = "INSERT INTO publisher (p_name, p_bookID, p_authname) "+
// 					 "VALUES(?,?,?); ";
// 		PreparedStatement stmt4 = connect.prepareStatement(sql4);
// 		stmt4.setString(1,publisher);
// 		stmt4.setInt(2, newID);
// 		stmt4.setString(3, authors);
// 		stmt4.addBatch();
// 		stmt4.executeBatch();
// 		//comit changes
// 		connect.commit();
// 		// STEP: Clean-up environment
// 		stmt.close();
// 		stmt2.close();
// 		stmt4.close();
		
// 		System.out.println("Successfully created book: " + title);
		
// 	} catch (Exception e) {
// 		System.err.println(e.getClass().getName() + ": " + e.getMessage());
// 	}
	
// 	System.out.println("++++++++++++++++++++++++++++++++++");
// }






// }
