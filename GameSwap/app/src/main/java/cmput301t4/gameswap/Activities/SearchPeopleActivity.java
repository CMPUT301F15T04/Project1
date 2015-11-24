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
import cmput301t4.gameswap.Managers.UserManager;
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

    private SearchPeopleActivity activity = this;


    private User user_1;
    private User user_2;
    private User user_3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_people);

        friendList = UserManager.getFriendlist();
        /**
         * including some more users in the app
         */
        user_1 = new User("kynan".toLowerCase(),"kynan@ualberta.ca","Edmonton","780-999-1234",null);
        user_2 = new User("Blake".toLowerCase(),"blake@ualberta.ca","Edmonton","780-444-1234",null);
        user_3 = new User("Daniel".toLowerCase(),"dren@ualberta.ca","Edmonton","780-444-1244",null);

        int userListSize = UserListManager.getUserListSize();
        if (userListSize == 0) {
            UserListManager.addUser(user_1);
            UserListManager.addUser(user_2);
            UserListManager.addUser(user_3);
        }

        int size = UserListManager.getUserListSize();
        String sizeStr = String.valueOf(size);
        Toast.makeText(getBaseContext(),sizeStr , Toast.LENGTH_SHORT).show();


        /**
         * Code from - http://sampleprogramz.com/android/searchview.php
         */

        search = (SearchView) findViewById(R.id.searchView);
        search.setQueryHint("Search Trader");
        search.setIconifiedByDefault(false);

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                findTrader(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void findTrader(String trader){
        traderName = search.getQuery().toString().toLowerCase();

        if (UserManager.getTrader().getFriendList().hasFriend(trader)) {
            Intent intent = new Intent(SearchPeopleActivity.this, FriendProfileActivity.class);
            intent.putExtra("name", traderName.toLowerCase());
            activity.finish();
            startActivity(intent);

        } else if(UserListManager.hasUserName(traderName)) {
                Intent intent2 = new Intent(SearchPeopleActivity.this, AddFriendActivity.class);
                intent2.putExtra("name",traderName.toLowerCase());
                search.clearChildFocus(search);
                activity.finish();
                startActivity(intent2);

        } else {
            Toast.makeText(getBaseContext(), "NO user exist", Toast.LENGTH_SHORT).show();
        }



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
