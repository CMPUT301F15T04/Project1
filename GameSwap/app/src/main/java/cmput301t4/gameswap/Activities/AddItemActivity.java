package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cmput301t4.gameswap.Exceptions.DateFormatException;
import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

/**
 * Adds item to user inventory based off of user input
 */
public class AddItemActivity extends Activity implements OnItemSelectedListener{
    //create the unique list views and adapters for console, quality, and public and private

    /** The spinner to choose the console */
    private Spinner consoleSpinner;
    /** The spinner to choose the quality */
    private Spinner qualitySpinner;
    /** The spinner to choose if the item is public or private */
    private Spinner publicprivateSpinner;

    final static String DATE_FORMAT = "dd-MM-yyyy";

    private String title;
    private String releaseDate;
    private String description;
    private Date date;
    private Boolean isDateValid;



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

        prepareSpinnerdata();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
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

    private void prepareSpinnerdata(){
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

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void saveButtonClick(View view) {
        //Toast.makeText(getBaseContext(), "Saving", Toast.LENGTH_SHORT).show();

        EditText titleEditText = (EditText) findViewById(R.id.gameTitle);
        EditText releaseEditText = (EditText) findViewById(R.id.releaseDateEdit);
        EditText descEditText = (EditText) findViewById(R.id.descriptionBox);

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
            this.finish();
            Intent intent = new Intent(AddItemActivity.this, myInventoryActivity.class);
            startActivity(intent);
        }

    }
    public void cancelButtonClick(View view) {
        this.finish();
    }

    public static boolean checkDate(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
