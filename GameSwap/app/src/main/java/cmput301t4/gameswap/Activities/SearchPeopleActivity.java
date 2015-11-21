package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.Managers.UserListManager;
import cmput301t4.gameswap.Models.FriendList;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.Models.UsersList;
import cmput301t4.gameswap.R;

public class SearchPeopleActivity extends Activity {

    Button sButton;
    EditText traderEditText;
    String traderName;
    private SearchView search;
    private FriendList friendList;

    private UsersList usersList;
    private User user_1;
    private User user_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_people);

        friendList = FriendManager.getFriendlist();
        /**
         * including some more users in the app
         */
        user_1 = new User("kynan","kynan@ualberta.ca","Edmonton","780-999-1234",null);
        user_2 = new User("Blake","blake@ualberta.ca","Edmonton","780-444-1234",null);
        usersList = UserListManager.getInstance();
        if (usersList== null) {
            UserListManager.addUser(user_1);
            UserListManager.addUser(user_2);
        }
        /**
         * Code from - http://sampleprogramz.com/android/searchview.php
         */

        search = (SearchView) findViewById(R.id.searchView);
        search.setQueryHint("Search Trader");
        search.setIconifiedByDefault(false);

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Toast.makeText(getBaseContext(), "searching...", Toast.LENGTH_SHORT).show();
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getBaseContext(), query,
                //Toast.LENGTH_SHORT).show();
                findTrader(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void findTrader(String trader){
        traderName = search.getQuery().toString();
        if (FriendManager.getFriendlist().hasFriend(trader)) {
            Toast.makeText(getBaseContext(), "Already a friend", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(SearchPeopleActivity.this, AddFriendActivity.class);
            startActivity(intent);
        }
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_search_people, menu);
        return true;
    }

    @Override
    public void onPause(){
        super.onPause();
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
