import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class RentalSystem {
	// MY PRIVATE STATIC INSTANCE!!
	private static RentalSystem instance;
    
    // MY PRIVATE CONSTRUCTOR
    private RentalSystem() {
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
        rentalHistory = new RentalHistory();
        loadData();
    }
    
 // PUBLIC STATIC METHOD FOR ME TO GET INSTANCE
    public static RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }
	
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private RentalHistory rentalHistory = new RentalHistory();

    public boolean addVehicle(Vehicle vehicle) {
        // This code helps us check for duplicate license plate
        if (findVehicleByPlate(vehicle.getLicensePlate()) != null) {
            System.out.println("Error: Vehicle with license plate " + vehicle.getLicensePlate() + " already exists.");
            return false;
        }
        
        vehicles.add(vehicle);
        saveVehicle(vehicle);
        System.out.println("Vehicle added successfully.");
        return true;
    }

    public boolean addCustomer(Customer customer){
    	   // This code helps us check for duplicate customer ID
        if (findCustomerById(customer.getCustomerId()) != null) {
            System.out.println("Error: Customer with ID " + customer.getCustomerId() + " already exists.");
            return false;
        }
        
        customers.add(customer);
        saveCustomer(customer);
        System.out.println("Customer added successfully.");
        return true;
    }

    public void rentVehicle(Vehicle vehicle, Customer customer, LocalDate date, double amount) {
        if (vehicle.getStatus() == Vehicle.VehicleStatus.Available) {
            vehicle.setStatus(Vehicle.VehicleStatus.Rented);
            rentalHistory.addRecord(new RentalRecord(vehicle, customer, date, amount, "RENT"));
            saveRecord(new RentalRecord(vehicle, customer, date, amount, "RENT"));
            System.out.println("Vehicle rented to " + customer.getCustomerName());
        }
        else {
            System.out.println("Vehicle is not available for renting.");
        }
        
        
    }

    public void returnVehicle(Vehicle vehicle, Customer customer, LocalDate date, double extraFees) {
        if (vehicle.getStatus() == Vehicle.VehicleStatus.Rented) {
            vehicle.setStatus(Vehicle.VehicleStatus.Available);
            rentalHistory.addRecord(new RentalRecord(vehicle, customer, date, extraFees, "RETURN"));
            System.out.println("Vehicle returned by " + customer.getCustomerName());
        }
        else {
            System.out.println("Vehicle is not rented.");
        }
        
        saveRecord(new RentalRecord(vehicle, customer, date, extraFees, "RETURN"));
    }    

    public void displayVehicles(Vehicle.VehicleStatus status) {
        // Display appropriate title based on status
        if (status == null) {
            System.out.println("\n=== All Vehicles ===");
        } else {
            System.out.println("\n=== " + status + " Vehicles ===");
        }
        
        // Header with proper column widths
        System.out.printf("|%-16s | %-12s | %-12s | %-12s | %-6s | %-18s |%n", 
            " Type", "Plate", "Make", "Model", "Year", "Status");
        System.out.println("|--------------------------------------------------------------------------------------------|");
    	  
        boolean found = false;
        for (Vehicle vehicle : vehicles) {
            if (status == null || vehicle.getStatus() == status) {
                found = true;
                String vehicleType;
                if (vehicle instanceof Car) {
                    vehicleType = "Car";
                } else if (vehicle instanceof Minibus) {
                    vehicleType = "Minibus";
                } else if (vehicle instanceof PickupTruck) {
                    vehicleType = "Pickup Truck";
                } else {
                    vehicleType = "Unknown";
                }
                System.out.printf("| %-15s | %-12s | %-12s | %-12s | %-6d | %-18s |%n", 
                    vehicleType, vehicle.getLicensePlate(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getStatus().toString());
            }
        }
        if (!found) {
            if (status == null) {
                System.out.println("  No Vehicles found.");
            } else {
                System.out.println("  No vehicles with Status: " + status);
            }
        }
        System.out.println();
    }

    public void displayAllCustomers() {
        for (Customer c : customers) {
            System.out.println("  " + c.toString());
        }
    }
    
    public void displayRentalHistory() {
        if (rentalHistory.getRentalHistory().isEmpty()) {
            System.out.println("  No rental history found.");
        } else {
            // Header with proper column widths
            System.out.printf("|%-10s | %-12s | %-20s | %-12s | %-12s |%n", 
                " Type", "Plate", "Customer", "Date", "Amount");
            System.out.println("|-------------------------------------------------------------------------------|");
            
            for (RentalRecord record : rentalHistory.getRentalHistory()) {                
                System.out.printf("| %-9s | %-12s | %-20s | %-12s | $%-11.2f |%n", 
                    record.getRecordType(), 
                    record.getVehicle().getLicensePlate(),
                    record.getCustomer().getCustomerName(),
                    record.getRecordDate().toString(),
                    record.getTotalAmount()
                );
            }
            System.out.println();
        }
    }
    
    public Vehicle findVehicleByPlate(String plate) {
        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equalsIgnoreCase(plate)) {
                return v;
            }
        }
        return null;
    }
    
    public Customer findCustomerById(int id) {
        for (Customer c : customers)
            if (c.getCustomerId() == id)
                return c;
        return null;
    }
    
 // 1. Save vehicle to file (called inside addVehicle())
    private void saveVehicle(Vehicle vehicle) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("vehicles.txt", true))) {
            // Format: Type,LicensePlate,Make,Model,Year,Status
            String vehicleType;
            if (vehicle instanceof Car) {
                vehicleType = "CAR";
            } else if (vehicle instanceof Minibus) {
                vehicleType = "MINIBUS";
            } else if (vehicle instanceof PickupTruck) {
                vehicleType = "PICKUPTRUCK";
            } else {
                vehicleType = "UNKNOWN";
            }
            
            writer.println(vehicleType + "," + 
                          vehicle.getLicensePlate() + "," + 
                          vehicle.getMake() + "," + 
                          vehicle.getModel() + "," + 
                          vehicle.getYear() + "," + 
                          vehicle.getStatus());
        } catch (IOException e) {
            System.out.println("Error saving vehicle: " + e.getMessage());
        }
    }

    // 2. Save customer to file (called inside addCustomer())
    private void saveCustomer(Customer customer) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("customers.txt", true))) {
            // Format: CustomerID,Name,PhoneNumber
            writer.println(customer.getCustomerId() + "," + 
                          customer.getCustomerName() );
        } catch (IOException e) {
            System.out.println("Error saving customer: " + e.getMessage());
        }
    }

    // 3. Save rental record to file (called in rentVehicle() and returnVehicle())
    private void saveRecord(RentalRecord record) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("rental_records.txt", true))) {
            // Format: RecordType,LicensePlate,CustomerID,Date,Amount
            writer.println(record.getRecordType() + "," + 
                          record.getVehicle().getLicensePlate() + "," + 
                          record.getCustomer().getCustomerId() + "," + 
                          record.getRecordDate() + "," + 
                          record.getTotalAmount());
        } catch (IOException e) {
            System.out.println("Error saving rental record: " + e.getMessage());
        }
    }
    
 // Load data from files when program starts
    private void loadData() {
        loadVehicles();
        loadCustomers();
        loadRentalRecords();
    }
    
    
 // THIS METHOD WILL HELP US TO LOAR THE VEHICLES FROM vehicles.txt
    private void loadVehicles() {
        try (BufferedReader reader = new BufferedReader(new FileReader("vehicles.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String type = parts[0];
                    String licensePlate = parts[1];
                    String make = parts[2];
                    String model = parts[3];
                    int year = Integer.parseInt(parts[4]);
                    Vehicle.VehicleStatus status = Vehicle.VehicleStatus.valueOf(parts[5]);
                    
                    Vehicle vehicle = null;
                    switch (type) {
                        case "CAR":
                            vehicle = new Car(make, model, year, 4); // THIS COULD BE OUR DEFAULT NUMBER OF SEATS
                            break;
                        case "MINIBUS":
                            vehicle = new Minibus(make, model, year, false); // THIS COULD BE OUR DEFAULT ACCESSIBILITY
                            break;
                        case "PICKUPTRUCK":
                            vehicle = new PickupTruck(make, model, year, 0.0, false); // Default cargo size and trailer
                            break;
                    }
                    
                    if (vehicle != null) {
                        vehicle.setLicensePlate(licensePlate);
                        vehicle.setStatus(status);
                        vehicles.add(vehicle);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No vehicles file found or error reading: " + e.getMessage());
        }
    }
    
    
 // THIS METHOD WILL HELP US TO LOAD THE CUSTOMERS FROM customers.txt
    private void loadCustomers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    int customerId = Integer.parseInt(parts[0]);
                    String name = parts[1];
                
                    
                    Customer customer = new Customer(customerId, name);
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            System.out.println("No customers file found or error reading: " + e.getMessage());
        }
    }
    
    
    
 // THIS METHOD WILL HELP US TO LOAD THE RENTAL RECORDS FROM rental_records.txt
    private void loadRentalRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("rental_records.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String recordType = parts[0];
                    String licensePlate = parts[1];
                    int customerId = Integer.parseInt(parts[2]);
                    LocalDate recordDate = LocalDate.parse(parts[3]);
                    double totalAmount = Double.parseDouble(parts[4]);
                    
                    // Find the vehicle and the customer
                    Vehicle vehicle = findVehicleByPlate(licensePlate);
                    Customer customer = findCustomerById(customerId);
                    
                    if (vehicle != null && customer != null) {
                        RentalRecord record = new RentalRecord(vehicle, customer, recordDate, totalAmount, recordType);
                        rentalHistory.addRecord(record);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No rental records file found or error reading: " + e.getMessage());
        }
    }
}