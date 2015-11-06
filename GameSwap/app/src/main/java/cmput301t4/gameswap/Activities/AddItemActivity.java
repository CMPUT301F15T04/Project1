package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cmput301t4.gameswap.Adapters.ExpandableListAdapter;
import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.R;

//code taken from http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
public class AddItemActivity extends Activity {
    //create the unique list views and adapters for console, quality, and public and private
    ExpandableListAdapter consolelistAdapter;
    ExpandableListAdapter qualitylistAdapter;
    ExpandableListAdapter privatepubliclistAdapter;
    ExpandableListView consoleexpListView;
    ExpandableListView qualityexpListView;
    ExpandableListView privatepublicexpListView;

    //what will be held in each of the ELV
    List<String> consoleDataHeader;
    HashMap<String, List<String>> consoleDataChild;
    List<String> qualityDataHeader;
    HashMap<String, List<String>> qualityDataChild;
    List<String> privatepublicDataHeader;
    HashMap<String, List<String>> privatepublicDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //loads it upon saving
        super.onCreate(savedInstanceState);
        //sets it to the activity
        setContentView(R.layout.activity_add_item);
        //sets what the ELVs are
        consoleexpListView=(ExpandableListView) findViewById(R.id.platformchoice);
        qualityexpListView=(ExpandableListView) findViewById(R.id.qualityChoice);
        privatepublicexpListView=(ExpandableListView) findViewById(R.id.privatepublicChoice);
        //sets the data for all the ELVs
        prepareListData();
        //sets the unique adapters
        consolelistAdapter = new ExpandableListAdapter(this, consoleDataHeader, consoleDataChild);
        qualitylistAdapter = new ExpandableListAdapter(this, qualityDataHeader, qualityDataChild);
        privatepubliclistAdapter = new ExpandableListAdapter(this, privatepublicDataHeader, privatepublicDataChild);
        // setting list adapter
        consoleexpListView.setAdapter(consolelistAdapter);
        qualityexpListView.setAdapter(qualitylistAdapter);
        privatepublicexpListView.setAdapter(privatepubliclistAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
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

    private void prepareListData() {
        consoleDataHeader = new ArrayList<String>();
        consoleDataChild = new HashMap<String, List<String>>();
        qualityDataHeader = new ArrayList<String>();
        qualityDataChild = new HashMap<String, List<String>>();
        privatepublicDataHeader = new ArrayList<String>();
        privatepublicDataChild = new HashMap<String, List<String>>();

        //names of the dropdown menues
        consoleDataHeader.add("Console");
        qualityDataHeader.add("Quality");
        privatepublicDataHeader.add("Private/Public");


        //making list data
        List<String> consoles = new ArrayList<String>();
        consoles.add("Playstation 4");
        consoles.add("Xbox ONE");
        consoles.add("PC");
        consoles.add("Wii U");
        consoles.add("Nintendo 3DS");
        consoles.add("Playstation 3");
        consoles.add("Playstation Vita");
        consoles.add("Xbox 360");
        consoles.add("Nintendo Wii");
        consoles.add("Nintendo DS");
        consoles.add("Playstation 2");
        consoles.add("Xbox");
        consoles.add("Nintendo Gamecube");
        consoles.add("Game Boy Advance");
        consoles.add("Playstation Portable");
        consoles.add("Playstation");
        consoles.add("Nintendo 64");
        consoles.add("Gameboy");
        consoles.add("SNES");
        consoles.add("NES");

        List<String> quality = new ArrayList<String>();
        quality.add("5-Perfect Condition/Unopened/Download Code");
        quality.add("4-Opened No Scratches/Damage");
        quality.add("3-Light Scratches/Damage");
        quality.add("2-Decent Scratches/Damage");
        quality.add("1-Heavy Scratches/Damage");

        List<String> private_public = new ArrayList<String>();
        private_public.add("Public");
        private_public.add("Private");

        //showing it all
        consoleDataChild.put(consoleDataHeader.get(0), consoles);
        qualityDataChild.put(qualityDataHeader.get(0), quality);
        privatepublicDataChild.put(privatepublicDataHeader.get(0), private_public);
    }

    public void saveButtonClick(View view) {
        EditText titleEditText = (EditText) findViewById(R.id.gameTitle);
        EditText releaseEditText = (EditText) findViewById(R.id.releaseDateEdit);
        EditText descEditText = (EditText) findViewById(R.id.descriptionBox);
        int qual = qualityDataChild.get(qualityDataHeader.get(0)).indexOf(qualityexpListView.getSelectedItem());
        int console = consoleDataChild.get(consoleDataHeader.get(0)).indexOf(consoleexpListView.getSelectedItem());
        int priPub = privatepublicDataChild.get(privatepublicDataHeader.get(0)).indexOf(privatepublicexpListView.getSelectedItem());
        boolean isPrivate = (priPub == privatepublicDataChild.get(privatepublicDataHeader.get(0)).indexOf("Private"));
        InventoryManager.addItem(titleEditText.getText().toString(), releaseEditText.getText().toString(), isPrivate, qual, console, descEditText.getText().toString());
        this.finish();
    }

    public void cancelButtonClick(View view) {
        this.finish();
    }
}
