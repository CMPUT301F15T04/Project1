package cmput301t4.gameswap.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import cmput301t4.gameswap.Exceptions.DateFormatException;

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
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date date = new Date();
        String datestring = df.format(date);
        try {
            this.DateTransaction = df.parse(datestring);
        } catch (ParseException e) {
            throw new DateFormatException();
        }
        //this.DateTransaction = new Date();

        //=====CODE FOR NOTIFICATION GOES HERE (WHEN WE DO SERVER)=====//


        //=====END CODE FOR NOTIFICATION=====//

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

    //===== .equals Override =====//
    @Override
    public boolean equals(Object trade){
        Trade trade1 = (Trade) trade;
        if(this.Ownername.equals(trade1.getOwnername())){
            if(this.BorrowerName.equals(trade1.getBorrowerName())){
                if(this.OwnerItems.equals(trade1.getOwnerItems())){
                    if(this.BorrowerItems.equals(trade1.getBorrowerItems())){
                        //WE DID NOT ADD THE DATE CHECK YET
                        return true;
                    }//end check BorrowerItem
                }//end check OwnerItem
            }//end check BorrowerName
        }//end check OwnerNAme
        return false;
    }//end trade equals override
}//end Trade Class
