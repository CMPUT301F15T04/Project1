
public class CreateAccountTest extends ActivityInstrumentationTestCase2 {

	//Note 1: Current Account parameter are longer than actually stated in testcase
	//For now (+ simplicity + readability) we only use 3 parameter

	//Note 2: This testcase deals does not deal with if "friends" or "others" 
	//tries to access account information. They will be added later once basic
	//functionality is completed

	//Note 3: We assume you currently have a connection to server. This is because 
	//to create an account it is assumed you are connected online. Therefore all
	//test written will be based off the assumption you have connect to server
	//Offline/Online testcases will be implemented later once basic
	//functionality is completed

	private boolean connection = Boolean.TRUE; //Assumption connection = true

	public CreateAccountTest(){
        	super(tradegames.class);    //"tradegamesapp" is name of the app for now
        }

	//Test Create Account
	public void testCreateAccount(){
		ServerManage SM= new ServerManager();
        	Account accountOne = new Account("rupehra","Edmonton","rupehra@ualberta.ca");
		assertTrue(connection); //Place holder for Connection test
		SM.addAccountServer(accountOne);
		//Check if account is saved into server
        	assertTrue(SM.loadAccountServer(accountOne));
		//Check to see if saved accurately 
        	assertTrue(SM.loadAccountServer(accountOne) == accountOne);
        }
	//Test if the Account Alreadt exist
	public void testAccountAlreadyExists(){
		ServerManage SM= new ServerManager();
        	Account accountOne = new Account("rupehra","Edmonton","rupehra@ualberta.ca");
		assertTrue(connection); //Place holder for Connection test
		SM.addAccountServer(accountOne);
		//Check if account is saved into server
        	assertTrue(SM.loadAccountServer(accountOne));
		//Check to see if saved accurately 
        	assertTrue(SM.loadAccountServer(accountOne) == accountOne);

		Account accountTwo = new Account("rupehra","Edmonton","rupehra@ualberta.ca");
		//We should not be able to add this account
		try{
			//Try to add accountTwo
        		SM.addAccountServer(accountTwo);
        	}catch(AlreadyExistException e){
        		displayMessage("Account already exist");
        	}	
        }
	//Test if we can find an account via name (searching by name is not actually how it
	//is going to be done)
	public void testFindAccount(){
		ServerManage SM= new ServerManager();
		assertTrue(connection); //Place holder for Connection test
        	Account accountOne = new Account("rupehra","Edmonton","rupehra@ualberta.ca");
		SM.addAccountServer(accountOne);
		assertTrue(SM.findAccount("rupehra"))
	}
	//Test if invalid input into Account infomration
	public void testAccountInvalidInformation(){
        	ServerManage SM= new ServerManager();
		assertTrue(connection); //Place holder for Connection test
        	Account accountOne = new Account(" "," ","");
        	try{
        		SM.addAccountServer(accountOne);
        	}catch(InvalidInformationException e){
        		displayMessage("Invalid information provided");
        	}
        }
	//US02.04.01
	//As an owner or borrower, I will have a profile where by my contact information and city are recorded.
	//Test to see if you can access other people's account Information 
	public void testLoadProfile(){
		ServerManage SM= new ServerManager();
        	Account accountOne = new Account("rupehra","Edmonton","rupehra@ualberta.ca");
		assertTrue(connection); //Place holder for Connection test
		SM.addAccountServer(accountOne);
		Account accountOne = SM.loadAccountServer(accountOne);
		assertTrue(accountOne.getName().equals("rupehra"));
		assertTrue(accountOne.getCity().equals("Edmonton"));
		assertTrue(accountOne.getEmail().equals("rupehra@ualberta.ca"));
	}
	//Test if you can edit a Profile in the account
	public void testEditProfile(){
		ServerManage SM= new ServerManager();
        	Account accountOne = new Account("rupehra","Edmonton","rupehra@ualberta.ca");
		assertTrue(connection); //Place holder for Connection test
		SM.addAccountServer(accountOne);
		//Since to edit a profile it is assumed you are currently logged in and the program know what "accountOne" is the current user
		Account accountOne = SM.loadAccountServer(accountOne);
		//Check that the account current city is Edmonton
		assertTrue(accountOne.getCity == "Edmonton");
		accountOne.setCity("Not Edmonton");
		SM.updateAccountServer(accountOne);
        	Account changeAccountOne = loadFromFile(accountOne);
		//Check to see change in account City
		assertTrue(changeAccountOne.getCity().equals("Not Edmonton"));
        }


}//end CreateAccountTest
