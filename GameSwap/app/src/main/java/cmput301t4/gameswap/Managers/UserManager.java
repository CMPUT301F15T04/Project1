package cmput301t4.gameswap.Managers;

import cmput301t4.gameswap.User;

/**
 * Created by rupehra on 11/1/15.
 *
 *
 * functions needed to include: editUserName(), EditPhoneNumber(),EditEmail()
 * EditCity(), CreateTrader() ?, DeleteTrader() ?, AddFriend(), DeleteFriend()
 *
 *
 */
public class UserManager {

    private static User trader = null;

    //=====Singleton Code=====//

    static public User getTrader(){
        if(trader == null){
            trader = new User();
        }
        return trader;
    }//end getTrader

    public void editUserName(String username){
        trader.setUserName(username);
    }

    public void editPhoneNumber(String phoneNumber){
        trader.setUserPhoneNumber(phoneNumber);
    }

    public void editEmail(String email){
        trader.setUserEmail(email);
    }

    public void editCity(String city){
        trader.setUserCity(city);
    }

}//end UserManager
