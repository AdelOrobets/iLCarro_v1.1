package utils;

import java.security.SecureRandom;
import java.util.List;

public class RandomUtils {

    private static final SecureRandom random = new SecureRandom();

    // firstName for user
    private static final List<String> FIRST_NAMES = List.of(
            "Alice", "Shai", "Aleksey", "Diana", "Eitan", "Moshe",
            "George", "Alex", "Ivan", "Julia", "Doris", "Adel",
            "Mike", "Nina", "David", "Hana", "Maria", "Noa");

    public static String generateFirstNameFromList() {
        return FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size()));
    }

    // lastName for user
    private static final List<String> LAST_NAMES = List.of(
            "Cohen", "Levi", "Mizrahi", "Avraham", "Biton", "Peretz"
            , "BenDavid", "Malka", "Azoulay", "Elbaz", "Sharabi", "Dayan",
            "Mor", "Haim", "Zohar", "Halimi", "Alon", "Nahum");

    public static String generateLastNameFromList() {
        return LAST_NAMES.get(random.nextInt(LAST_NAMES.size()));
    }

    // Generates valid random email
    public static String generateEmail(int length) {
        return generateRandomString(length,
                "abcdefghijklmnopqrstuvwxyz0123456789") + "@gmail.com";
    }

    // Generates invalid random email
    public static String generateInvalidEmailNoAtSymbol(int length) {
        return generateRandomString(length,
                "abcdefghijklmnopqrstuvwxyz0123456789") + "gmail.com";
    }

    public static String generateInvalidEmailNoDomain(int length) {
        return generateRandomString(length,
                "abcdefghijklmnopqrstuvwxyz0123456789") + "@gmail";
    }

    // Generates valid random password
    public static String generatePassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "@$#^&*!";

        String allChars = upper + lower + digits + special;
        StringBuilder password = new StringBuilder();

        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(special.charAt(random.nextInt(special.length())));

        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }
        return password.toString();
    }

    // Generates invalid random password
    public static String generatePasswordInvalidNoSymbol(int length) {
        return generateRandomString(length,
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
    }

    public static String generatePasswordInvalidNoDigit(int length) {
        return generateRandomString(length,
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@$#^&*!");
    }

    private static String generateRandomString(int length, String characters) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    // generate test data for Let the car work
    private static final List<String> cities = List.of("Tel Aviv", "Jerusalem", "Haifa", "Eilat", "Beersheba");
    private static final List<String> manufacturers = List.of("Toyota", "Honda", "Ford", "BMW", "Mazda");
    private static final List<String> models = List.of("Corolla", "Civic", "Focus", "X5", "CX-5");
    private static final List<String> fuels = List.of("Diesel", "Petrol", "Hybrid", "Electric", "Gas");
    private static final List<String> classes = List.of("Economy", "Standard", "Luxury", "SUV");

    public static String generateCity() {
        return cities.get(random.nextInt(cities.size()));
    }

    public static String generateManufacture() {
        return manufacturers.get(random.nextInt(manufacturers.size()));
    }

    public static String generateModel() {
        return models.get(random.nextInt(models.size()));
    }

    public static int generateYear() {
        return 2010 + random.nextInt(15); // From 2010 to 2024
    }

    public static String generateFuelType() {
        return fuels.get(random.nextInt(fuels.size()));
    }

    public static int generateSeats() {
        return 2 + random.nextInt(7); // 2 to 8 seats
    }

    public static String generateCarClass() {
        return classes.get(random.nextInt(classes.size()));
    }

    public static String generateSerialNumber() {
        return "SN-" + random.nextInt(1_000_000);
    }

    public static double generatePricePerDay() {
        return 10.00 + random.nextInt(30); // Price between 10 and 30
    }

    public static String generateAboutText() {
        return "This is a clean and reliable car, great for trips and city driving.";
    }
}
