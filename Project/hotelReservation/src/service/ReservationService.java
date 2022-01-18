package service;

import model.*;
import java.util.*;

public class ReservationService {
    List<IRoom> roomList = new ArrayList<>();
    Map<String, IRoom> mapOfRoom = new HashMap<>();
    public void addRoom(IRoom room) {
        this.roomList.add(room);
        this.mapOfRoom.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return this.mapOfRoom.get(roomId);
    }
}
