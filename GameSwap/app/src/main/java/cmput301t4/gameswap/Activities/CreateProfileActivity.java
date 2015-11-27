package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.R;

/**
 * Created by Blake on 2015-11-05.
 */
public class CreateProfileActivity extends Activity {
    private User myUser;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText cityEditText;
    private EditText phoneNumberEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        usernameEditText = (EditText) findViewById(R.id.username);
        emailEditText = (EditText) findViewById(R.id.email_user);
        cityEditText = (EditText) findViewById(R.id.user_city);
        phoneNumberEditText = (EditText) findViewById(R.id.user_phone_num);
    }

    public void createProfileButton(View view) {

        UserManager.createUser(usernameEditText.getText().toString(), emailEditText.getText().toString(), cityEditText.getText().toString(), phoneNumberEditText.getText().toString(), this);
        ServerManager.saveUserOnline(UserManager.getTrader());

        this.finish();
    }

    public void cancelButton(View view) {
        this.finish();
    }

    public User getUser() {

        Log.w("myUser",myUser.getUserName());
        return myUser;
    }

    //======Code used for testcases=====//

    public EditText getusername(){return usernameEditText;}

    public EditText getemail(){return emailEditText;}

    public EditText getcity(){return cityEditText;}

    public EditText getphonenumber(){return phoneNumberEditText;}

    public Button getCreateProfilebutton(){
        Button button = (Button) findViewById(R.id.saveButton);
        return button;
    }

    //======End code used for testcases=====//
}
