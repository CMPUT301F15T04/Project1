package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.Managers.InvSearchManager;
import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;


/**
 * Lets you browse through friend inventory
 *
 * @author Preyanshu Kumar, Kynan Ly, Daniel Ren, Rupehra Chouhan, Blake Sakaluk
 * @version Part 4
 */
public class FriendInventoryActivity extends Activity {

    /** ArrayList of names of items */
    private ArrayList<String> itemNamesList;
    /** inventory is an object of class Inventory */
    private Inventory inventory;
    /** Adapter for item names */
    private ArrayAdapter<String> adapter;
    /*ListView to place these item names */
    private ListView listView;
    /* item ID */
    private int itemID;
    /* Item name */
    private String Name;
    /** A description of the Item */
    private String Description;
    /** The date when the game was released for purchase */
    private String ReleaseDate;
    /** Quality of the game*/
    private int Quality;
    /*IsPRivate */
    private Boolean IsPrivate;
    /*Platform of the game*/
    private int Platform;
    /** latitude of the place */
    private double latitude;
    /** longitude of the place */
    private double longitude;
    private SearchView search;


    /**
     * Grabs friend inventory items and displays them
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_inventory);
        inventory = InvSearchManager.showFriendInventory(UserManager.getFriend().getInventory());
        itemNamesList = inventory.getItemsNames();
        listView = (ListView) findViewById(R.id.friendInventoryListView);
        adapter = new ArrayAdapter<String>(this,R.layout.friendinventorylistviewtext,itemNamesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Name = UserManager.getFriend().getInventory().getItem(position).getName();
                Description = UserManager.getFriend().getInventory().getItem(position).getDescription();
                ReleaseDate = UserManager.getFriend().getInventory().getItem(position).getReleaseDate();
                itemID = UserManager.getFriend().getInventory().getItem(position).getItemid();
                latitude = UserManager.getFriend().getInventory().getItem(position).getLocation().getLatitude();
                longitude = UserManager.getFriend().getInventory().getItem(position).getLocation().getLongitude();

                final Intent intent = new Intent(FriendInventoryActivity.this, ViewItemActivity.class);
                intent.putExtra("name", Name);
                intent.putExtra("description", Description);
                intent.putExtra("releaseDate", ReleaseDate);
                intent.putExtra("index", position);
                intent.putExtra("Latitude", latitude);
                intent.putExtra("Longitude", longitude);
                intent.putExtra("itemId", itemID);
                startActivity(intent);
            }
        });

        search = (SearchView)findViewById(R.id.browseByTextualQuery);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search.clearFocus();
                inventory = InvSearchManager.showFriendInventory(UserManager.getFriend().getInventory());
                itemNamesList = inventory.getItemsNames();
                for (int i =0; i < itemNamesList.size();i++){
                    if(query.equals(itemNamesList.get(i).toLowerCase())){
                        Item item = inventory.getItem(i);
                        final Intent intent = new Intent(FriendInventoryActivity.this, ViewItemActivity.class);
                        intent.putExtra("name", item.getName());
                        intent.putExtra("description", item.getDescription());
                        intent.putExtra("releaseDate", item.getReleaseDate());
                        intent.putExtra("index", i);
                        intent.putExtra("Latitude", latitude);
                        intent.putExtra("Longitude", longitude);
                        intent.putExtra("itemId", itemID);
                        startActivity(intent);
                        finish();
                    }

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    /**
     * Called when user clicks DONE from on friend inventory page
     * @param v: done button view
     */
    public void frienddoneButtonClicked(View v){
        finish();
    }


    //==========Function needed for TestCases===========//

    public ListView getListView(){
        return (ListView) findViewById(R.id.friendInventoryListView);
    }

    //==========End Function needed for TestCases=======//

}//end FriendInventoryActivity
