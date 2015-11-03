package cmput301t4.gameswap;

import junit.framework.TestCase;

/**
 * Created by dren on 11/2/15.
 */
public class ServerTest extends TestCase {

    public void testServerGet(){
        AccessServer server = new AccessServer();
        AccessServer.getServer();
    }
}
