final class SportCar extends Car {
	    private int horsepower;
	    private boolean hasTurbo;
	    
	    // Constructor
	    public SportCar(String startMake, String startModel, int startYear, int seats, int horsepower, boolean hasTurbo) {
	        super(startMake, startModel, startYear, seats);
	        this.horsepower = horsepower;
	        this.hasTurbo = hasTurbo;
	    }
	    
	    // Empty method for Assignment #2
	    public String getInfo() {
	        return ""; // Empty implementation
	    }
	}
