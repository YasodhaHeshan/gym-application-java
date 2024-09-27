
package Model;
import java.sql.*;
import javax.swing.JOptionPane;

public class M_SignUp {
     public void signUpUser(String username,String f_name,String l_name,String password,int tno,int age,String diet_prefer,String diet_restric){
       try{ 
    Statement st=MyDBconnection.createDBconnection().createStatement();
       
       int row_count=st.executeUpdate("Insert into users values ('"+username+"','"+f_name+"','"+l_name+"','"+password+"','"+tno+"','"+age+"','"+diet_prefer+"','"+diet_restric+"')");
       if (row_count >0){
           JOptionPane.showMessageDialog(null, "SignUp Successful","SignUp",JOptionPane.INFORMATION_MESSAGE);
       }
       }
       catch(SQLException e){
           System.err.println(e.getMessage());
       }
     }
}
