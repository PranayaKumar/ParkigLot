package exception;

public class ParkingFloorNotFoundException extends Exception{
    public ParkingFloorNotFoundException() {
    }

    public ParkingFloorNotFoundException(String message) {
        super(message);
    }
}
