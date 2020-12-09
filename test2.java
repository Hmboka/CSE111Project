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
		System.out.println("6. What book has a rating of at least (your input of rating value), written in (insert language), and has at least (insert number of pages) pages?"); //done
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
                            insertNewBook();
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
			if(selectInput == 6){
				goodreads6();
	
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
		System.out.println("This is your Personal Library Tracker");		//done
		System.out.println("What would you like to do?");
		System.out.println("1. Search books from your Personal Read Database?");
		System.out.println("2. Would you like to add a book from the general GoodReads Database to your Personal Read Database?");
		System.out.println("3. Search books from your Favorite Database?");  //done
		System.out.println("4. Would you like to manually add, delete from your Favorite Database?");
		System.out.println("5. Would you like to search books based on Authors from either Database?");
		System.out.println("6. Would you like to search books based on Rating from either Database?"); 
		System.out.println("0. Back");
		int selectInputp = myScanner.nextInt();
        try {

            if(selectInputp < 0 || selectInputp > 6){
                System.out.println("Invalid Entry. Please enter an option listed above.");
                personal();
            }
            else if(selectInputp == 1){
                System.out.println("Please enter the name of the book:");
                myScanner.nextLine();
				String inputBookNamep1 = myScanner.nextLine();
				System.out.println("++++++++++++++++++++++++++++++++++");
				personal1(inputBookNamep1);
			}
			
            else if(selectInputp == 2){
                System.out.println("What would you like to do?");
                System.out.println("1. Add book");
                System.out.println("2. Delete Book");
                    int selectInputp2 = myScanner.nextInt();
                    if(selectInputp2 < 0 || selectInputp2 > 2){
                        System.out.println("Invalid Entry. Please enter an option listed above.");
                        personal();
                    }
                    else if(selectInputp2 == 1){				
                          //  insertNewBook(); 
                        }
                        else {
                            //System.out.println(selectInput2);	
                            // System.out.println("Enter Name of Book you want to Delete:");
                            // myScanner.nextLine();
                            // String inputBookName2_2 = myScanner.nextLine();
                            // goodreads2_2(inputBookName2_2);
                        }
			}
			
			else if(selectInputp == 3){
                System.out.println("Please enter the name of the book:");
                myScanner.nextLine();
				String inputBookNamep3 = myScanner.nextLine();
				System.out.println("++++++++++++++++++++++++++++++++++");
				personal3(inputBookNamep3);
			}

			if(selectInputp == 0){
				userIntro();
			}
	
			else if(selectInputp == 6){
				//userOption6();
			}
			personal();
    
           
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
    private int goodReads1(String inputBookName) {
        //searches book based on title entered
        // b_bookID, b_title, a_name, r_avgbookrating, b_langcode, b_numpages, b_isbn 
        try {
            Statement stmt = c.createStatement();
			String query = "SELECT * " + "FROM books " + "where b_title LIKE " + "'%" + inputBookName + "%'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				String b_bookID = rs.getString("b_bookID");
				String b_title = rs.getString("b_title");
                String b_langcode = rs.getString("b_langcode");
                String b_numpages = rs.getString("b_numpages");
                String b_isbn = rs.getString("b_isbn");
				System.out.println(b_bookID + "\t" + b_title + "\t" + b_langcode + "\t" + b_numpages + "\t" + b_isbn);
			}
			rs.close();
			stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
	
	private int personal1(String inputBookNamep1) {
        //searches book based on title entered
		// b_bookID, b_title, a_name, r_avgbookrating, b_langcode, b_numpages, b_isbn 
		
        try {
             String sql  = "SELECT * " + "FROM hasread " + "where hr_name LIKE " + "'%" + inputBookNamep1 + "%'";
             ResultSet rs = c.prepareStatement(sql).executeQuery();
             while (rs.next()) {
				String hr_name = rs.getString("hr_name");
                String hr_bookID = rs.getString("hr_bookID");
                String hr_myrating = rs.getString("hr_myrating");
                double hr_date = rs.getDouble("hr_date");
                System.out.println(hr_name + "\t" + hr_bookID + "\t" + hr_myrating + hr_date + "\n");
            }
            rs.close();
  
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }


	private int personal3(String inputBookNamep3) {
        //searches book based on title entered
		// b_bookID, b_title, a_name, r_avgbookrating, b_langcode, b_numpages, b_isbn 
		
        try {
             String sql  = "SELECT * " + "FROM favebooks " + "where fb_title LIKE " + "'%" + inputBookNamep3 + "%'";
             ResultSet rs = c.prepareStatement(sql).executeQuery();
             while (rs.next()) {
				String fb_title = rs.getString("fb_title");
                String fb_bookID = rs.getString("fb_bookID");
                String fb_myrating = rs.getString("fb_myrating");
                System.out.println(fb_title + "\t" + fb_bookID + "\t" + fb_myrating + "\n");
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

	private int goodreads6() {

        System.out.println("Enter Rating");
		myScanner.nextLine();
		String input6_2 = myScanner.nextLine();
		System.out.println("Enter Langauge Code");
		//myScanner.nextLine();
		String input6_1 = myScanner.nextLine();
		System.out.println("Enter Number of Pages");
		//myScanner.nextLine();
		String input6_3 = myScanner.nextLine();
        try {
            Statement stmt = c.createStatement();
//			String query = "SELECT b_title, b_numpages, b_langcode " + "FROM books " + "where b_numpages >= " + "'%" + input6_3 + "%'" + "AND b_langcode = " + "'%" + input6_1 + "%'" + "AND b_bookID in (SELECT r_bookID FROM rating where r_avgbookrating >= " + "'%" + input6_2 + "%')";
			String query = "SELECT b_title, b_numpages, b_langcode " + "FROM books " + "where b_numpages >= " +  input6_3 + " AND b_langcode = '" + input6_1 + "' AND b_bookID IN(Select r_bookID From rating Where r_avgbookrating >= " + input6_2 + ")"; 

			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				String b_title = rs.getString("b_title");
                int b_numpages = rs.getInt("b_numpages");
				String b_langcode = rs.getString("b_langcode");
				System.out.println(b_title + "\t" + b_numpages + "\t" + b_langcode + "\t");
			}
			rs.close();
			stmt.close();

        } catch (Exception e) {
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
        System.out.print("Enter author(s)--Seperate multiple authors with a forward slash: ");
        String authors = myScanner.nextLine();
            //String authors = "Lona B. Kenney";
        //System.out.println("Title = " + title);
        //System.out.println("author = " + authors);
        System.out.print("Enter book publisher: ");
        String publisher = myScanner.nextLine();
            //String publisher = "Crown Publishers";
        System.out.print("Enter book publish date: ");
        String pubdate = myScanner.nextLine();
        System.out.print("Enter isbn: ");
        String isbn = myScanner.nextLine();
            //String isbn = "051750037X";     051750037X
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
            //insert to books
            String sql = "INSERT INTO books VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, newID);
            stmt.setString(2, title);
            stmt.setString(3, lang);
            stmt.setInt(4, pagecnt);
            stmt.setString(5, isbn);  

            stmt.executeUpdate();


            //insert to authored
            String sql2 = "INSERT INTO authored "+
                         "VALUES(?, ?); ";
            PreparedStatement stmt2 = c.prepareStatement(sql2);
            stmt2.setString(1,authors);
            stmt2.setInt(2, newID);


            stmt2.executeUpdate();
           
            //insert to authors
            //ensure that author does not exist
            PreparedStatement stmt3;
            if(authorCount(authors) == 0){
                String sql3 = "INSERT INTO authors(a_name, a_authrating) "+
                            "VALUES(?, ?); ";
                stmt3 = c.prepareStatement(sql3);
                stmt3.setString(1,authors);
                stmt3.setDouble(2,5.0);
                
                stmt3.executeUpdate();
                c.commit();
                stmt3.close();          
            } else{
                System.out.println("Author already exists!");
            }
            //insert into publisher
            String sql4 = "INSERT INTO publisher " +
                         "VALUES(?,?,?, ?); ";
            PreparedStatement stmt4 = c.prepareStatement(sql4);
            stmt4.setString(1,publisher);
            stmt4.setInt(2, newID);
            stmt4.setString(3, authors);
            stmt4.setString(4, pubdate);

            stmt4.executeUpdate();
            
               
            // STEP: Clean-up environment
            c.commit();
            stmt2.close();
            stmt.close();
            stmt4.close();
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

   
    public static void main(String args[]) {
        test2 sj = new test2();
        
        sj.openConnection("data/grapp.sqlite");
        sj.userIntro();
        //sj.createBookID();
        //sj.closeConnection();
    }
}
