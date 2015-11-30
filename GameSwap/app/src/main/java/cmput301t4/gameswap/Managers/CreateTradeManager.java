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

    static public Inventory clearOwnerSide(){
        OwnerSide = new Inventory();
        return OwnerSide;
    }

    static public Inventory clearFriendSide(){
        FriendSide = new Inventory();
        return FriendSide;
    }

    static public Inventory setOwnerSide(Inventory inventory){
        OwnerSide = inventory;
        return inventory;
    }

    static public Inventory setFriendSide(Inventory inventory){
        FriendSide = inventory;
        return inventory;
    }

    static public Boolean OwnerSideContian(Item item){
        for(int i = 0; i < OwnerSide.size(); i++){
            if(item.getItemid() == (OwnerSide.getItem(i).getItemid())) {
                return true;
            }
        }
        return false;
    }

    static public Boolean FriendSideContain(Item item){
        for(int i = 0; i < FriendSide.size(); i++){
            if(item.getItemid() == (FriendSide.getItem(i).getItemid())){
                return true;
            }
        }
        return false;
    }

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

    static public void cancelTradeMade(){}

}
