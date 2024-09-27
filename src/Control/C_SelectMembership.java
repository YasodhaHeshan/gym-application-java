
package Control;

import Model.M_SelectMembership;
public class C_SelectMembership {
   public void selectMembership(String username,String membership_name){
       M_SelectMembership m1=new M_SelectMembership();
       m1.selectMembership(username, membership_name);
   }
}
