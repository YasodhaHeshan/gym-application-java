
package Model;
import java.sql.*;

public class MyDBconnection {
    private static Connection con;
    public static Connection createDBconnection(){
         try{
        String dbloc ="jdbc:mysql://localhost/gym";
        con=DriverManager.getConnection(dbloc,"root","");}
       catch(SQLException e){
           System.err.println(e.getMessage());
       }
       return con;
    }
    
}
        
    

