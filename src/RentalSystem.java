import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalSystem {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private RentalHistory rentalHistory = new RentalHistory(); // new attribute for Assignment #2
    
    // Original Assignment #1 methods (PLEASE PLEASEEE DON'T FORGET TO COPY CODE FROM FIRST ASSIGNMENT)
    
    // Original addVehicle method
    public boolean addVehicle(Vehicle vehicle) {
        if (vehicle == null || vehicle.getLicensePlate() == null || vehicle.getLicensePlate().isEmpty()) {
            return false;
        }
        for (Vehicle v : vehicles) {
            if (vehicle.getLicensePlate().equalsIgnoreCase(v.getLicensePlate())) {
                return false;
            }
        }
        vehicle.setStatus(Vehicle.Status.Available);
        vehicles.add(vehicle);
        return true;
    }
    
    // Original rentVehicle method
    public boolean rentVehicle(String licensePlate, String customerName) {
        for (Vehicle v : vehicles) {
            if (licensePlate.equalsIgnoreCase(v.getLicensePlate())) {
                if (v.getStatus() == Vehicle.Status.Available) {
                    String capsName = customerName.substring(0, 1).toUpperCase() +
                            customerName.substring(1).toLowerCase();
                    v.setStatus(Vehicle.Status.Rented);
                    return true;
                }
            }
        }
        return false;
    }
    
    // Original returnVehicle method
    public boolean returnVehicle(String licensePlate) {
        if (licensePlate == null) return false;
        for (Vehicle v : vehicles) {
            if (licensePlate.equalsIgnoreCase(v.getLicensePlate())) {
                if (v.getStatus() == Vehicle.Status.Rented) {
                    v.setStatus(Vehicle.Status.Available);
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    
    // Original display method
    public void displayAvailableVehicles() {
        System.out.println(" | Type | Plate | Make | Model | Year |");
        System.out.println("----------------------------------------------------------------------");
        for (Vehicle v : vehicles) {
            if (v.getStatus() == Vehicle.Status.Available) {
                String type = (v instanceof Car) ? "CAR" : "MINIBUS";
                System.out.println(
                    " | " + type + " | " + v.getLicensePlate() + " | " +
                    v.getMake() + " | " + v.getModel() + " | " + v.getYear() + " |"
                );
            }
        }
    }
    
    // New Assignment #2 methods (empty implementations)
    public boolean rentVehicle(Vehicle vehicle, Customer customer, LocalDate rentalDate, double rentalAmount) {
        return false; // Empty implementation
    }
    
    public boolean returnVehicle(Vehicle vehicle, Customer customer, LocalDate returnDate, double extraFees) {
        return false; // Empty implementation
    }
}