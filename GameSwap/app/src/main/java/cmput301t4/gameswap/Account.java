package cmput301t4.gameswap;

/**
 * Created by rupehra on 11/1/15.

        This is a dummy account. We might not need an account at all.
 */


public class Account {

    private String username;
    private String password;
    private Integer accountId;
    private Trader trader;


    public Account(String username, String password){
        this.username = username;
        this.password = password;
        this.accountId = 1;                         // need to implement this, use U
//        this.trader = new Trader();               // this line of code is giving errors
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }







}
