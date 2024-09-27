
package Model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;


public class M_Payments {
    public void payment(String username,String amount,String month,String date){
       Connection connection = null;
    try {
        connection = MyDBconnection.createDBconnection();

        // Check if the username exists in the 'users' table
        PreparedStatement checkUserStmt = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?");
        checkUserStmt.setString(1, username);
        ResultSet resultSet = checkUserStmt.executeQuery();
        resultSet.next();
        int userCount = resultSet.getInt(1);

        if (userCount > 0) {
            // Check if the user has already paid for the same month
            PreparedStatement checkDuplicatePaymentStmt = connection.prepareStatement(
                "SELECT COUNT(*) FROM payments WHERE us_name = ? AND month = ?"
            );
            checkDuplicatePaymentStmt.setString(1, username);
            checkDuplicatePaymentStmt.setString(2, month);
            ResultSet duplicatePaymentResult = checkDuplicatePaymentStmt.executeQuery();
            duplicatePaymentResult.next();
            int duplicateCount = duplicatePaymentResult.getInt(1);

            if (duplicateCount == 0) {
                // Username exists, and no duplicate payment for the same month
                PreparedStatement insertPaymentStmt = connection.prepareStatement(
                    "INSERT INTO payments (us_name, amount, month, paydate) VALUES (?, ?, ?, ?)"
                );
                insertPaymentStmt.setString(1, username);
                insertPaymentStmt.setString(2, amount);
                insertPaymentStmt.setString(3, month);
                insertPaymentStmt.setString(4, date);

                int rowCount = insertPaymentStmt.executeUpdate();

                if (rowCount > 0) {
                    JOptionPane.showMessageDialog(null, "Payment Successful", "Payment", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "User has already paid for this month", "Payment", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Username does not exist", "Payment", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
}
