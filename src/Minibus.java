
public class Minibus extends Vehicle implements Rentable{

	private boolean isAccessible;  // our class' private data field / attribute
	
	// our four-parameter constructor
	public Minibus (String startMake, String startModel, int startYear, boolean accessibility) {
		super(startMake, startModel, startYear);
		this.isAccessible = accessibility;		
	}	
	
	// method to ensure the bus is wheel chair accessible 
	public boolean getIsAccessible()
	{
		return isAccessible;
	}
	
	// overriding the parent class' getInfo() method 
	public String getInfo() {
		String headRows = "License plate\tMake\tModel\tYear\tStatus\tAccessibility";
		String details = getLicensePlate() + "\t" +
                getMake() + "\t" +
                getModel() + "\t" +
                getYear() + "\t" +
                getStatus() + "\t" +
                getIsAccessible();		
		return headRows +"\n" + details;
	}
	
	// implementing the methods in our 'Rentable' interface
	
		// method for renting Minibus objects
		@Override
		public void rentVehicle() {
	        if (getStatus() == Vehicle.Status.Available) {
	            setStatus(Vehicle.Status.Rented);
	            System.out.println("Bus " + getLicensePlate() + " has been rented.");
	        } else {
	            System.out.println("Bus " + getLicensePlate() + " is not available.");
	        }
	    }

		
		// method for returning Minibus objects
		@Override
	    public void returnVehicle() {
	        if (getStatus() == Vehicle.Status.Rented) {
	            setStatus(Vehicle.Status.Available);
	            System.out.println("Bus " + getLicensePlate() + " has been returned.");
	        } else {
	            System.out.println("Bus " + getLicensePlate() + " is not currently rented.");
	        }
	    }
}
