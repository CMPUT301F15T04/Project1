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

public class FriendProfileActivity extends Activity {

    private Button removeTraderButton;
    private TextView traderNameTextView;
    private String traderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        removeTraderButton = (Button)findViewById(R.id.removeTraderButton);

        traderNameTextView = (TextView) findViewById(R.id.traderNameTextView);

        //Intent intent = getIntent();
        //Bundle b = intent.getExtras();


        traderNameTextView.setText(UserManager.getFriend().getUserName());
        traderName = traderNameTextView.getText().toString();
        System.out.println(UserManager.getFriend().getUserName() + " testing location 3");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_profile, menu);
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

    @Override
    protected void onPause() {
        super.onPause();

        this.finish();
    }

    public void removeTraderButtonClicked(View view){
        int index;
        //index = FriendManager.getFriendIndex(traderName);
        index = UserManager.getTrader().getFriendList().getFriendIndex(traderName);
        System.out.println(traderName + " testing location 2");
        UserManager.getTrader().getFriendList().delFriend(index);
        ServerManager.saveUserOnline(UserManager.getTrader());
        Intent intent = new Intent(FriendProfileActivity.this,AddFriendActivity.class);
        //intent.putExtra("")
        startActivity(intent);
        finish();

    }


    public void tradeButtonClicked(View v){
        Toast.makeText(getBaseContext(), "Trade", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(FriendProfileActivity.this,OfferTradeActivity.class);
        startActivity(intent);
        finish();



    }
}
