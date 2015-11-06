package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cmput301t4.gameswap.Adapters.ExpandableListAdapter;
import cmput301t4.gameswap.R;


public class ExpandableListActivity extends Activity {
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
    protected void onCreate(Bundle savedInstanceState){
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

}