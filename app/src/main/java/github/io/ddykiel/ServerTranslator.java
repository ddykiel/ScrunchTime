package github.io.ddykiel;

public class ServerTranslator {

    public ServerTranslator(){
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

    // It may be better to de-couple this from RoommateModel
    // We could create a HashTable<RoommateModel, Int> to map RoommateModels to IDs
    // However, I feel like there's more potential for this to go wrong... so for now, RoommateIDs are stored in the RoommmateModel object
    int convertName(RoommateModel r){
        return r.getRoommateID();
    }
}
