
package Control;
import Model.M_Payments;
import java.util.Date;

public class C_Payments {
    public void payment(String username,String amount,String month,String date){
        M_Payments m1=new M_Payments();
        m1.payment(username, amount, month, date);
    }
}
