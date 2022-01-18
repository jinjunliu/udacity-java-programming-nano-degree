package model;

import java.lang.*;

public class Room implements IRoom {
    String roomNumber;
    Double price;
    RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Room number: " + roomNumber + "\nPrice: " + price.toString() + "\nRoom type: " + roomType.toString();
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return false;
    }
}
