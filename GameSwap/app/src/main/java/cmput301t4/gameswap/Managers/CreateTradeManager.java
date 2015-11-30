package cmput301t4.gameswap.Managers;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.Item;

/**
 * Created by Rupehra on 2015-11-29.
 */
public class CreateTradeManager {

    private static ArrayList<Item> OwnerSide = null;
    private static ArrayList<Item> FriendSide = new ArrayList<Item>();

    //=====Singleton Code=====//
    static public ArrayList<Item> getOwnerSide(){
        if (OwnerSide == null){
            OwnerSide = new ArrayList<Item>();
        }
        return OwnerSide;
    }//end getOwnerSide

    static public ArrayList<Item> getFriendSide(){
        if (FriendSide == null){
            FriendSide = new ArrayList<Item>();
        }
        return FriendSide;
    }//end getFriendSide

    public void addOwnerSide(Item item){
        OwnerSide.add(item);
    }//end addOwnerSide

    public void addFriendSide(Item item){
        FriendSide.add(item);
    }//end addFriendSide

    public int size(){
        return FriendSide.size();
    }

}
