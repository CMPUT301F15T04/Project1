package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.R;

/**
 * Views user's information on one page
 *
 * @author Preyanshu Kumar, Kynan Ly, Daniel Ren, Rupehra Chouhan, Blake Sakaluk
 * @version Part 4
 */
public class MyProfileActivity extends Activity {

    /** TextView for user name*/
    private TextView nameTextView;
    /** TextView for user location */
    private TextView locationTextView;
    /** TextView for user contact info*/
    private TextView contactTextView;
    /** TextView for user email*/
    private TextView emailTextView;
    //private ImageView profilePicture;


    /**
     * Displays user personal information about the user
     * @param savedInstanceState
     */
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

        //profilePicture = (ImageView) findViewById(R.id.profilePic);
        //profilePicture.setImageBitmap(profileImageView);
    }


    /**
     * Called when the user clicks on Edit Profile button
     * @param view: edit profile button view
     */
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

    public void checkNotifications(View v){
        String message = "";
        for(int i = 0; i < 4; i++){
            if(i == 3){
                message += UserManager.getTrader().IfNotify(i);
            }else{
                message += UserManager.getTrader().IfNotify(i) + "\n";
            }
        }
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

}
