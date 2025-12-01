public abstract class Vehicle {
    private String licensePlate;
    private String make;
    private String model;
    private int year;
    private VehicleStatus status;

    public enum VehicleStatus { Available, Held, Rented, UnderMaintenance, OutOfService }

    public Vehicle(String make, String model, int year) {
//    	if (make == null || make.isEmpty())
//    		this.make = null;
//    	else
//    		this.make = make.substring(0, 1).toUpperCase() + make.substring(1).toLowerCase();
//    	
//    	if (model == null || model.isEmpty())
//    		this.model = null;
//    	else
//    		this.model = model.substring(0, 1).toUpperCase() + model.substring(1).toLowerCase();
    	
    	make = capitalize(make);        // Use helper method
        model = capitalize(model);
        this.year = year;
        this.status = VehicleStatus.Available;
        this.licensePlate = null;
    }
    
 // THIS PRIVATE HELPER METHOD HELPS US CAPITALIZE THE FIRST LETTER OF MODEL AND MAKE
    private String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String firstLetter = input.substring(0, 1).toUpperCase();
        String rest = input.substring(1).toLowerCase();
        return firstLetter + rest;
    }

    public Vehicle() {
        this(null, null, 0);
    }

    public void setLicensePlate(String plate) {
        this.licensePlate = plate == null ? null : plate.toUpperCase();
    }

    public void setStatus(VehicleStatus status) {
    	this.status = status;
    }

    public String getLicensePlate() { return licensePlate; }

    public String getMake() { return make; }

    public String getModel() { return model;}

    public int getYear() { return year; }

    public VehicleStatus getStatus() { return status; }

    public String getInfo() {
        return "| " + licensePlate + " | " + make + " | " + model + " | " + year + " | " + status + " |";
    }

}
