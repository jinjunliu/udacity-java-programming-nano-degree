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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (!this.roomNumber.equals(other.roomNumber)) {
            return false;
        }
        if (!this.price.equals(other.price)) {
            return false;
        }
        return this.roomType == other.roomType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + (this.roomNumber != null ? this.roomNumber.hashCode() : 0);
        hash = 13 * hash + (this.price != null ? this.price.hashCode() : 0);
        hash = 13 * hash + (this.roomType != null ? this.roomType.hashCode() : 0);
        return hash;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return this.roomType;
    }

    @Override
    public boolean isFree() {
        return false;
    }
}
