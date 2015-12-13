package cmput301t4.gameswap.Activities;

/**
 * Activity that lets user browse friend inventory by quality
 *
 * @author Rupehra Chouhan, Preyanshu Kumar, Kynan Ly, Daniel Ren, Blake Sakaluk
 * @version Part 5
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.InvSearchManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

public class SearchByQualityActivity extends Activity {

    /** The spinner to choose the console */
    private Spinner qualitySpinner;
    /** Adapter for listview */
    private ArrayAdapter<String> adapter;
    /** Listview for items*/
    private ListView listView;
    /** List of items to display in the listView */
    private ArrayList<String> itemNames;
    /** Friend's inventory */
    private Inventory inventory;
    /** Value of item platform in console */
    private Integer qualityValue;
    /** Referring this to the name of this activity*/
    private SearchByQualityActivity activity = this;
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
        setContentView(R.layout.activity_search_by_quality);
        qualitySpinner = (Spinner) findViewById(R.id.qualitySpinner);
        inventory = InvSearchManager.showFriendInventory(UserManager.getFriend().getInventory());
        listView = (ListView) findViewById(R.id.qualitylistView);
        itemNames = new ArrayList<String>();
        qualitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemNames = new ArrayList<String>();
                qualityValue = qualitySpinner.getSelectedItemPosition();
                for (int i = 0; i < inventory.size(); i++) {
                    Item item = inventory.getItem(i);
                    if ((item.getQuality().equals(qualityValue)) && item.getIsPrivate().equals(false)) {
                        itemNames.add(inventory.getItem(i).getName());
                    }
                }
                displayItems();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //when the item is clicked, a new screen opens which displays the details of the item
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
                latitude = inventory.getItem(position).getLocation().getLatitude();
                longitude = inventory.getItem(position).getLocation().getLongitude();
                Platform =  inventory.getItem(position).getPlatform();
                Quality = inventory.getItem(position).getQuality();

                final Intent intent = new Intent(SearchByQualityActivity.this, ViewItemActivity.class);
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
        adapter = new ArrayAdapter<String>(this,R.layout.searchbyquality,itemNames);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_search_by_quality, menu);
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
