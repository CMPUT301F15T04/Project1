package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.InvSearchManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

public class SearchByPlatformActivity extends Activity {

    /** The spinner to choose the console */
    private Spinner consoleSpinner;
    /** Adapter for listview */
    private ArrayAdapter<String> adapter;
    /** Listview for items*/
    private ListView listView;
    /** List of items to display in the listView */
    private ArrayList<String> itemNames;
    /** Friend's inventory */
    private Inventory inventory;
    /** Value of item platform in console */
    private Integer consoleValue;
    /** Indices of the items in inventory */
    private ArrayList<Integer> indices;
    /** Referring this to the name of this activity*/
    private SearchByPlatformActivity activity = this;
    /** Index of the selected item */
    private Integer index;
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_platform);
        consoleSpinner = (Spinner) findViewById(R.id.PlatformSpinner);
        inventory = InvSearchManager.showFriendInventory(UserManager.getFriend().getInventory());
        listView = (ListView) findViewById(R.id.PlatformlistView);
        itemNames = new ArrayList<String>();
        indices = new ArrayList<Integer>();
        consoleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemNames = new ArrayList<String>();
                indices = new ArrayList<Integer>();
                consoleValue = consoleSpinner.getSelectedItemPosition();
                for (int i =0; i< inventory.size();i++){
                    Item item = inventory.getItem(i);
                    if ((item.getPlatform().equals(consoleValue)) && (item.getIsPrivate().equals(false))) {
                        indices.add(i);
                        itemNames.add(inventory.getItem(i).getName());
                    }
                }
                displayItems();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = indices.get(position);
                Name = UserManager.getFriend().getInventory().getItem(index).getName();
                Description = UserManager.getFriend().getInventory().getItem(index).getDescription();
                ReleaseDate = UserManager.getFriend().getInventory().getItem(index).getReleaseDate();
                itemID = UserManager.getFriend().getInventory().getItem(index).getItemid();
                latitude = UserManager.getFriend().getInventory().getItem(index).getLocation().getLatitude();
                longitude = UserManager.getFriend().getInventory().getItem(index).getLocation().getLongitude();
                Platform = UserManager.getFriend().getInventory().getItem(index).getPlatform();
                Quality = UserManager.getFriend().getInventory().getItem(index).getQuality();

                final Intent intent = new Intent(SearchByPlatformActivity.this, ViewItemActivity.class);
                intent.putExtra("name", Name);
                intent.putExtra("description", Description);
                intent.putExtra("releaseDate", ReleaseDate);
                intent.putExtra("index", position);
                intent.putExtra("Latitude", latitude);
                intent.putExtra("Longitude", longitude);
                intent.putExtra("itemId", itemID);
                intent.putExtra("platform",Platform);
                intent.putExtra("quality",Quality);
                startActivity(intent);
            }

        });
    }

    /**
     * update the listview
     */
    public void displayItems(){
        adapter = new ArrayAdapter<String>(this,R.layout.searchbyplatform,itemNames);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_by_platform, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
