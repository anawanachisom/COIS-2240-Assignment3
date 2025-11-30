
public class PickupTruck extends Vehicle{
	private double cargoSize;
    private boolean hasTrailer;
    
    // Constructor
    public PickupTruck(String startMake, String startModel, int startYear, double cargoSize, boolean hasTrailer) {
        super(startMake, startModel, startYear);
        this.cargoSize = cargoSize;
        this.hasTrailer = hasTrailer;
    }
    
    // Empty method for Assignment #2
    public String getInfo() {
        return ""; // Empty implementation
    }
}
