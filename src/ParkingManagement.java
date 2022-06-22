import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingManagement {
    private List<Vehicle> allVehicles;
    private int slot = 10, s = 0;

    public ParkingManagement() {
        allVehicles = new ArrayList<>();
    }

    public String addVehicle(Vehicle vehicle) throws ParkingSlotNotAvailableException {
        if (vehicle instanceof ResidentVehicle) {
            allVehicles.add(vehicle);
            return "Your vehicle at parking slot NO: R" + (allVehicles.size() - s);
        } else if (slot == 0) {
            throw new ParkingSlotNotAvailableException();
        }

        allVehicles.add(vehicle);
        slot--;
        s++;
        return "Your vehicle at parking slot NO: V" + allVehicles.size();
    }

    public String setVisitorVehicleOutTime(String regNumber, String outTime) throws VehicleNotFoundException {
        // stream() is used to traverse the list of objects.
        // filter to get the VisitorVehicle object
        // Collector.toList() is used to convert the result to a list as result

        List<Vehicle> veh = allVehicles.stream().filter(i -> i instanceof VisitorVehicle).filter(j -> j.getRegNumber().equalsIgnoreCase(regNumber))
                .collect(Collectors.toList());

        // if the list of found vehicles is not including any vehicle, throw exception
        if (veh.isEmpty()) {
            throw new VehicleNotFoundException();
        }

        // otherwise set the time out for the vehicle in the list of found vehicle, as the list including only one car if found
        ((VisitorVehicle) veh.get(0)).setOutTime(outTime);
        slot++;
        return "Vehicle with RegNo: " + regNumber + " updated successfully";
    }

    public int getParkedVisitorVehiclesCount() {
        return (int) allVehicles.stream().filter(i -> i instanceof ResidentVehicle).filter(j -> ((ResidentVehicle) j).getRegNumber() != null).count();
    }

    public String displayAllVehicles() {
        String s = "Resident Vehicle\n\n";
        int j = 1;
        for (Vehicle v : allVehicles) {
            if (v instanceof ResidentVehicle) {
                s += j + " ";
                s += v;
                j++;
            }
            j++;

        }
        j = 1;
        s += "\n\nVisitor vehicle\n\n";
        for (Vehicle v : allVehicles) {
            if (v instanceof VisitorVehicle) {
                s += j + " ";
                s += v;
                j++;
            }
        }
        return s;
    }
}
