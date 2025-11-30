public class Car extends Vehicle implements Rentable{
	
	private int numSeats;  //our class' private data field / attribute
	
	// Our class' four-parameter constructor
	public Car (String startMake, String startModel, int startYear, int numSeats) {
		super(startMake, startModel, startYear);
		if (numSeats <= 0) // seats must not be negative
			{System.out.println("Seats must be at least one");}
	
	}
	
	// seat getter
	public int getNumSeats()
	{
	return numSeats;
	}
	
	// overriding the parent class' getInfo() method
	public String getInfo() {
		String headRows = "License plate\tMake\tModel\tYear\tStatus\tNumber of Seats";
		String details = getLicensePlate() + "\t" +
                getMake() + "\t" +
                getModel() + "\t" +
                getYear() + "\t" +
                getStatus() + "\t" +
                getNumSeats();		
		return headRows +"\n" + details;
	}
	
// implementing the methods in our 'Rentable' interface
	
	// method for renting car objects
	@Override
	public void rentVehicle() {
        if (getStatus() == Vehicle.Status.Available) {
            setStatus(Vehicle.Status.Rented);
            System.out.println("Car " + getLicensePlate() + " has been rented.");
        } else {
            System.out.println("Car " + getLicensePlate() + " is not available.");
        }
    }

	
	// method for returning car objects
	@Override
    public void returnVehicle() {
        if (getStatus() == Vehicle.Status.Rented) {
            setStatus(Vehicle.Status.Available);
            System.out.println("Car " + getLicensePlate() + " has been returned.");
        } else {
            System.out.println("Car " + getLicensePlate() + " is not currently rented.");
        }
    }
	}