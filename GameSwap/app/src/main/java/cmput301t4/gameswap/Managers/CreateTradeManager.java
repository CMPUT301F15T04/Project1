package cmput301t4.gameswap.Managers;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;

/**
 * Created by Rupehra on 2015-11-29.
 */
public class CreateTradeManager {

    private static Inventory OwnerSide = null;
    private static Inventory FriendSide = null;

    //=====Singleton Code=====//
    static public Inventory getOwnerSide(){
        if (OwnerSide == null){
            OwnerSide = new Inventory();
        }
        return OwnerSide;
    }//end getOwnerSide

    static public Inventory getFriendSide(){
        if (FriendSide == null){
            FriendSide = new Inventory();
        }
        return FriendSide;
    }//end getFriendSide

    static public void addOwnerSide(Item item){
        getOwnerSide().add(item);
    }//end addOwnerSide

    static public void addFriendSide(Item item){
        getFriendSide().add(item);
    }//end addFriendSide

    static public int OwnerSize(){
        return OwnerSide.size();
    }

    static public int FriendSize() {return FriendSide.size();}

}
