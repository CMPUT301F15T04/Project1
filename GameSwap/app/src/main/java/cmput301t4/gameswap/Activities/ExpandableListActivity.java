package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;

import cmput301t4.gameswap.R;

/**
 * Created by preyanshu on 11/5/15.
 */
public class ExpandableListActivity extends Activity {
    ExpandableListAdapter consolelistAdapter;
    ExpandableListAdapter qualitylistAdapter;
    ExpandableListAdapter privatepubliclistAdapter;
    ExpandableListView consoleexpListView;
    ExpandableListView qualityexpListView;
    ExpandableListView privatepublicexpListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_entry);
        consoleexpListView=(ExpandableListView) findViewById(R.id.platformchoice);
        qualityexpListView=(ExpandableListView) findViewById(R.id.qualityChoice);
        privatepublicexpListView=(ExpandableListView) findViewById(R.id.privatepublicChoice);
        prepareListData();
        consolelistAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        qualitylistAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        privatepubliclistAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        consoleexpListView.setAdapter(consolelistAdapter);
    }
    private void prepareListData() {
        
    }

}
