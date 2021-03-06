package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
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
        itemNamesList = new ArrayList<String>();
        inventory = InvSearchManager.showFriendInventory(UserManager.getFriend().getInventory());
        itemNamesList = inventory.getItemsNames();
        listView = (ListView) findViewById(R.id.friendInventoryListView);
        adapter = new ArrayAdapter<String>(this,R.layout.friendinventorylistviewtext,itemNamesList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listView.getItemAtPosition(position).toString();
                for (int i =0; i<inventory.size();i++){
                    if(inventory.getItem(i).getName().toLowerCase().equals(name.toLowerCase())){
                        position = i;
                        break;
                    }
                }
                Name = inventory.getItem(position).getName();
                Description = inventory.getItem(position).getDescription();
                ReleaseDate = inventory.getItem(position).getReleaseDate();
                itemID = inventory.getItem(position).getItemid();
                latitude =inventory.getItem(position).getLocation().getLatitude();
                longitude = inventory.getItem(position).getLocation().getLongitude();
                Platform = inventory.getItem(position).getPlatform();
                Quality = inventory.getItem(position).getQuality();
                IsPrivate = inventory.getItem(position).getIsPrivate();

                final Intent intent = new Intent(FriendInventoryActivity.this, ViewItemActivity.class);
                intent.putExtra("name", Name);
                intent.putExtra("description", Description);
                intent.putExtra("releaseDate", ReleaseDate);
                intent.putExtra("index", position);
                intent.putExtra("Latitude", latitude);
                intent.putExtra("Longitude", longitude);
                intent.putExtra("itemId", itemID);
                intent.putExtra("platform",Platform);
                intent.putExtra("quality",Quality);
                intent.putExtra("private", IsPrivate);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_friend_inventory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.byPlatform) {
            //searchFriend = Boolean.FALSE;
            Toast toast = Toast.makeText(getBaseContext(),"by Platfom", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Intent intent = new Intent(FriendInventoryActivity.this,SearchByPlatformActivity.class);
            startActivity(intent);
            //switchSearch(searchFriend);
        }
        else if(id == R.id.byQuality){
            //searchFriend = Boolean.TRUE;
            Toast toast = Toast.makeText(getBaseContext(),"by quality", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Intent intent = new Intent(FriendInventoryActivity.this,SearchByQualityActivity.class);
            startActivity(intent);
            //switchSearch(searchFriend);
        }
        return super.onOptionsItemSelected(item);
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
