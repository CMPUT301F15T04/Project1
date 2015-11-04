package cmput301t4.gameswap.Managers;

import cmput301t4.gameswap.Models.Trader;

/**
 * Created by rupehra on 11/1/15.
 *
 *
 * functions needed to include: editUserName(), EditPhoneNumber(),EditEmail()
 * EditCity(), CreateTrader() ?, DeleteTrader() ?, AddFriend(), DeleteFriend()
 *
 *
 */
public class TraderManager {


    static public Trader trader = new Trader("Rupehra","rupehra1@gmail.com","Edmonton","7809994444");
    //Singleton Code
     /*static public Trader getTraderInstance(){
        if(trader == null){
            trader = new Trader();
        }
        return trader;
    }*/

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






}
