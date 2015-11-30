package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.R;

public class MyProfileActivity extends Activity {

    private TextView nameTextView;
    private TextView locationTextView;
    private TextView contactTextView;
    private TextView emailTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);


        nameTextView = (TextView) findViewById(R.id.nameTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
        contactTextView = (TextView) findViewById(R.id.contactTextView);
        emailTextView = (TextView) findViewById(R.id.email);

        nameTextView.setText(UserManager.getTrader().getUserName().toString());
        locationTextView.setText(UserManager.getTrader().getUserCity().toString());
        contactTextView.setText(UserManager.getTrader().getUserPhoneNumber());
        emailTextView.setText(UserManager.getTrader().getUserEmail());
    }

    public void editProfileButtonClicked(View view){
        Intent intent = new Intent(MyProfileActivity.this,EditProfileActivity.class);
        finish();
        startActivity(intent);

    }
    //=====Function needed for Testcases=====//

    public Button getEditButton(){
        Button button = (Button) findViewById(R.id.editProfile);
        return button;
    }//end getEditButton

    public TextView getNameText(){
        return nameTextView;
    }//end getNameText

    public TextView getContactText(){
        return contactTextView;
    }//end getContactText

    public TextView getEmailText(){
        return emailTextView;
    }//end getEmailText

    public TextView getCityText(){
        return locationTextView;
    }

    //=====End of function needed for Testcases=====//

}
