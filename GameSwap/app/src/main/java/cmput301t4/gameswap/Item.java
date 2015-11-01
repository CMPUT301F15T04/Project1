package cmput301t4.gameswap;

import android.content.Intent;

import java.sql.Blob;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import cmput301t4.gameswap.Exceptions.DateFormatException;
import cmput301t4.gameswap.Exceptions.NameTooLongException;

/**
 * Created by kynan on 10/30/15.
 */
public class Item {

    //=====Item Attributes=====//
    private String Name;
    private String Description;
    private Date ReleaseDate;
    //Private Image
    private Boolean isPrivate;
    private Integer Quality;
    private Integer Platform;

    //Contructor for Item without setting Image
    public Item(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description) throws NameTooLongException {
        this.setNameText(Name);
        this.checkDate(ReleaseDate);
        this.isPrivate = isPrivate;
        this.Quality = Quaility;
        this.Platform = Platform;
        this.Description = Description;
    }//End Item without image constructor

    //Contructor for Item with Image (Not Yet Implemented)
    /*
    public Item(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description) throws NameTooLongException {
        this.setNameText(Name);
        this.checkDate(ReleaseDate);
        this.isPrivate = isPrivate;
        this.Quality = Quaility;
        this.Platform = Platform;
        this.Description = Description;
    }//End Item without image constructor
    */

    //Method to ensure Date is in the correct format
    //Not sure how we want to do date.
    //There this method is TEMP
    public void checkDate(String Date) throws DateFormatException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            ReleaseDate = formatter.parse(Date);
        } catch (ParseException e) {
            throw new DateFormatException();
        }
    }//End checkDate

    //Method to ensure String is within desired length
    //Not sure how we want to do date.
    //There this method is TEMP
    public void setNameText(String text) throws NameTooLongException {
        if (text.length() <= 140) {
            this.Name = text;
        } else {
            throw new NameTooLongException();
        }
    }//End setNameText

    //=====Setting public and private function=====//
    public void setPublic() {
        this.isPrivate = true;
    }//end setPublic

    public void setPrivate() {
        this.isPrivate = false;
    }//end setPrivate

    //=====Getters and Setters=====//
    //Note: Setting IsPrivate true/false method is not in this section

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        ReleaseDate = releaseDate;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public Integer getQuality() {
        return Quality;
    }

    public void setQuality(Integer quality) {
        Quality = quality;
    }

    public Integer getPlatform() {
        return Platform;
    }

    public void setPlatform(Integer platform) {
        Platform = platform;
    }
}//end Item Class