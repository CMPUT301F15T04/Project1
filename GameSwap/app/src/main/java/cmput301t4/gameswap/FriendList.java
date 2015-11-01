package cmput301t4.gameswap;

import java.util.ArrayList;

/**
 * Created by sakaluk on 11/1/15.
 */
public class FriendList {
    ArrayList<Trader> friendCollection;

    public void AddFriend(int FriendID) {
        // TODO: Pull Trader from server by ID
        Trader friend = new Trader();
        friendCollection.add(friend);
    }

    public void DeleteFriend(int FriendID) {
        for(int i = 0; i < friendCollection.size(); i++) {
            if(friendCollection.get(i).getID() == friendID) {
                friendCollection.remove(friend);
            }
        }
    }
}
