package github.io.ddykiel;
import java.util.ArrayList;
import java.io.Serializable;

public class RoomModel implements Serializable {
    private String roomName;
    private ArrayList<RoommateModel> roomies;
    private RoommateModel user;
    private ServerTranslator translator;

    public RoomModel(String s, RoommateModel currentUser, RoommateModel firstRoommate){

        roomName = s;
        user = currentUser;

        roomies = new ArrayList<RoommateModel>();
        roomies.add(firstRoommate);
        translator = new ServerTranslator();
    }

    String getRoomName(){
        return roomName;
    }

    void setRoomName(String s){
        roomName = s;
    }

    RoommateModel getUser(){
        return user;
    }

    ArrayList<RoommateModel> getRoommates(){
        return roomies;
    }

    void addRoommate(RoommateModel r){
        roomies.add(r);
    }

    void removeRoommate(RoommateModel r){
        roomies.remove(r);
    }

    ServerTranslator getTranslator(){
        return translator;
    }
}
