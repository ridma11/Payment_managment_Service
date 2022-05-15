package com;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
    //A common method to connect to the DB
	 private Connection connect() 
     { 
         Connection con = null; 
         try
     { 
         Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/item", "root", ""); 
} 
    catch (Exception e) 
	        {e.printStackTrace();} 
    return con; 
    } 
	
    public String addpayment(String paymentID, String accNo, String userName,String amount, String description) 
    { 
        String output = ""; 
            try
            { 
                Connection con = connect(); 
                if (con == null) 
                    {	
                        return "Error while connecting to the database for inserting.";
                    } 
                
                // create a prepared statement
                String query = "insert into  payment_management(`paymentID`,`accNo`,`userName`,`amount`,`description`)"
                + " values (?, ?, ?, ?, ?)"; 
                PreparedStatement preparedStmt = con.prepareStatement(query); 
                // binding values
                preparedStmt.setInt(1, 0); 
                preparedStmt.setString(2, accNo); 
                preparedStmt.setString(3, userName);
                preparedStmt.setString(4, amount); 
                preparedStmt.setString(5, description); 
                
               // execute the statement
                preparedStmt.execute(); 
                con.close(); 
                //output = "New bill Create successfully"; 
                
                String newBills = readPayment();
                output = "{\"status\":\"success\", \"data\": \"" + 
           			 newBills + "\"}"; 
                
            } 
            catch (Exception e) 
            { 

            output = "{\"status\":\"error\", \"data\": \"Error while inserting the bill.\"}"; 
            System.err.println(e.getMessage()); 
            } 
        return output; 
    } 
   
    public String readPayment() 
    { 
    String output = ""; 
    try
    { 
        Connection con = connect(); 
        if (con == null) 
        {
        	return "Error while connecting to the database for reading."; } 
        // Prepare the html table to be displayed
        output = "<table border='1'><tr>"
        		+ "<th>Paymnt ID</th>"
        		+ "<th>Acc no</th>" +
                "<th>User name</th>" + 
                "<th>Amount</th>"+
                "<th>Description</th> </tr>"; 
        
        
        
        /**Use join query connect tree table */
        String query = "SELECT * FROM  payment_management" ; 
        Statement stmt = con.createStatement(); 
        ResultSet rs = stmt.executeQuery(query); 
        // iterate through the rows in the result set
        while (rs.next()) 
        { 
            String paymentID = rs.getString("paymentID"); 
            String accNo = rs.getString("accNo"); 
            String userName = rs.getString("userName"); 
            String amount  = rs.getString("amount");
            String description = rs.getString("description"); 
            
         
           
            // Add into the html table
            output += "<tr><td>" + paymentID + "</td>"; 
            output += "<td>" + accNo + "</td>"; 
            output += "<td>" + userName + "</td>";
            output += "<td>" + amount + "</td>"; 
            output += "<td>" + description + "</td>"; 
          
 
            		 
            // buttons
        			output += "<td><input name='btnUpdate' "
        					+ "type='button' value='Update' "
        					+ " class='btnUpdate btn btn-secondary'></td>"
        					+ "<td><input name='btnRemove' "
        					+ "type='button' value='Remove' "
        					+ "class='btnRemove btn btn-danger' "
        					+ "data-id='"
        			 + paymentID + "'>" + "</td></tr>";
        } 
        con.close(); 
        // Complete the html table
        output += "</table>"; 
    } 
	catch (Exception e) 
	{ 
		output = "Error while reading the notice."; 
		System.err.println(e.getMessage()); 
	} 
	return output; 
	} 
    
   
    
    
    
    public String updatePayment(String paymentID, String accNo, String userName,String amount, String description)  
    
    { 
        String output = ""; 
    try
    { 
        Connection con = connect(); 
        if (con == null) 
    { 
	return "Error while connecting to the database for updating."; } 
    // create a prepared statement
    String query = "UPDATE payment_management SET accNo=?,userName=?"
    		+ ",amount=?,description=? WHERE paymentID=?"; 
    PreparedStatement preparedStmt = con.prepareStatement(query); 
        // binding values
    
    
    preparedStmt.setString(1, accNo); 
    preparedStmt.setString(2, userName);
    preparedStmt.setString(3, amount); 
    preparedStmt.setString(4, description);  
    preparedStmt.setInt(5, Integer.parseInt(paymentID));
    
       

    
    // execute the statement
    preparedStmt.execute(); 
    con.close(); 
    String newAppointment = readPayment();
  	output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";
  }
  catch (Exception e)
  {
  	output = "{\"status\":\"error\", \"data\": \"Error while deleting the appointment details. \"}";
  	System.err.println(e.getMessage());
  }
        return output; 
    } 

    

    public String deletePayment(String paymentID) 
    { 
	    String output = ""; 
	    try
	    { 
	    	Connection con = connect(); 
		    if (con == null) 
		    	{return "Error while connecting to the database for deleting."; } 
			    // create a prepared statement
			    String query = "delete from payment_management where paymentID=?"; 
			    PreparedStatement preparedStmt = con.prepareStatement(query); 
			    // binding values
			    //preparedStmt.setString(1, paymentID);
			    preparedStmt.setInt(1, Integer.parseInt(paymentID));
			    // execute the statement
			    preparedStmt.execute(); 
			    con.close(); 
			 
	   
    String newAppointment = readPayment();
	output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";
}
catch (Exception e)
{
	output = "{\"status\":\"error\", \"data\": \"Error while deleting the appointment details. \"}";
	System.err.println(e.getMessage());
}

return output;
}

}

