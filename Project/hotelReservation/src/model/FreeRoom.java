package model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, Double price, RoomType roomType) {
        super(roomNumber, price, roomType);
        this.setRoomPrice(0.0);
    }

    @Override
    public String toString() {
        return "Room number: " + this.getRoomNumber() + "\nRoom type: " + this.getRoomType().toString() + "\nThis is a free room.";
    }
}
