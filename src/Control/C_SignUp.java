
package Control;
import Model.M_SignUp;

public class C_SignUp {
    public void signUpUser(String username,String f_name,String l_name,String password,int tno,int age,String diet_prefer,String diet_restric)
    {
        M_SignUp m1=new M_SignUp();
        m1.signUpUser(username, f_name, l_name, password, tno, age, diet_prefer, diet_restric);
    }
}
