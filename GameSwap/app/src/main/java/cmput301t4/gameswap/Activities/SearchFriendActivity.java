package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cmput301t4.gameswap.R;

public class SearchFriendActivity extends Activity {

    private ArrayAdapter<String> adapter;
    private ListView friendListView;
    private ArrayList<String> names;

    protected int friendListViewItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);

        friendListView = (ListView) findViewById(R.id.listView);
        names = new ArrayList<String>();
        names.add("Rupehra");
        names.add("Kittu");
        // names.addAll(data);
        adapter = new ArrayAdapter<String>(this, R.layout.listviewtext, names);
        friendListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        //http://stackoverflow.com/questions/21329132/android-custom-dropdown-popup-menu
        //http://stackoverflow.com/questions/7201159/is-using-menuitem-getitemid-valid-in-finding-which-menuitem-is-selected-by-use
        //http://stackoverflow.com/questions/4554435/how-to-get-the-index-and-string-of-the-selected-item-in-listview-in-android
        friendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View childView, int position, long id) {
                Toast.makeText(getBaseContext(), "Yay", Toast.LENGTH_SHORT).show();

                PopupMenu popupMenu = new PopupMenu(SearchFriendActivity.this, childView);
                popupMenu.getMenuInflater().inflate(R.menu.friend_popup, popupMenu.getMenu());

                friendListViewItemPosition = position;

                // onPrepareOptionsMenu(popupMenu.getMenu());

            }

        });
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_friend, menu);

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
