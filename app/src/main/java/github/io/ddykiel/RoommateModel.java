package github.io.ddykiel;
import java.io.Serializable;

public class RoommateModel implements Serializable {
    private boolean isUser;
    private String status;
    private String roommateName;
    private int roommateID;

    public RoommateModel(boolean b, String s, int i){
        isUser = b;
        roommateName = s;
        roommateID = i; // Eventually phase out?
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

    int getRoommateID(){
        return roommateID;
    }
}
