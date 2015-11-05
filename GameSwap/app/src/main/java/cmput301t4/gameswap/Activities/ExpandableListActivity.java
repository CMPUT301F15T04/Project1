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
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_entry);
        expListView=(ExpandableListView) findViewById(R.id.platformchoice);
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
    }


}
