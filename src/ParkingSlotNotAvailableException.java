public class ParkingSlotNotAvailableException extends Exception {
    public ParkingSlotNotAvailableException() {
        System.out.println("There is no parking slot available for your car!!!!");
    }

    public ParkingSlotNotAvailableException(String message) {
        super(message);
        System.out.println(message);
    }
}
