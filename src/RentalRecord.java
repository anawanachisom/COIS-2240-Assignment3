import java.time.LocalDate;

public class RentalRecord {
    private Vehicle vehicle;
    private Customer customer;
    private LocalDate recordDate;
    private double totalAmount;
    private String recordType; // "RENT" or "RETURN"
    
    // Constructor
    public RentalRecord(Vehicle vehicle, Customer customer, LocalDate recordDate, double totalAmount, String recordType) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.recordDate = recordDate;
        this.totalAmount = totalAmount;
        this.recordType = recordType;
    }
    
    // Empty method for Assignment #2
    public String toString() {
        return ""; // Empty implementation
    }
}