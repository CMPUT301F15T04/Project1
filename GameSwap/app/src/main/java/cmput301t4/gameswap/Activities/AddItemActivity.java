package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cmput301t4.gameswap.Exceptions.DateFormatException;
import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

/**
 * Adds item to user inventory based off of user input
 * Created by Preyanshu and Blake 2015-11-04
 */
public class AddItemActivity extends Activity implements OnItemSelectedListener {
    //create the unique list views and adapters for console, quality, and public and private
    //code referenced from http://developer.android.com/guide/topics/ui/controls/spinner.html

    /**
     * The spinner to choose the console
     */
    private Spinner consoleSpinner;

    /**
     * The spinner to choose the quality
     */
    private Spinner qualitySpinner;

    /**
     * The spinner to choose if the item is public or private
     */
    private Spinner publicprivateSpinner;

    /**
     * The Date for the item being added
     */
    final static String DATE_FORMAT = "dd-MM-yyyy";

    /**
     * The title for the item being added
     */
    private String title;

    /**
     * The release date for the item being added
     */
    private String releaseDate;

    /**
     * The description for the item being added
     */
    private String description;

    /**
     * The check for if the date added is valid
     */
    private Boolean isDateValid;

    /**
     * The file were our information will be added
     */
    private static final String FILENAME = "file.sav"; // model

    /**
     * The text if the user wants to edit the title of the item
     */
    private EditText titleEditText;

    /**
     * The text if the user wants to edit the description of the item
     */
    private EditText descEditText;

    /**
     * The text if the user wants to edit the release date of the item
     */
    private EditText releaseEditText;

    private ImageView userImageButton;


    private ArrayAdapter<Item> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //loads it upon saving
        super.onCreate(savedInstanceState);
        //sets it to the activity
        setContentView(R.layout.activity_add_item);
        //setting spinners
        consoleSpinner = (Spinner) findViewById(R.id.consoleSpinner);
        qualitySpinner = (Spinner) findViewById(R.id.qualitySpinner);
        publicprivateSpinner = (Spinner) findViewById(R.id.privatepublicSpinner);
        userImageButton= (ImageButton) findViewById(R.id.imageButton);

        prepareSpinnerdata();

        loadFromFile();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_add_item, menu);
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

    /**
     * Prepares the data for the different spinners that we have
     */
    private void prepareSpinnerdata() {
        //function creates spinner data for us for the three spinners here.
        // Create an ArrayAdapter for console array
        ArrayAdapter<CharSequence> console_adapter = ArrayAdapter.createFromResource(this,
                R.array.Console, android.R.layout.simple_spinner_item);
        // Specify the layout to be dropdown
        console_adapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
        // Apply the adapter to the console spinner
        consoleSpinner.setAdapter(console_adapter);

        // Create Array adapter for quality array
        ArrayAdapter<CharSequence> quality_adapter = ArrayAdapter.createFromResource(this,
                R.array.Quality, android.R.layout.simple_spinner_item);
        // Specify the layout to be a dropdown
        quality_adapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
        // Apply the adapter to the quality spinner
        qualitySpinner.setAdapter(quality_adapter);

        // Create Array adapter for the array we wish to use for private/public
        ArrayAdapter<CharSequence> public_private_adapter = ArrayAdapter.createFromResource(this,
                R.array.Public_or_Private, android.R.layout.simple_spinner_item);
        // use the layout for public and private
        public_private_adapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
        publicprivateSpinner.setAdapter(public_private_adapter);

    }

    private void selectImage(){

        Intent choosePicIntent=new Intent();
        choosePicIntent.setAction(Intent.ACTION_GET_CONTENT);

        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }

    /**
     * A necessary function that must be added to choose the item in the spinner
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        // On selecting a spinner item
        //String item = parent.getItemAtPosition(pos).toString();

        //code referenced from http://stackoverflow.com/questions/13431644/save-and-retrieve-selected-spinner-position
        int userChoiceConsole = consoleSpinner.getSelectedItemPosition();
        int userChoiceQuality = qualitySpinner.getSelectedItemPosition();
        int userChoicePrivate = publicprivateSpinner.getSelectedItemPosition();
        SharedPreferences sharedPref = getSharedPreferences("FileName", 0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("userChoiceSpinner", userChoiceConsole);
        prefEditor.putInt("userChoiceSpinner", userChoiceQuality);
        prefEditor.putInt("userChoiceSpinner", userChoicePrivate);


        prefEditor.commit();
    }
    /**
     * A necessary function to implement spinners if nothing is chosen
     */
    public void onNothingSelected(AdapterView<?> parent) {
    }
    /**
     * Saves the data from the inputs we enter
     */
    public void saveButtonClick(View view) {

        titleEditText = (EditText) findViewById(R.id.gameTitle);
        releaseEditText = (EditText) findViewById(R.id.releaseDateEdit);
        descEditText = (EditText) findViewById(R.id.descriptionBox);

        int console = consoleSpinner.getSelectedItemPosition();
        int qual = qualitySpinner.getSelectedItemPosition();
        boolean isPrivate = (publicprivateSpinner.getSelectedItemPosition() == 1);

        title = titleEditText.getText().toString();
        releaseDate = releaseEditText.getText().toString();
        description = descEditText.getText().toString();

        isDateValid = checkDate(releaseDate);
        if (isDateValid == false)
            Toast.makeText(getBaseContext(), "Wrong date format!", Toast.LENGTH_SHORT).show();

        else if (TextUtils.isEmpty(title) || TextUtils.isEmpty(releaseDate) || TextUtils.isEmpty(description)) {
            Toast.makeText(getBaseContext(), "At least one of the fields is empty!", Toast.LENGTH_SHORT).show();
        } else {
            InventoryManager.addItem(title, releaseDate, isPrivate, qual, console, description);

            saveToFile();
            this.finish();
            Intent intent = new Intent(AddItemActivity.this, myInventoryActivity.class);
            startActivity(intent);
        }

    }

    public void cancelButtonClick(View view) {
        this.finish();
    }

    public void addImageOption(View view){

        PopupMenu popupMenu = new PopupMenu(AddItemActivity.this,userImageButton );
        popupMenu.getMenuInflater().inflate(R.menu.image_popup,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getBaseContext(), "Yay", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        popupMenu.show();



    }



    public static boolean checkDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Saves our added item to a Gson file
     */
    private void saveToFile() {

        try {
            ArrayList<Item> items = InventoryManager.getInstance().getItems();
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(items, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

    }
    /**
     * Loads our item from the Gson file if needed
     */
    private void loadFromFile(){

        try {

            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type arraylistType = new TypeToken<ArrayList<Item>>() {}.getType();
            ArrayList<Item> items = gson.fromJson(in, arraylistType);
            InventoryManager.getInstance().setItems(items);
        } catch (FileNotFoundException e) {
            ArrayList<Item> items = InventoryManager.getInstance().getItems();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

