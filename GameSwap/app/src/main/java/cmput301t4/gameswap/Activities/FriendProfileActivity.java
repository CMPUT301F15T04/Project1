package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.R;

/**
 * Views friend's profile and their information
 *
 * @author Preyanshu Kumar, Kynan Ly, Daniel Ren, Rupehra Chouhan, Blake Sakaluk
 * @version Part 4
 */
public class FriendProfileActivity extends Activity {

    /** Button for if the user is a friend*/
    private Button FriendStatusButton;
    /** Button to view friend inventory */
    private Button friendInventory;
    /** Button to trade with friend */
    private Button friendTrade;
    /** TextView for trader name */
    private TextView traderNameTextView;
    /** TextView for trader city */
    private TextView traderCityTextView;
    /** TextView for trader phone */
    private TextView traderPhoneTextView;
    /** TextView for trader e-mail */
    private TextView traderEmailTextView;
    /** trader name  */
    private String traderName;
    /** IsFriend status in boolean */
    private Boolean isFriend;


    /**
     *Sets the friend profile page with their information
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        isFriend = b.getBoolean("isfriend");
        traderNameTextView = (TextView) findViewById(R.id.traderNameTextView);
        traderCityTextView = (TextView) findViewById(R.id.traderCityTextView);
        traderPhoneTextView = (TextView) findViewById(R.id.traderPhoneTextView);
        traderEmailTextView = (TextView) findViewById(R.id.traderEmailTextView);
        FriendStatusButton = (Button)findViewById(R.id.removeTraderButton);
        friendInventory = (Button) findViewById(R.id.friendInventoryButton);
        friendTrade = (Button) findViewById(R.id.friendTradeButton);

        if (isFriend==Boolean.FALSE){
            FriendStatusButton.setText("Add Friend");
            traderNameTextView.setText(UserManager.getFriend().getUserName());
            traderCityTextView.setText("City Unavailable");
            traderPhoneTextView.setText("Phonenumber Unavailable");
            traderEmailTextView.setText("Email Unavailable");
            friendInventory.setEnabled(false);
            friendTrade.setEnabled(false);
            traderName = traderNameTextView.getText().toString();
        } else {
            FriendStatusButton.setText("Remove Friend");
            traderNameTextView.setText(UserManager.getFriend().getUserName());
            traderCityTextView.setText(UserManager.getFriend().getUserCity());
            traderPhoneTextView.setText(UserManager.getFriend().getUserPhoneNumber());
            traderEmailTextView.setText(UserManager.getFriend().getUserEmail());
            traderName = traderNameTextView.getText().toString();
            friendInventory.setEnabled(true);
            friendTrade.setEnabled(true);
        }

    }

    /**
     * Called when user attempts to remove friend from their friendlist
     * @param view: remove trader button view
     */
    public void removeTraderButtonClicked(View view){
        if (isFriend == Boolean.TRUE){
            int index;
            UserManager.getTrader().getFriendList().hasFriend(UserManager.getFriend().getUserName());
            index = UserManager.getTrader().getFriendList().getFriendIndex(traderName);
            UserManager.getTrader().getFriendList().delFriend(index);
            ServerManager.saveUserOnline(UserManager.getTrader());
            stateswtich(isFriend);
        }
        else {
            FriendManager.addFriend(traderName);
            UserManager.saveUserLocally(this);
            ServerManager.saveUserOnline(UserManager.getTrader());
            stateswtich(isFriend);
        }
    }


    /**
     * Called when user attempts to trade with friend by clicking on Trade button
     * @param v: trade button view
     */
    public void tradeButtonClicked(View v){
        //Toast.makeText(getBaseContext(), "Trade", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(FriendProfileActivity.this,OfferTradeActivity.class);
        startActivity(intent);
        //finish();
    }

    /**
     * Called when user clicks inventory button on friend profile
     * @param v: Friend inventory button view
     */
    public void friendInventoryButton(View v){
        Intent intent = new Intent(FriendProfileActivity.this,FriendInventoryActivity.class );
        startActivity(intent);
    }

    /**
     * @param areFriend: boolean to check whether the user is a friend
     */
    public void stateswtich(Boolean areFriend){
        if(areFriend == Boolean.TRUE){
            Toast.makeText(getBaseContext(), traderName + " Removed From FriendList", Toast.LENGTH_SHORT).show();
            traderCityTextView.setText("City Unavailable");
            traderPhoneTextView.setText("Phonenumber Unavailable");
            traderEmailTextView.setText("Email Unavailable");
            FriendStatusButton.setText("Add Friend");
            friendInventory.setEnabled(false);
            friendTrade.setEnabled(false);
            isFriend = Boolean.FALSE;
        } else {
            Toast.makeText(getBaseContext(), traderName + " Added to FriendList", Toast.LENGTH_SHORT).show();
            traderCityTextView.setText(UserManager.getFriend().getUserCity());
            traderPhoneTextView.setText(UserManager.getFriend().getUserPhoneNumber());
            traderEmailTextView.setText(UserManager.getFriend().getUserEmail());
            FriendStatusButton.setText("Remove Friend");
            friendInventory.setEnabled(true);
            friendTrade.setEnabled(true);
            isFriend = Boolean.TRUE;
        }
    }

    //=================Functions needed for Testcases==================//

    public Button getAddFriendButton(){
        return (Button) findViewById(R.id.removeTraderButton);
    }

    public TextView getNameView(){
        return (TextView) findViewById(R.id.traderNameTextView);
    }

    public TextView getCityView(){
        return (TextView) findViewById(R.id.traderCityTextView);
    }

    public TextView getPhoneView(){
        return (TextView) findViewById(R.id.traderPhoneTextView);
    }

    public TextView getEmailView(){
        return  (TextView) findViewById(R.id.traderEmailTextView);
    }

    public Button getInventoryButton(){
        return (Button) findViewById(R.id.friendInventoryButton);
    }

    public Button getTradeButton(){
        return (Button) findViewById(R.id.friendTradeButton);
    }

    //=================End Function needed for Testcases===============//

}
