public class VehicleNotFoundException extends Exception {
    public VehicleNotFoundException() {
        System.out.println("Your car is not found!!");
    }

    public VehicleNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }
}
