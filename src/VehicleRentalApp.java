import java.util.Scanner;

public class VehicleRentalApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    // declaring our scanner object by the name 'sc'
        RentalSystem system = RentalSystem.getInstance();  // NOTE: THIS IS THE NEW SINGLETON WAY  
        
        // Test Singleton
        RentalSystem system1 = RentalSystem.getInstance();
        RentalSystem system2 = RentalSystem.getInstance();
        System.out.println("Same instance? " + (system1 == system2)); // This is supposed to print "true" for me
        

        while (true) {
            System.out.println("\n ***** Vehicle Rental System *****");
            System.out.println("1: Add Vehicle");
            System.out.println("2: Rent Vehicle");
            System.out.println("3: Return Vehicle");
            System.out.println("4: Display Available Vehicles");
            System.out.println("5: Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine();   // reads the user's input and assigns it to the variable 'choice'
            switch (choice) {
                case "1":
                    addVehicleFlow(sc, system);
                    break;
                    
                case "2":
                    System.out.print("Enter your license plate to rent a vehicle please: ");
                    String rentPlate = sc.nextLine();
                    System.out.print("Enter your name please: ");
                    String name = sc.nextLine();
                    if (system.rentVehicle(rentPlate, name)) {
                        System.out.println("Vehicle " + rentPlate + " has been rented.");
                    } else {
                        System.out.println("Sorry. You are unable to rent vehicle (not found or not available).");
                    }
                    break;
                    
                case "3":
                    System.out.print("Enter license plate to return: ");
                    String rentPlate2 = sc.nextLine();
                    if (system.returnVehicle(rentPlate2)) {
                        System.out.println("Vehicle " + rentPlate2 + " has been returned.");
                    } else {
                        System.out.println("Unable to return vehicle (not found or not rented).");
                    }
                    break;
                    
                case "4":
                    system.displayAvailableVehicles();  // prints everything itself
                    break;

                case "5":
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addVehicleFlow(Scanner sc, RentalSystem system) {
        System.out.println("Select vehicle type: 1) Car  2) Minibus");
        String type = sc.nextLine();

        System.out.print("Enter make: ");
        String make = sc.nextLine();

        System.out.print("Enter model: ");
        String model = sc.nextLine();

        System.out.print("Enter year: ");
        int year = readInt(sc);

        Vehicle v = null;
        if ("1".equals(type)) {
            System.out.print("Enter number of seats: ");
            int seats = readInt(sc);
            v = new Car(make, model, year, seats);
        } else if ("2".equals(type)) {
            System.out.print("Is accessible (true/false): ");
            boolean accessible = Boolean.parseBoolean(sc.nextLine());
            v = new Minibus(make, model, year, accessible);
        } else {
            System.out.println("Unknown type.");
            return;
        }

        
        System.out.print("Enter license plate: ");
        String plate = sc.nextLine();
        v.setLicensePlate(plate);

        if (system.addVehicle(v)) {
            System.out.println("Vehicle added successfully.");
        } else {
            System.out.println("Vehicle could not be added (duplicate plate?).");
        }
    }

    private static int readInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid integer: ");
            }
        }
    }
}
