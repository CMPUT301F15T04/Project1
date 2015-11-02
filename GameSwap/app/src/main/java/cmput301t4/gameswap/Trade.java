package cmput301t4.gameswap;

import java.util.Date;
import java.util.ArrayList;

/**
 * Created by kynan on 11/1/15.
 */
public class Trade {
    //Ownername =  the one who initiated the trade
    //BorrowerName = the one who can accept the trade
    //Ownername/Borrower are String (TEMP not sure if we want to use ID,etc)
    private String Ownername;
    private String BorrowerName;
    private ArrayList<Item> OwnerItems;
    private ArrayList<Item> BorrowerItems;
    private Date DateTransaction;

    public Trade(String OwnerName, String BorrowerName, ArrayList<Item> OwnerItems, ArrayList<Item> BorrowerItems){
        this.Ownername = OwnerName;
        this.BorrowerName = BorrowerName;
        this.OwnerItems = OwnerItems;
        this.BorrowerItems = BorrowerItems;
        this.DateTransaction = new Date();
    }//End Trade Constructor


    //=====Getters and Setters=====//
    public String getOwnername() {
        return Ownername;
    }

    public void setOwnername(String ownername) {
        Ownername = ownername;
    }

    public String getBorrowerName() {
        return BorrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        BorrowerName = borrowerName;
    }

    public ArrayList<Item> getOwnerItems() {
        return OwnerItems;
    }

    public void setOwnerItems(ArrayList<Item> ownerItems) {
        OwnerItems = ownerItems;
    }

    public ArrayList<Item> getBorrowerItems() {
        return BorrowerItems;
    }

    public void setBorrowerItems(ArrayList<Item> borrowerItems) {
        BorrowerItems = borrowerItems;
    }

    public Date getDateTransaction() {
        return DateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        DateTransaction = dateTransaction;
    }
}//end Trade Class
