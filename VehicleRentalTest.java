import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleRentalTest {

    private RentalSystem rentalSystem;

    @BeforeEach
    void setUp() {
        rentalSystem = RentalSystem.getInstance();
    }

    // 1. Vehicle License Plate Validation
    @Test
    void testLicensePlate() {
  
        Vehicle v1 = new Car("Toyota", "Corolla", 2020, 5);
        Vehicle v2 = new Car("Honda", "Civic", 2021, 5);
        Vehicle v3 = new Car("Mazda", "3", 2022, 5);

        assertDoesNotThrow(() -> v1.setLicensePlate("AAA100"));
        assertDoesNotThrow(() -> v2.setLicensePlate("ABC567"));
        assertDoesNotThrow(() -> v3.setLicensePlate("ZZZ999"));

        assertEquals("AAA100", v1.getLicensePlate());
        assertEquals("ABC567", v2.getLicensePlate());
        assertEquals("ZZZ999", v3.getLicensePlate());
        assertTrue(v1.getLicensePlate().matches("^[A-Z]{3}[0-9]{3}$"));

        
        Vehicle invalid = new Car("Ford", "Focus", 2019, 5);

        assertThrows(IllegalArgumentException.class,
                () -> invalid.setLicensePlate(""));
        assertThrows(IllegalArgumentException.class,
                () -> invalid.setLicensePlate(null));
        assertThrows(IllegalArgumentException.class,
                () -> invalid.setLicensePlate("AAA1000"));
        assertThrows(IllegalArgumentException.class,
                () -> invalid.setLicensePlate("ZZZ99"));

      
        assertNull(invalid.getLicensePlate());
    }

    // 2. Rent/Return Vehicle Validation
    @Test
    void testRentAndReturnVehicle() {
        Vehicle vehicle = new Car("Toyota", "Corolla", 2020, 5);
        vehicle.setLicensePlate("AAA100");
        Customer customer = new Customer(1, "George");

       
        assertEquals(Vehicle.VehicleStatus.Available, vehicle.getStatus());

        
        boolean firstRent = rentalSystem.rentVehicle(
                vehicle, customer, LocalDate.now(), 100.0);
        assertTrue(firstRent);
        assertEquals(Vehicle.VehicleStatus.Rented, vehicle.getStatus());

       
        boolean secondRent = rentalSystem.rentVehicle(
                vehicle, customer, LocalDate.now(), 100.0);
        assertFalse(secondRent);

       
        boolean firstReturn = rentalSystem.returnVehicle(
                vehicle, customer, LocalDate.now(), 0.0);
        assertTrue(firstReturn);
        assertEquals(Vehicle.VehicleStatus.Available, vehicle.getStatus());

        
        boolean secondReturn = rentalSystem.returnVehicle(
                vehicle, customer, LocalDate.now(), 0.0);
        assertFalse(secondReturn);
    }

    // 3. Singleton Validation
    @Test
    void testSingletonRentalSystem() throws Exception {
        
        RentalSystem instance1 = RentalSystem.getInstance();
        RentalSystem instance2 = RentalSystem.getInstance();
        assertSame(instance1, instance2);

        
        Constructor<RentalSystem> constructor =
                RentalSystem.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }
}
