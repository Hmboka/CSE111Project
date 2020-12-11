// STEP: Import required packages
import java.sql.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.*;
import java.awt.*;  
import java.awt.event.*;  

public class testingG {
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
                System.out.println("See error message below and try again: ");
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
                System.out.println("You have entered an invalid input, please try again.");
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
		System.out.println("2. Would you like to add, or delete from the general GoodReads Database?"); //done
		System.out.println("3. Would you like to add a book from this general GoodReads Database to your Favorite Database?"); //done
		System.out.println("4. Would you like to search books written by a certain auhtor?");		//done
		System.out.println("5. Would you like to search books with a certain rating?");		//done
		System.out.println("6. Search for a book that has a rating of at least (your input of rating value), written in (insert language), and has at least (insert number of pages) pages?"); //done
		System.out.println("0. Back"); //done
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
                            
                            deleteBook();
                        }
			}
			
            if(selectInput == 3){
                personal2(0);
        
            }

			if(selectInput == 4){
				System.out.println("Please enter the name of the author?");
				myScanner.nextLine();
				String inputBookName4 = myScanner.nextLine();
				System.out.println("++++++++++++++++++++++++++++++++++");
				goodreads4(inputBookName4);
	
			}
			if(selectInput == 5){
				goodreads5();
	
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
		System.out.println("This is your Personal Library Tracker");		
		System.out.println("What would you like to do?");	
		System.out.println("1. Search books from your Personal Read Database?");	//done
		System.out.println("2. Would you like to add a book from the general GoodReads Database to your Personal Read Database?"); //done
		System.out.println("3. Search books from your Favorite Database?");  //done
		System.out.println("4. Would you like to manually add, delete from your Favorite Database?"); //done
		System.out.println("5. Would you like to search books based on Authors from Read Database?");	//done
		System.out.println("6. Would you like to search books based on Authors from Favorite Database?");	//done
		System.out.println("7. Would you like to search books based on Rating from Read Database?"); 	//done
		System.out.println("8. Would you like to search books based on Rating from Favorite Database?"); 	//done
		System.out.println("0. Back");
		int selectInputp = myScanner.nextInt();
        try {

            if(selectInputp < 0 || selectInputp > 8){
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
            else if (selectInputp == 2){
                System.out.println("++++++++++++++++++++++++++++++++++");
                personal2(0);
            }
			
            // else if(selectInputp == 4){
            //     // System.out.println("What would you like to do?");
            //     // System.out.println("1. Add book");
            //     // System.out.println("2. Delete Book");
            //     //     int selectInputp2 = myScanner.nextInt();
            //     //     if(selectInputp2 < 0 || selectInputp2 > 2){
            //     //         System.out.println("Invalid Entry. Please enter an option listed above.");
            //             personal4();
            //         }
            //         else if(selectInputp2 == 1){				
            //               //  insertNewBook(); 
            //             }
            //             else {
            //                 //System.out.println(selectInput2);	
            //                 // System.out.println("Enter Name of Book you want to Delete:");
            //                 // myScanner.nextLine();
            //                 // String inputBookName2_2 = myScanner.nextLine();
            //                 // goodreads2_2(inputBookName2_2);
            //             }
			// }
			
			else if(selectInputp == 3){
                System.out.println("Please enter the name of the book:");
                myScanner.nextLine();
				String inputBookNamep3 = myScanner.nextLine();
				System.out.println("++++++++++++++++++++++++++++++++++");
				personal3(inputBookNamep3);
            }
            else if(selectInputp == 4){
                personal4();
            }
			
			else if(selectInputp == 5){
				personal5();
			}
			else if(selectInputp == 6){
				personal6();
			}
			else if(selectInputp == 7){
				personal7();
			}

			else if(selectInputp == 8){
				personal8();
			}
			if(selectInputp == 0){
				userIntro();
			}
	
			// else if(selectInputp == 6){
			// 	//userOption6();
			// }
			personal();
    
           
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
    private int goodReads1(String inputBookName) {
        int count = 0;
        //searches book based on title entered
        // b_bookID, b_title, a_name, r_avgbookrating, b_langcode, b_numpages, b_isbn 
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("Book ID" + "\t" + "Title" + "\t" + "Language Code" + "\t" + "# of Pages" + "\t" + "isbn");
        
        try {
            Statement stmt = c.createStatement();
			String query = "SELECT * " + "FROM books " + "where b_title LIKE " + "'%" + inputBookName + "%'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
                count++;
				String b_bookID = rs.getString("b_bookID");
				String b_title = rs.getString("b_title");
                String b_langcode = rs.getString("b_langcode");
                String b_numpages = rs.getString("b_numpages");
                String b_isbn = rs.getString("b_isbn");
				System.out.println(b_bookID + "\t" + b_title + "\t" + b_langcode + "\t" + b_numpages + "\t" + b_isbn);
            }
            if (count == 0){
                System.out.println("No matching results found for this search");
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
        int count = 0;
        //searches book based on title entered
		// b_bookID, b_title, a_name, r_avgbookrating, b_langcode, b_numpages, b_isbn 
		
        try {
             String sql  = "SELECT * " + "FROM hasread " + "where hr_name LIKE " + "'%" + inputBookNamep1 + "%'";
             ResultSet rs = c.prepareStatement(sql).executeQuery();
             while (rs.next()) {
                 count++;
				String hr_name = rs.getString("hr_name");
                String hr_bookID = rs.getString("hr_bookID");
                String hr_myrating = rs.getString("hr_myrating");
                double hr_date = rs.getDouble("hr_date");
                System.out.println(hr_name + "\t" + hr_bookID + "\t" + hr_myrating + hr_date + "\n");
            }
            if (count == 0){
                System.out.println("No matching results found for this search");
            }
            rs.close();
  
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }

    private int personal2(int tempID) { //to make this function more usable you can take in a decider string which decides how this functio
        //add a book from the general GoodReads Database to your Personal Read Database?
        Scanner scanner = new Scanner(System.in); 
        String title = "";
        String answer = "";
        int bookID = 0;
        if (tempID == 0){
            // Ask user if they want to search for the book ID
            System.out.println("Do you know the book ID for the book you'd like to add? Enter 'Yes' or 'No'");
            answer = scanner.nextLine();
            // check if user enters invalid answer

            String Str = new String(answer);
            answer = Str.toUpperCase();
            
            if (answer.equals("YES") == false){

                if(answer.equals("NO") == false){
                    //System.out.println("failed this test");
                    System.out.println("Sorry, " + answer +" is not a valid answer \n Please try again");
                    personal2(0);
                }
            }
            if(answer.equals("NO") == true){
                System.out.print("To search title and copy bookID of desired book, enter the book's title: ");
                title = scanner.nextLine();
                if(title.length() == 0){
                    System.out.print("Sorry, you're entry is not a valid title! Please try again.");
                    personal2(0);
                } else {
                    goodReads1(title);
                }
            } else{
                
            }

            //user pastes book ID in terminal
            System.out.print("Enter book ID: ");
            bookID = scanner.nextInt();
            
            // if what they entered was not an integer
            if(bookID != (int)(bookID)){
                System.out.print("Sorry, you're entry is not a valid bookID! Please try again. \n");
                personal2(0);
            }
            // if that book does not exist in the database
            if (bookIDCount(bookID) == 0){
                System.out.print("Sorry, book ID '" + bookID + "' does not exist in database. Please try again. \n");
                personal2(0);
            }
            // if that book already exists in the user's favebooks'
            if (fbIDCount(bookID) == 1){
                System.out.print("Book ID '" + bookID + "' already exists in your favebooks! \n");
                personal();
            }
        } else{
            bookID = tempID;
            if(bookID != (int)(bookID)){
                System.out.print("Sorry, you're entry is not a valid bookID! Please try again. \n");
                personal2(0);
            }
            // if that book does not exist in the database
            if (bookIDCount(bookID) == 0){
                System.out.print("Sorry, book ID '" + bookID + "' does not exist in database. Please try again. \n");
                personal2(0);
            }
            // if that book already exists in the user's favebooks'
            if (fbIDCount(bookID) == 1){
                System.out.print("Book ID '" + bookID + "' already exists in your favebooks! \n");
                personal();
            }
        }
        try {

            Statement stmt = c.createStatement();
			String sql = "SELECT b_title " + "FROM books " + "where b_bookID = " + bookID;
            ResultSet rs = stmt.executeQuery(sql);
            String b_title = "";
			while(rs.next()){
				b_title = rs.getString("b_title");
            }

            // collect users rating of book
            System.out.print("Enter your book rating for ''" + b_title + "': ");
            double fb_rating = myScanner.nextDouble();
            
            // check if book rating is not a double
            if(fb_rating != (double)(fb_rating)){
                System.out.print("Sorry, you're entry is not a valid rating! \n Please enter a value between 0.1 to 5.0: ");
                fb_rating = myScanner.nextDouble();

                //as long as user keeps putting in incorrect values, they will keep having to enter a rating
                while(fb_rating < 0.1 || fb_rating > 5.0){
                    System.out.print("Invalid rating! \n Please enter a value between 0.1 to 5.0: ");
                    fb_rating = myScanner.nextDouble();
                    System.out.println();
                }
                
            }
            System.out.println("made it here");
            // insert data into favebooks
            String sql2 = "INSERT INTO favebooks " +
                          "VALUES(?,?,?); ";

            PreparedStatement stmt2 = c.prepareStatement(sql2);
            stmt2.setInt(1, bookID);
            stmt2.setString(2, b_title);
            stmt2.setDouble(3, fb_rating);

            stmt2.executeUpdate();


            // STEP: Commit
            c.commit();

            // STEP: Clean up Environment
			rs.close();
			stmt.close();
            stmt2.close();
            scanner.close();
  
            System.out.println("Successfully added '" + b_title + "' to your favebooks library.");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }


	private int personal3(String inputBookNamep3) {
        int count = 0;
        //searches book based on title entered
		// b_bookID, b_title, a_name, r_avgbookrating, b_langcode, b_numpages, b_isbn 
		
        try {
             String sql  = "SELECT * " + "FROM favebooks " + "where fb_title LIKE " + "'%" + inputBookNamep3 + "%'";
             ResultSet rs = c.prepareStatement(sql).executeQuery();
             System.out.println("Book Title" + "\t" + "Book ID" + "\t" + "myRating" + "\n");
             while (rs.next()) {
                 count++;
				String fb_title = rs.getString("fb_title");
                String fb_bookID = rs.getString("fb_bookID");
                String fb_myrating = rs.getString("fb_myrating");
                System.out.println(fb_title + "\t" + fb_bookID + "\t" + fb_myrating + "\n");
            }
            if (count == 0){
                System.out.println("No matching results found for this search");
            }
            rs.close();
  
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
    
    private int goodreads4(String inputBookName4){
        int count = 0;
        try{
            
            String sql = "SELECT b_title FROM books WHERE b_bookID IN (SELECT ad_bookID FROM authored " + "where ad_name LIKE " + "'%" + inputBookName4 + "%')";
            ResultSet rs = c.prepareStatement(sql).executeQuery();
            while(rs.next()){
                count++;
                String b_title = rs.getString("b_title");
                System.out.println(b_title);
            }
            if (count == 0){
                System.out.println("No matching results found for this search");
            }
            rs.close();
    
        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }

	private int goodreads5() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        System.out.println("Enter Rating");
        double input5 = scanner.nextDouble();
        while(input5 == 0){
            System.out.println("Invalid entry. Try again.");
            input5 = scanner.nextDouble();
        }
                     // check if book rating is not a double
            if(input5 != (double)(input5)){
                System.out.print("Sorry, you're entry is not a valid rating! \n Please enter a value between 0.1 to 5.0: ");
                input5 = scanner.nextDouble();

                //as long as user keeps putting in incorrect values, they will keep having to enter a rating
                while(input5 < 0.1 || input5 > 5.0){
                    System.out.print("Invalid rating! \n Please enter a value between 0.1 to 5.0: ");
                    input5 = scanner.nextDouble();
                    System.out.println();
                }
                
            }
		
		
        try {
            Statement stmt = c.createStatement();
			String query = "SELECT b_title, r_avgbookrating " + "FROM books, rating " + "where b_bookID IN (Select r_bookID From rating Where r_avgbookrating >= " + input5 + ") Group By b_title"; 

			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
                count++;
				String b_title = rs.getString("b_title");
                double r_avgbookrating = rs.getDouble("r_avgbookrating");
				System.out.println(b_title + "\t" + r_avgbookrating + "\t");
            }
            if (count == 0){
                System.out.println("No matching results found for this search");
            }
			rs.close();
            stmt.close();
            scanner.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
    private int personal4() {
        int tempID = 0;
        try {

            tempID = insertNewBook();
            personal2(tempID);
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
	private int personal5() {
        int count = 0;
		System.out.println("Enter Author Name");
		myScanner.nextLine();
        String inputp5 = myScanner.nextLine();
        while (inputp5.length() == 0){
            System.out.print("Invalid input. Please try again: ");
            inputp5 = myScanner.nextLine();
        }
		System.out.println("++++++++++++++++++++++++++++++++++");
        try {
             String sql  = "SELECT hr_name from hasread where hr_bookID IN (Select ad_bookID From authored where ad_name LIKE " + "'%" + inputp5 + "%')";
             ResultSet rs = c.prepareStatement(sql).executeQuery();
             while (rs.next()) {
                 count++;
				String hr_name = rs.getString("hr_name");
                System.out.println(hr_name + "\t");
            }
            if (count == 0){
                System.out.println("No matching results found for this search");
            }
            rs.close();
  
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }
	
	private int personal6() {
        int count = 0;
		System.out.println("Enter Author Name");
		myScanner.nextLine();
        String inputp6 = myScanner.nextLine();
        while (inputp6.length() == 0){
            System.out.print("Invalid input. Please try again: ");
            inputp6 = myScanner.nextLine();
        }
		System.out.println("++++++++++++++++++++++++++++++++++");
        try {
             String sql  = "SELECT fb_title from favebooks where fb_bookID IN (Select ad_bookID From authored where ad_name LIKE " + "'%" + inputp6 + "%')";
             ResultSet rs = c.prepareStatement(sql).executeQuery();
             while (rs.next()) {
                 count++;
				String fb_title = rs.getString("fb_title");
                System.out.println(fb_title + "\t");
            }
            if (count == 0){
                System.out.println("No matching results found for this search");
            }
            rs.close();
  
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }


	private int goodreads6() {
        Scanner scanner = new Scanner(System.in); 
        int count = 0;

		System.out.println("Enter Rating ");
        double inputp6_2 = scanner.nextDouble();
             // check if book rating is not a double
            if(inputp6_2 != (double)(inputp6_2)){
                System.out.print("Sorry, you're entry is not a valid rating! \n Please enter a value between 0.1 to 5.0: ");
                inputp6_2 = scanner.nextDouble();

                //as long as user keeps putting in incorrect values, they will keep having to enter a rating
                while(inputp6_2 < 0.1 || inputp6_2 > 5.0){
                    System.out.print("Invalid rating! \n Please enter a value between 0.1 to 5.0: ");
                    inputp6_2 = scanner.nextDouble();
                    System.out.println();
                }
                
            }
		
		System.out.println("Enter Langauge Code");
        String input6_1 = scanner.nextLine();
        while (input6_1.length() == 0){
            System.out.print("Invalid input. Please try again: ");
            input6_1 = scanner.nextLine();
        }
		System.out.println("Enter Number of Pages");
        int input6_3 = scanner.nextInt();
        while (input6_3 <= 0){
            System.out.print("Invalid input. Please try again: ");
            input6_3 = scanner.nextInt();
        }
        
        try {
            Statement stmt = c.createStatement();

			String query = "SELECT b_title, b_numpages, b_langcode " + "FROM books " + "where b_numpages >= " +  input6_3 + " AND b_langcode = '" + input6_1 + "' AND b_bookID IN(Select r_bookID From rating Where r_avgbookrating >= " + inputp6_2 + ")"; 

			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
                count++;
				String b_title = rs.getString("b_title");
                int b_numpages = rs.getInt("b_numpages");
				String b_langcode = rs.getString("b_langcode");
				System.out.println(b_title + "\t" + b_numpages + "\t" + b_langcode + "\t");
            }
            if (count == 0){
                System.out.println("No matching results found for this search");
            }
			rs.close();
            stmt.close();
            scanner.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }

	private int personal7() {
        Scanner scanner = new Scanner(System.in); 
        int count = 0;

		System.out.println("Enter Rating ");
        double inputp7 = scanner.nextDouble();
             // check if book rating is not a double
            if(inputp7 != (double)(inputp7)){
                System.out.print("Sorry, you're entry is not a valid rating! \n Please enter a value between 0.1 to 5.0: ");
                inputp7 = scanner.nextDouble();

                //as long as user keeps putting in incorrect values, they will keep having to enter a rating
                while(inputp7 < 0.1 || inputp7 > 5.0){
                    System.out.print("Invalid rating! \n Please enter a value between 0.1 to 5.0: ");
                    inputp7 = scanner.nextDouble();
                    System.out.println();
                }
                
            }
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println();
        try {
             String sql  = "SELECT fb_title from favebooks where fb_myrating >= " + inputp7;
             ResultSet rs = c.prepareStatement(sql).executeQuery();
             while (rs.next()) {
                 count++;
				String fb_title = rs.getString("fb_title");
                System.out.println(fb_title + "\t");
            }
            if (count == 0){
                System.out.println("No favebook with such rating found.");
            }
            rs.close();
            scanner.close();
  
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
	}
	
	private int personal8() {
        Scanner scanner = new Scanner(System.in); 
        int count = 0;
		System.out.println("Enter Rating ");
        double inputp8 = scanner.nextDouble();
                    // check if book rating is not a double
            if(inputp8 != (double)(inputp8)){
                System.out.print("Sorry, you're entry is not a valid rating! \n Please enter a value between 0.1 to 5.0: ");
                inputp8 = scanner.nextDouble();

                //as long as user keeps putting in incorrect values, they will keep having to enter a rating
                while(inputp8 < 0.1 || inputp8 > 5.0){
                    System.out.print("Invalid rating! \n Please enter a value between 0.1 to 5.0: ");
                    inputp8 = scanner.nextDouble();
                    System.out.println();
                }
                
            }
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println();
        try {
            
             String sql  = "SELECT hr_name from hasread where hr_myrating >= " + inputp8;
             ResultSet rs = c.prepareStatement(sql).executeQuery();

             while (rs.next()) {
                count++;
				String hr_name = rs.getString("hr_name");
                System.out.println(hr_name + "\t");
            }
            if (count == 0){
                System.out.println("No favebook with such rating found.");
            }
            rs.close();
            scanner.close();
  
            
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

    
    private int insertNewBook() {
        String usrInput = "";
        String answer = "";
        int newID = createBookID();
        System.out.println();
        System.out.print("Enter exact book title: ");
        myScanner.nextLine();
        String title = myScanner.nextLine();
            //String title = "Mboka: The Way of Life in a Congo Village (A Congo Memoir)";
            while (title.length() == 0){
                System.out.print("Invalid input. Please try again: ");
                title = myScanner.nextLine();
            }
        int titleCount = bookTitleCount(title);
        System.out.println();
        if(titleCount > 0){
            System.out.println("There are currently " + titleCount + " book(s) which match this title.");
            System.out.println("Are you sure you would like to add this book?");
            System.out.println("Enter 'Exit' to cancel book insertion, enter 'See books' to view matching results. \nOR enter 'Continue' to add book anyway: ");
            answer = myScanner.nextLine();
            System.out.println();
        
        // check if user enters invalid answer

        String Str = new String(answer);
        answer = Str.toUpperCase();
        if(answer.equals("EXIT") == true){
            userIntro();
        }
        if (answer.equals("SEE BOOKS") == false){

            if(answer.equals("CONTINUE") == false){
                while (answer.equals("CONTINUE") == false && answer.equals("SEE BOOKS") == false && answer.equals("EXIT") == false){
                    System.out.println("Sorry, " + answer +" is not a valid answer. Please try again: ");
                    answer = myScanner.nextLine();
                    answer = answer.toUpperCase();
                    if(answer.equals("EXIT") == true){
                        userIntro();
                    }
                }
            }
        }

        if(answer.equals("SEE BOOKS") == true){
            System.out.println("Okay lets search the GooodReads database to find the book ID... ");
            System.out.print("To search title and copy bookID of desired book, enter the book's title: ");
            title = myScanner.nextLine();
            if(title.length() == 0){
                while (title.length() == 0){
                    System.out.print("Invalid input. Please try again: ");
                    title = myScanner.nextLine();
                }
            } else {
                goodReads1(title);
                System.out.print("Do you still wish to continue with adding this book? Enter 'Yes' or 'No': ");
                usrInput = myScanner.nextLine();

                String Str3 = new String(usrInput);
                usrInput = Str3.toUpperCase();
                System.out.println(usrInput);

                if (usrInput.equals("YES") == false){

                    if(usrInput.equals("NO") == false){
                        //System.out.println("failed this test");
                        while(usrInput.equals("NO") == false && usrInput.equals("YES") == false){
                            System.out.println("Sorry, '" + usrInput +"' is not a valid answer. Please try again: ");
                            usrInput = myScanner.nextLine();
                            usrInput = usrInput.toUpperCase();
                        }
                        
                    }
                }
                if(usrInput.equals("NO") == true){
                    personal();
                } 
            }
        }
            
    } else {
        System.out.println("Book does not exist yet. You may proceed.");
    }


        //BEGIN INSERT

        System.out.print("Enter author(s)--Seperate multiple authors with a forward slash: ");
        String authors = myScanner.nextLine();
            //String authors = "Lona B. Kenney";
            while (authors.length() == 0 || authors.contains(" /") || authors.contains("/ ")){
                if(authors.contains(" /") || authors.contains("/ ")){
                    System.out.print("Invalid input. No whitespace between forward slashes and authors allowed. Try again: ");
                    authors = myScanner.nextLine();
                }else{
                System.out.print("Invalid input. Please try again: ");
                authors = myScanner.nextLine();
                }
            }
        System.out.print("Enter book publisher: ");
        String publisher = myScanner.nextLine();
            //String publisher = "Crown Publishers";
            while (publisher.length() == 0){
                System.out.print("Invalid input. Please try again: ");
                publisher = myScanner.nextLine();
            }
        System.out.print("Enter book publish date in the form of mm/dd/yyyy: ");
        String pubdate = myScanner.nextLine();
        //System.out.println(pubdate.length());
            while (pubdate.contains(" /") || pubdate.contains("/ ") || pubdate.length() != 10){
                if(pubdate.contains(" /") || pubdate.contains("/ ")){
                    System.out.print("Invalid input. No whitespace between forward slashes. Try again: ");
                    pubdate = myScanner.nextLine();
                } else if (pubdate.charAt(2) != '/' || pubdate.charAt(5) != '/'){
                    System.out.println("Invalid input. Please follow format.");
                    System.out.print("Enter book publish date in the form of m/dd/yyyy: ");
                    pubdate = myScanner.nextLine();
                }
                else{
                System.out.print("Invalid input. Please try again: ");
                pubdate = myScanner.nextLine();
                }
                
            }
        System.out.print("Enter isbn: ");
        String isbn = myScanner.nextLine();
            //String isbn = "051750037X";
            while (isbn.length() != 10 || isbn.contains(" ")){
                System.out.print("Invalid input. Please try again: ");
                isbn = myScanner.nextLine();
            }

        System.out.print("Enter page count: ");
        int pagecnt = myScanner.nextInt();           
            // int pagecnt = 264;
            while (pagecnt <= 0){
                System.out.print("Invalid input. Please try again: ");
                pagecnt = myScanner.nextInt();
            }

        System.out.print("Enter language code--enter \"see list\" to see available codes: ");
        myScanner.nextLine();
        String lang = myScanner.nextLine();
            //int lang = "eng";
            while (lang.length() == 0){
                System.out.print("Invalid input. Please try again: ");
                lang = myScanner.nextLine();
            }

        //check to see if user would like to see list
        answer = lang;
        String Str2 = new String(answer);
        answer = Str2.toUpperCase();
        if(answer.equals("SEE LIST") == true){
                printLangCode();
                System.out.print("Enter language code: ");
                lang = myScanner.nextLine();
                while (lang.length() == 0){
                    System.out.print("Invalid input. Please try again: ");
                    lang = myScanner.nextLine();
                }
        }



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
            System.out.println("Successfully created book: '" + title + "' with a book ID of " + newID);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return newID;
    }

    private int deleteBook() {
        Scanner scanner = new Scanner(System.in);
        String usrInput = "";
        String answer = "";
        String input = "";
        String title = "";
       


        System.out.print("Do you know the book ID for the book you'd like to add? Enter 'Yes' or 'No': ");
        input = scanner.nextLine();
        input = input.toUpperCase();
        
        if (input.equals("YES") == false){

            if(input.equals("NO") == false){
                //System.out.println("failed this test");
                System.out.println("Sorry, " + input +" is not a valid answer \n Please try again");
                deleteBook();
            }
        }
        else if(answer.equals("NO") == true){
            System.out.print("To search title and copy bookID of desired book, enter the book's title: ");
            title = scanner.nextLine();
            if(title.length() == 0){
                System.out.print("Sorry, you're entry is not a valid title! Please try again.");
                deleteBook();
            } else {
                goodReads1(title);
            }
        } else{
            
        }
        System.out.print("Enter book ID: ");
        int bookID = scanner.nextInt();
        if(bookID != (int)(bookID)){
            System.out.print("Sorry, you're entry is not a valid bookID! Please try again. \n");
            deleteBook();
        }
        // if that book does not exist in the database
        if (bookIDCount(bookID) == 0){
            System.out.print("Sorry, book ID '" + bookID + "' does not exist in database. Please try again. \n");
            deleteBook();
        }
        
       
        System.out.println("Are you sure you woulld like to delete the following book permanantly?");
        printBook(bookID);
        scanner.nextLine();
        System.out.print("Enter 'Yes' to continue or 'No' to cancel: ");
        input = scanner.nextLine();
        input = input.toUpperCase();

        if (input.equals("YES") == false){

            if(input.equals("NO") == false){
                //System.out.println("failed this test");
                System.out.println("Sorry, " + input +" is not a valid answer \n Please try again");
                deleteBook();
            }
        }

        try {
            

            //delete from books
            String sql = "Delete from books where b_bookID = " + bookID;
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.execute();


            //delete from authored
            String sql2 = "Delete from authored where ad_bookID = " + bookID;
            PreparedStatement stmt2 = c.prepareStatement(sql2);
            stmt2.execute();
           

            //delete from publisher
            String sql4 = "Delete from publisher where p_bookID = " + bookID;
            PreparedStatement stmt4 = c.prepareStatement(sql4);
            stmt4.execute();


            // STEP: Clean-up environment
            c.commit();


            //delete from has read if exists
            if(hrIDCount(bookID) != 0){
                String sql6 = "Delete from hasread where hr_bookID = " + bookID;
                PreparedStatement stmt6 = c.prepareStatement(sql6);
                stmt6.execute();
                c.commit();
                stmt6.close();
            }
            //delete from favebooks
            if(fbIDCount(bookID) != 0){
                String sql5 = "Delete from favebooks where fb_bookID = " + bookID;
                PreparedStatement stmt5 = c.prepareStatement(sql5);
                stmt5.execute();
                c.commit();
                stmt5.close();
            }

            stmt2.close();
            stmt.close();
            stmt4.close();
            
            
            System.out.println("Successfully deleated book ID: " + bookID);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return 0;
    }

    private int authorCount(String inputAuthName) {
        System.out.println("Checking to see if author already exists...");
        //user chooses lang code from list by entering a number
        int authCount = 0;
        
        try {

            String sqlBID = "select count(a_name) " +
                            "from authors " +
                            "where a_name = " + "'" + inputAuthName + "'";

        
            ResultSet rsBID = c.prepareStatement(sqlBID).executeQuery();
            
            while (rsBID.next()) {
                authCount = rsBID.getInt("count(a_name)");
            }
            //System.out.println(authCount);
            rsBID.close();
            return authCount;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return authCount;
    }

    private int bookIDCount(int bookID) {
        System.out.println("Checking to see if bookID already exists...");
        //user chooses lang code from list by entering a number
        int bCount = 0;
        
        try {

            String sqlBID = "select count(b_bookID) " +
                            "from books " +
                            "where b_bookID = " + bookID;

        
            ResultSet rsBID = c.prepareStatement(sqlBID).executeQuery();
            
            while (rsBID.next()) {
                bCount = rsBID.getInt("count(b_bookID)");
            }

            rsBID.close();

           //if bCount == 0 then bookID does not exist
            return bCount;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return bCount;
    }
    private int fbIDCount(int bookID) {
        System.out.println("Checking to see if book already exists in favebooks...");
        //user chooses lang code from list by entering a number
        int bCount = 0;
        
        try {

            String sqlBID = "select count(*) " +
                            "from favebooks " +
                            "where fb_bookID =  " + bookID;

        
            ResultSet rsBID = c.prepareStatement(sqlBID).executeQuery();
            
            while (rsBID.next()) {
                bCount = rsBID.getInt("count(*)");
            }

            rsBID.close();

           //if bCount == 0 then bookID does not exist
            return bCount;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return bCount;
    }

    private int hrIDCount(int bookID) {
        System.out.println("Checking to see if book already exists in favebooks...");
        //user chooses lang code from list by entering a number
        int bCount = 0;
        
        try {

            String sqlBID = "select count(*) " +
                            "from hasread " +
                            "where hr_bookID =  " + bookID;

        
            ResultSet rsBID = c.prepareStatement(sqlBID).executeQuery();
            
            while (rsBID.next()) {
                bCount = rsBID.getInt("count(*)");
            }

            rsBID.close();

           //if bCount == 0 then bookID does not exist
            return bCount;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return bCount;
    }
    private int bookTitleCount(String title) {
        System.out.println("Checking to see if  already exists...");
        //user chooses lang code from list by entering a number
        int bCount = 0;
        
        try {


            Statement stmt = c.createStatement();
			String query = "SELECT count(*) " + "FROM books " + "where b_title LIKE " + "'%" + title + "%'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				bCount = rs.getInt("count(*)");
				
			}
			rs.close();
			stmt.close();

           //if bCount == 0 then bookID does not exist
            return bCount;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
        System.out.println("++++++++++++++++++++++++++++++++++");
        return bCount;
    }
    private void printLangCode() {
        
        //user chooses lang code from list by entering a number
        String lang = "";
        
        try {

            String sqlBID = "select distinct b_langcode " +
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
    private void printBook(int bookID) {
        
        
            try {
                Statement stmt = c.createStatement();
                String query = "SELECT * " + "FROM books " + "where b_bookID = " + bookID;
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    String b_bookID = rs.getString("b_bookID");
                    String b_title = rs.getString("b_title");
                    String b_langcode = rs.getString("b_langcode");
                    String b_numpages = rs.getString("b_numpages");
                    String b_isbn = rs.getString("b_isbn");
                    System.out.println(b_bookID + "\t" + b_title + "\t" + b_langcode + "\t" + b_numpages + "\t" + b_isbn + "\n");
                }
                rs.close();
                stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
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
        testingG sj = new testingG();
        
        sj.openConnection("data/grapp.sqlite");
        sj.userIntro();
        sj.closeConnection();
        //sj.createBookID();
        //sj.closeConnection();
    }
}
/* 

if inserting manually from personal then dont print:


    "Okay lets search the GooodReads database to find the book ID... 
    To search title and copy bookID of desired book, enter the book's title:""
    when goodReads1() is called
    also suggest user to compare isbn's of the book they would like to add.
    see if you could get goodReads1() to show author as well.


*/