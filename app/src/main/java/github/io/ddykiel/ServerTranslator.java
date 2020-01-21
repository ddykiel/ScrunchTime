package github.io.ddykiel;

import java.io.Serializable;
import java.util.HashMap;

public class ServerTranslator implements Serializable {
// ArrayList serverPair;
    private HashMap<Integer, Integer> clientToServerIDs;

    public ServerTranslator(){
        clientToServerIDs = new HashMap<Integer, Integer>();
    }

    // Translates the string "status," used on the client side, to the corresponding integers on the server side
    // The first two numbers are the version number. The last number is a code corresponding with a color.
    // The server side is using a data type "small int," hence the five integers
    int convertStatus(RoommateModel r){
        String status = r.getStatus();
        int intStatus = -1;

        if (status.equals("RED")){
            intStatus = 01001;
        }
        if (status.equals("PURPLE")){
            intStatus = 01002;
        }
        if (status.equals("BLUE")){
            intStatus = 01003;
        }
        if (status.equals("GREEN")){
            intStatus = 01004;
        }
        if (status.equals("YELLOW")){
            intStatus = 01005;
        }
        if (status.equals("INACTIVE")){
            intStatus = 01006;
        }

        return intStatus;
    }

    int convertID(RoommateModel r){
        int clientID = r.getRoommateClientID();
        return clientToServerIDs.get(clientID);
    }

    void addID(RoommateModel r){
        clientToServerIDs.put(r.getRoommateClientID(), -1);
    }

}
