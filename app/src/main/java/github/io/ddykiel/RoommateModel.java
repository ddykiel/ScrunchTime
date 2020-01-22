package github.io.ddykiel;
import java.io.Serializable;

// A class that stores data about roommates
public class RoommateModel implements Serializable {
    private boolean isUser;
    private String status;
    private String roommateName;
    private int roommateClientID;

    public RoommateModel(boolean b, String s, int i){
        isUser = b;
        roommateName = s;
        roommateClientID = i;
        status = "INACTIVE";
    }

    boolean getIsUser(){
        return isUser;
    }

    void setIsUser(boolean b){
        isUser = b;
    }

    String getStatus(){
        return status;
    }

    void setStatus(String s){
        status = s;
    }

    String getRoommateName(){
        return roommateName;
    }

    void setRoommateName(String s){
        roommateName = s;
    }

    int getRoommateClientID(){
        return roommateClientID;
    }
}
