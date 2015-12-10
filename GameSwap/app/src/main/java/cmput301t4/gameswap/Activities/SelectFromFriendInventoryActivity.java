package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.Managers.InvSearchManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.R;

/**
 * Class that let user pick items from friend inventory that they want in trade
 *
 * @author Preyanshu Kumar, Kynan Ly, Daniel Ren, Rupehra Chouhan, Blake Sakaluk
 * @version Part 4
 */
public class SelectFromFriendInventoryActivity extends Activity {

    /** ArrayList of item names */
    private ArrayList<String> itemNamesList;
    /** friend inventory*/
    private Inventory inventory;
    /** adapter for friend inventory */
    private ArrayAdapter<String> adapter;
    /** ListView for friend inventory */
    private ListView listView;

    /**
     * Called whent the activity is first created
     * Grabs friend inventory items and displays them
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_from_friend_inventory);
        inventory = InvSearchManager.showFriendInventory(UserManager.getFriend().getInventory());
        itemNamesList = inventory.getItemsNames();
        listView = (ListView) findViewById(R.id.friendInventoryListView);
        adapter = new ArrayAdapter<String>(this,R.layout.friendinventorylistviewtext,itemNamesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(CreateTradeManager.FriendSideContain(inventory.getItem(position)) == Boolean.FALSE){
                    Toast.makeText(getBaseContext(),inventory.getItem(position).getName() + " Added to Trade", Toast.LENGTH_SHORT).show();
                    CreateTradeManager.addFriendSide(inventory.getItem(position));
                    CreateTradeManager.updateFriendSiderName();
                } else  {
                    Toast.makeText(getBaseContext(), "Already Added to list", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * Called when user is done selecting items from friend inventory
     * @param v: done button view
     */
    public void frienddoneButtonClicked(View v){
        finish();
    }

    //=============Function needed for Testcases================//

    public ListView getListView(){
        return listView;
    }

    public Button getDone(){
        return (Button) findViewById(R.id.frienddone);
    }

    //=============End Function needed for Testcases============//


}
