package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.R;

public class AddFriendActivity extends Activity {

    private Button addTrader;
    private String traderName;

    private AddFriendActivity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(getBaseContext(), "I am here", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_add_friend);

        addTrader = (Button) findViewById(R.id.addTraderButton);
        //Intent intent = getIntent();
        //Bundle b = intent.getExtras();


        traderName = (UserManager.getFriend().getUserName());
        TextView username = (TextView) findViewById(R.id.userNameTextView);
        username.setText(traderName);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_friend, menu);
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

    public void addTraderClicked(View v){
        System.out.println(UserManager.getTrader().getFriendList());
        UserManager.getTrader().getFriendList().addFriend(traderName);
        UserManager.saveUserLocally(this);
        ServerManager.saveUserOnline(UserManager.getTrader());
        FriendManager.addFriend(traderName);

        Toast.makeText(getBaseContext(), "Added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddFriendActivity.this,FriendProfileActivity.class);
        intent.putExtra("name", traderName);
        finish();
        startActivity(intent);

    }


}
