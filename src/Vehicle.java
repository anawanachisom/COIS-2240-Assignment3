
public abstract class Vehicle {
// Our vehicle class has five private data fields / attributes
	
	private String licensePlate;
	private String make;
	private String model;
	private int year;
	
	public enum Status{Available, Held, Rented, UnderMaintenance, OutOfService}	
	private Status status;
	
	// Our class' three-parameter constructor
	
		public Vehicle (String startMake, String startModel, int startYear) 
		{	
		year = startYear;
		
		// ensuring the first letter of the make is in uppercase
		String firstMake = startMake.substring(0, 1);
		String restMake = startMake.substring(1);
		make = firstMake + restMake;
		
		// ensuring the first letter of the model is in uppercase
		String firstModel = startModel.substring(0, 1);
		String restModel = startModel.substring(1);
		model = firstModel + restModel;
		
		status = Status.Available;  // initialize status to "Available" 
		
		licensePlate = "";  // initialize license plate to null
		
		}
		
		// License plate setter
		public void setLicensePlate(String plate) 
			{
			// Convert to uppercase here
			plate = plate.toUpperCase();
			this.licensePlate = plate;
			}
		
		// License plate getter
		public String getLicensePlate() 
			{
			return licensePlate;
			}
			
		// Status setter	
		public void setStatus(Status stat) 
			{
			status = stat;	
			}
		
		// Status getter
		public Status getStatus()
			{
			return status;	
			}
		
		// Make getter
		public String getMake()
			{
			return make;
			}
		
		// Model getter
		public String getModel()
			{
			return model;
			}
		
		// Year getter
		public int getYear()
			{
			return year;
			}
 
		// Our method for returning the vehicles' details
		public String getInfo()
		{
			String headRows = "License plate\tMake\tModel\tYear\tStatus";
			String details = licensePlate + "\t" +
                    make + "\t" +
                    model + "\t" +
                    year + "\t" +
                    status;		
			return headRows +"\n" + details;
		}
}
