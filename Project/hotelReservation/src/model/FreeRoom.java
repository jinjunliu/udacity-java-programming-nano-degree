package model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, Double price, RoomType roomType) {
        super(roomNumber, price, roomType);
        this.price = 0.0;
    }

    @Override
    public String toString() {
        return "Room number: " + roomNumber + "\nRoom type: " + roomType.toString() + "\nThis is a free room.";
    }
}
