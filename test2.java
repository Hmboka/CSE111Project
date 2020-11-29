import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class test2 {
//public class test2 extends test{    //use when testing if we want to use sperate file if not the queires are at the bottom of this file
	public static Connection connect;
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

        userintro();
		
	}

	public static void userintro(){
        System.out.println("Welcome to You Personal Library Tracker");
        System.out.println("Sponsored by GoodReads");
		System.out.println("Enter 1 if you like to open GoodReads Database. Enter 2 if you like to open your Personal Library. Enter 0 to quit. ");
		System.out.println("1. GoodReads Library Database");
		System.out.println("2. Personal Library Database");
		System.out.println("0. Quit");
		int input = scan.nextInt();
		if(input < 0 || input > 2){
			System.out.println("You have entered an invalid input, please try again...");
			System.out.println();
			userintro();
		}
		if(input == 1){
			System.out.print("GoodReads Library Database ");
			goodreads();
		}
		if(input == 2){
			System.out.print("Personal Library Database ");
			personal();
		}
		
		else{
			System.out.println("Exiting Library...");
			System.out.println("Goodbye!");
			System.out.println("End of Library Program");
			end();
		}
		
	}
	
	public static void end(){
		scan.close();
		System.exit(0);
	}

	
	public static void goodreads(){
		System.out.println("This is your Personal Library Tracker");
		System.out.println("What would you like to do?");
		System.out.println("1. Search books from general GoodReads Database?");
		System.out.println("2. Would you like to add, delete, or edit books from the general GoodReads Database?");
		System.out.println("3. Would you like to add a book from this general GoodReads DatabaseFavorite Database?");
		System.out.println("4. Would you like to search books written by on auhtors?");
		System.out.println("5. Would you like to search books with a certain rating?");
		System.out.println("6. What book has a rating of at least (your input of rating vaule), written in (insert language), and has at least (insert number of pages) pages?");
		System.out.println("0. Back");

		int selectInput = scan.nextInt();
		if(selectInput < 0 || selectInput > 6){
			System.out.println("Invalid Entry. Please enter an option listed above.");
			goodreads();
		}
		if(selectInput == 1){
			System.out.println("Please enter the name of the book?");
			scan.nextLine();
			String inputBookName = scan.nextLine();
			goodreads1(inputBookName);

		}

		else if(selectInput == 6){
			//userOption6();
		}
		else{//option 5 goes back to choose user or admin
			userintro();
		}
		goodreads();
		
	}
	

	public static void personal(){
		System.out.println("This is your Personal Library Tracker");
		System.out.println("What would you like to do?");
		System.out.println("1. Search books from general GoodReads Database?");
		System.out.println("2. Would you like to add, delete, or edit books from the general GoodReads Database?");
		System.out.println("3. Search books from your Favorite Database?");
		System.out.println("4. Would you like to add, delete, or edit your Favorite Database?");
		System.out.println("5. Would you like to search books based on Authors from either Database?");
		System.out.println("6. Would you like to search books based on Rating from either Database?");
		System.out.println("0. Back");
		int selectInput = scan.nextInt();
		if(selectInput < 0 || selectInput > 6){
			System.out.println("Invalid Entry. Please enter an option listed above.");
			personal();
		}
		if(selectInput == 1){
			System.out.println("Please enter the name of the book?");
			scan.nextLine();
			String inputBookName = scan.nextLine();

		}

		else if(selectInput == 6){
			
		}
		else{
			userintro();
		}
		personal();
		
	}

// this is aprt is same as whats in test3 but couldnt figure it out so moved it here but running into the same problems


	public static void goodreads1(String inputBookName){
		try{
			Connection connect;
			Statement stmt;
			connect = DriverManager.getConnection("jdbc:sqlite");
			stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(
					"Select b_bookID, b_title, b_langcode, b_numpages, b_isbn "+
					"From books " +
					"where b_title = '" + inputBookName +"';");
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
			connect.close();
			System.out.println();
		}
		catch(Exception e){
			//System.out.print("There was a problem connecting to the database...");
			System.err.println(e.getMessage());
			System.out.println("Program Ending...");
			System.exit(0);
		}
	}



}
