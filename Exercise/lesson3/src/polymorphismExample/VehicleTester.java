package polymorphismExample;

public class VehicleTester {
    public static void main(String[] args) {
        // Create an array of vehicles
        Vehicle[] vehicles = new Vehicle[3];
        // Add a Car, Boat, and Plane object to the array
        vehicles[0] = new Car();
        vehicles[1] = new Boat();
        vehicles[2] = new Plane();

        for (Vehicle vehicle : vehicles) {
            vehicle.speed();
        }
    }
}
