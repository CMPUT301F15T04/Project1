package cmput301t4.gameswap.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import cmput301t4.gameswap.Exceptions.DateFormatException;

/**
 * Created by kynan on 11/1/15. Gives information and functionality of the trade
 */
public class Trade {
    //Ownername =  the one who initiated the trade
    //BorrowerName = the one who can accept the trade
    //Ownername/Borrower are String (TEMP not sure if we want to use ID,etc)
    /** The one initiating the trades name */
    private String Ownername;
    /** The name of the person receiving the trade */
    private String BorrowerName;
    /** The items that the initiating trader is offering */
    private ArrayList<Item> OwnerItems;
    /** The items that the initiator wants from the other trader*/
    private ArrayList<Item> BorrowerItems;
    /** The date of the trade */
    private Date DateTransaction;


    /**
     * Creates a new trade between two people
     *
     * @param OwnerName The name of the Initiator
     * @param BorrowerName The name of the Receiver
     * @param OwnerItems The Initiators items that are being offered
     * @param BorrowerItems The Receivers items that the Initiator wants
     * @throws DateFormatException This occurs when an improper date is given and can't be parsed
     */
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


    /**
     * Sets the values the user wants in the trade
     *
     * @param trade the trade that the user wants to create
     * @throws DateFormatException This occurs when an improper date is given and can't be parsed
     */
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
