package cmput301t4.gameswap.Managers;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;

/**
 * CreateTradeManager handles  the process of creating a trade
 * @author Rupehra Chouhan, Blake Sakaluk, Preyanshu Kumar, Kynan Ly, Daniel Ren
 * @version Part 4
 */
public class CreateTradeManager {

    /** Initializing owner inventory to null */
    private static Inventory OwnerSide = null;
    /** Initializing friend inventory to null*/
    private static Inventory FriendSide = null;
    /** This list contains the names of all the owners in trades */
    private static ArrayList<String> ownerSideName;
    /** This list contains the names of all the friends in trades */
    private static ArrayList<String> friendSideName;

    //=====Singleton Code=====//

    /**
     * Gets owner inventory
     * @return owner inventory
     */
    static public Inventory getOwnerSide(){
        if (OwnerSide == null){
            OwnerSide = new Inventory();
        }
        return OwnerSide;
    }

    /**
     * Gets friend inventory
     * @return friend inventory
     */
    static public Inventory getFriendSide(){
        if (FriendSide == null){
            FriendSide = new Inventory();
        }
        return FriendSide;
    }

    /**
     * Gets all the owners
     * @return name of all the owners
     */
    static public ArrayList<String> getOwnerSideName(){
        if (ownerSideName == null){
            ownerSideName = new ArrayList<String>();
        }
        return ownerSideName;
    }

    /**
     * Gets all the friends
     * @return name of friends with whom the owner is trading
     */
    static public ArrayList<String> getFriendSideName(){
        if (friendSideName == null){
            friendSideName = new ArrayList<String>();
        }
        return friendSideName;
    }

    //=====End Singleton Code======//

    /**
     * Clears the owner inventory
     * @return updated owner inventory
     */
    static public Inventory clearOwnerSide(){
        OwnerSide = new Inventory();
        updateOwnerSideName();
        return OwnerSide;
    }

    /**
     * Clears the friend inventory
     * @return updated friend inventory
     */
    static public Inventory clearFriendSide(){
        FriendSide = new Inventory();
        updateFriendSiderName();
        return FriendSide;
    }

    /**
     * Setting owner name
     * @param previous name of the previous owner
     */
    static public void setOwnerName(ArrayList<String> previous){
        ownerSideName = previous;
    }

    /**
     * Setts borrower name
     * @param previous name of the previous borrower
     */
    static public void setBorrowerName(ArrayList<String> previous){
        friendSideName = previous;
    }

    /**
     * Sets owner inventory
     * @param inventory inventory contains items from owner inventory to trade with
     * @return inventory
     */
    static public Inventory setOwnerSide(Inventory inventory){
        OwnerSide = inventory;
        return inventory;
    }

    /**
     * Sets friend inventory
     * @param inventory inventory contains items from friend inventory to trade for
     * @return friend inventory
     */
    static public Inventory setFriendSide(Inventory inventory){
        FriendSide = inventory;
        return inventory;
    }

    /**
     * Checks if the owner has that item in their inventory
     * @param item item that we are looking for
     * @return true if owner has that item
     *                  returns false otherwise
     */
    static public Boolean OwnerSideContian(Item item){
        for(int i = 0; i < getOwnerSide().size(); i++){
            if(item.getItemid() == (getOwnerSide().getItem(i).getItemid())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the friend has that item in their inventory
     * @param item item that we are looking for
     * @return true if friend has that item
     *              returns false otherwise
     */
    static public Boolean FriendSideContain(Item item){
        for(int i = 0; i < getFriendSide().size(); i++){
            if(item.getItemid() == ( getFriendSide().getItem(i).getItemid())){
                return true;
            }
        }
        return false;
    }

    /**
     *Adds an item to owner inventory to trade for
     * @param item item
     */
    static public void addOwnerSide(Item item){
        getOwnerSide().add(item);
    }

    /**
     * Adds an item to friend inventory to trade with
     * @param item item
     */
    static public void addFriendSide(Item item){
        getFriendSide().add(item);
    }

    /**
     * Updates owner items in their inventory to trade for
     */
    static public void updateOwnerSideName(){
        ownerSideName = getOwnerSide().getItemsNames();
    }

    /**
     * Updates friend items in their inventory to trade with
     */
    static public void updateFriendSiderName(){
        friendSideName = getFriendSide().getItemsNames();
    }

}
