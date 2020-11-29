import java.sql.*;
import java.io.*;
import java.util.Scanner;


public class test3 {

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