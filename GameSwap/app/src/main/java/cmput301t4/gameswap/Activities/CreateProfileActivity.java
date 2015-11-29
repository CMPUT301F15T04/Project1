package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.R;

/**
 * Created by Blake and Preyanshu on 2015-11-05.
 */
public class CreateProfileActivity extends Activity {
    private User myUser;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText cityEditText;
    private EditText phoneNumberEditText;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
    }
    public void addImageOption(View view) {
        final ImageButton takeProfPic = (ImageButton) findViewById(R.id.profilePicture);
        takeProfPic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }
    public void createProfileButton(View view) {
        usernameEditText = (EditText) findViewById(R.id.username);
        emailEditText = (EditText) findViewById(R.id.email_user);
        cityEditText = (EditText) findViewById(R.id.user_city);
        phoneNumberEditText = (EditText) findViewById(R.id.user_phone_num);
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
}
