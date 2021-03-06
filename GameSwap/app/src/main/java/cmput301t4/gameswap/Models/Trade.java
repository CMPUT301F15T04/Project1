package cmput301t4.gameswap.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cmput301t4.gameswap.Exceptions.DateFormatException;

/**
 * Gives information and functionality of the trade
 * @author Kynan Ly, Rupehra Chouhan , Daniel Ren, Blake Sakaluk, Preyanshu Kumar
 * @version Part 4
 */

public class Trade  {
    /** The one initiating the trades name */
    private String Ownername;
    /** The name of the person receiving the trade */
    private String BorrowerName;
    /** The items that the initiating trader is offering */
    private Inventory OwnerItems;
    /** The items that the initiator wants from the other trader*/
    private Inventory BorrowerItems;
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
    public Trade(String OwnerName, String BorrowerName, Inventory OwnerItems, Inventory BorrowerItems){
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

    }


    /**
     *Gets the owner name
     * @return owner name
     */
    public String getOwnername() {
        return Ownername;
    }

    /**
     * Sets the owner name
     * @param ownername
     */
    public void setOwnername(String ownername) {
        Ownername = ownername;
    }

    /**
     * Gets the borrower name
     * @return borrower name
     */
    public String getBorrowerName() {
        return BorrowerName;
    }

    /**
     * Sets the borrower name
     * @param borrowerName
     */
    public void setBorrowerName(String borrowerName) {
        BorrowerName = borrowerName;
    }

    /**
     * Gets the owner items
     * @return owner items
     */
    public Inventory getOwnerItems() {
        return OwnerItems;
    }

    /**
     * Sets the owner items
     * @param ownerItems
     */
    public void setOwnerItems(Inventory ownerItems) {
        OwnerItems = ownerItems;
    }

    /**
     * Sets the borrower items
     * @return borrower items
     */
    public Inventory getBorrowerItems() {
        return BorrowerItems;
    }

    /**
     * Set borrower items
     * @param borrowerItems
     */
    public void setBorrowerItems(Inventory borrowerItems) {
        BorrowerItems = borrowerItems;
    }

    /**
     * Gets the date of transaction
     * @return date of transaction
     */
    public Date getDateTransaction() {
        return DateTransaction;
    }

    /**
     * Sets date of transaction
     * @param dateTransaction
     */
    public void setDateTransaction(Date dateTransaction) {
        DateTransaction = dateTransaction;
    }


    /**
     * Sets the values the user wants in the trade
     * .equals Override
     *
     * @param trade the trade that the user wants to create
     * @throws DateFormatException This occurs when an improper date is given and can't be parsed
     */
    @Override
    public boolean equals(Object trade){
        Trade trade1 = (Trade) trade;
        if(this.Ownername.equals(trade1.getOwnername())){
            if(this.BorrowerName.equals(trade1.getBorrowerName())){
                if(this.DateTransaction.equals(trade1.getDateTransaction())){
                    //TODO make this more accurate check item by item maybe/modify an override when doing inventory compares
                    return true;
                }
                return true;
            }//end check BorrowerName
        }//end check OwnerNAme
        return false;
    }//end trade equals override
}//end Trade Class
