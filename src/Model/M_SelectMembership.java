
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;


public class M_SelectMembership {
    public void selectMembership(String username,String membership_name){
       try {
            Connection connection = MyDBconnection.createDBconnection(); // Assuming MyDBconnection returns a valid connection
            String checkUsernameQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
            String checkMembershipQuery = "SELECT COUNT(*) FROM selectmembership WHERE use_name = ?";
            String insertMembershipQuery = "INSERT INTO selectmembership (use_name, membership_name) VALUES (?, ?)";

            // First, check if the username exists in the users table
            PreparedStatement checkUsernameStatement = connection.prepareStatement(checkUsernameQuery);
            checkUsernameStatement.setString(1, username);
            ResultSet usernameResult = checkUsernameStatement.executeQuery();

            if (usernameResult.next() && usernameResult.getInt(1) > 0) {
                // Check if the user has already selected a membership
                PreparedStatement checkMembershipStatement = connection.prepareStatement(checkMembershipQuery);
                checkMembershipStatement.setString(1, username);
                ResultSet membershipResult = checkMembershipStatement.executeQuery();

                if (membershipResult.next() && membershipResult.getInt(1) > 0) {
                    // The user has already selected a membership
                    JOptionPane.showMessageDialog(null, "User has already selected a membership.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // The user has not selected a membership, so proceed with the insertion
                    PreparedStatement insertMembershipStatement = connection.prepareStatement(insertMembershipQuery);
                    insertMembershipStatement.setString(1, username);
                    insertMembershipStatement.setString(2, membership_name);

                    int row_count = insertMembershipStatement.executeUpdate();

                    if (row_count > 0) {
                        JOptionPane.showMessageDialog(null, "Successfully Select Membership", "SelectMembership", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                // The username does not exist in the users table
                JOptionPane.showMessageDialog(null, "Username does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }
    }
     
}
