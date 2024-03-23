import java.util.Scanner;

public class CarRentalSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Kamal and Friends Car Rental System!");

        // Step 1: Verification
        if (verifyCustomer(scanner)) {
            // Step 2: Car Selection
            Car chosenCar = selectCar(scanner);
            if (chosenCar != null) {
                // Step 3: Booking Confirmation
                confirmBooking(scanner, chosenCar);
            }
        }

        System.out.println("Thank you for using our system!");
        scanner.close();
    }

    private static boolean verifyCustomer(Scanner scanner) {
        System.out.print("Enter Aadhar Card Number (12 digits): ");
        String aadhar = scanner.nextLine();
        if (!isValidAadhar(aadhar)) {
            System.out.println("Invalid Aadhar number format!");
            return false;
        }

        System.out.print("Enter PAN Card Number (10 digits - First 5 & Last digits Alphabets, rest Numbers): ");
        String pan = scanner.nextLine();
        if (!isValidPAN(pan)) {
            System.out.println("Invalid PAN number format!");
            return false;
        }

        System.out.print("Enter Driving License Number (15 digits - First 2 digits matching State Code): ");
        String license = scanner.nextLine();
        if (!isValidDrivingLicense(license)) {
            System.out.println("Invalid Driving License number format!");
            return false;
        }

        System.out.println("Verification Successful!");
        return true;
    }

    private static boolean isValidAadhar(String aadhar) {
        return aadhar.length() == 12 && aadhar.matches("[0-9]+");
    }

    private static boolean isValidPAN(String pan) {
        return pan.length() == 10 && pan.matches("[A-Z]{5}[0-9]{4}[A-Z]");
    }

    private static boolean isValidDrivingLicense(String license) {
        return license.length() == 15 && license.matches("[A-Z]{2}[0-9]{13}");
    }

    // Replace this with logic to display and choose a car from available cars
    private static Car selectCar(Scanner scanner) {
        System.out.println("Car selection logic goes here...");
        // Simulate choosing a car
        Car chosenCar = new Car("Model X", 5, 100);
        return chosenCar;
    }

    private static void confirmBooking(Scanner scanner, Car chosenCar) {
        System.out.println("You have chosen: " + chosenCar);

        // Simulate choosing rental period
        int days = 3;

        System.out.println("Rental period: " + days + " days");
        System.out.println("Estimated cost: $" + days * chosenCar.getPrice());

        System.out.print("Confirm booking? (y/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            System.out.println("Booking confirmed! Please proceed to payment.");
            // Payment logic would go here (replace with integration to a payment gateway)
        } else {
            System.out.println("Booking cancelled.");
        }
    }
}

class Car {
    private String model;
    private int capacity;
    private int pricePerDay;

    public Car(String model, int capacity, int pricePerDay) {
        this.model = model;
        this.capacity = capacity;
        this.pricePerDay = pricePerDay;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return pricePerDay;
    }
}
