package utils;

import dto.CarLombok;
import dto.UserLombok;

public class TestDataFactory {

    // USERS
    private static UserLombok.UserLombokBuilder baseUser() {
        return UserLombok.builder()
                .firstName(RandomUtils.generateFirstNameFromList())
                .lastName(RandomUtils.generateLastNameFromList())
                .username(RandomUtils.generateEmail(8))
                .password(RandomUtils.generatePassword(10));
    }

    public static UserLombok validUser() {
        return baseUser().build();
    }

    public static UserLombok userWithoutEmail() {
        return baseUser().username("").build();
    }

    public static UserLombok userWithoutPassword() {
        return baseUser().password("").build();
    }

    public static UserLombok userWithoutFirstName() {
        return baseUser().firstName("").build();
    }

    public static UserLombok userWithoutLastName() {
        return baseUser().lastName("").build();
    }

    public static UserLombok invalidEmailNoAtSymbol() {
        return baseUser().username(RandomUtils.generateInvalidEmailNoAtSymbol(10)).build();
    }

    public static UserLombok invalidEmailNoDomain() {
        return baseUser().username(RandomUtils.generateInvalidEmailNoDomain(10)).build();
    }

    public static UserLombok invalidEmailWithSpace() {
        return baseUser().username(RandomUtils.generateEmail(4) + " " + RandomUtils.
                generateEmail(4)).build();
    }

    public static UserLombok invalidPasswordTooShort() {
        return baseUser().password(RandomUtils.generatePassword(1)).build();
    }

    public static UserLombok invalidPasswordTooLong() {
        return baseUser().password(RandomUtils.generatePassword(16)).build();
    }

    public static UserLombok invalidPasswordNoDigit() {
        return baseUser().password(RandomUtils.generatePasswordInvalidNoDigit(10)).build();
    }

    public static UserLombok invalidPasswordNoSymbol() {
        return baseUser().password(RandomUtils.generatePasswordInvalidNoSymbol(10)).build();
    }

    // CARS
    private static CarLombok.CarLombokBuilder baseCar() {
        return CarLombok.builder()
                .city(RandomUtils.generateCity())
                .manufacture(RandomUtils.generateManufacture())
                .model(RandomUtils.generateModel())
                .year(RandomUtils.generateYear())
                .fuel(RandomUtils.generateFuelType())
                .seats(RandomUtils.generateSeats())
                .carClass(RandomUtils.generateCarClass())
                .serialNumber(RandomUtils.generateSerialNumber())
                .pricePerDay(RandomUtils.generatePricePerDay())
                .about(RandomUtils.generateAboutText());
    }

    public static CarLombok validCar() {
        return baseCar().build();
    }

    public static CarLombok carWithoutLocation() {
        return baseCar().city("").build();
    }

    public static CarLombok carWithoutManufacture() {
        return baseCar().manufacture("").build();
    }

    public static CarLombok carWithoutModel() {
        return baseCar().model("").build();
    }

    public static CarLombok carWithInvalidYear() {
        return baseCar().year(0).build();
    }

    public static CarLombok carWithoutFuel() {
        return baseCar().fuel("").build();
    }

    public static CarLombok carWithoutSeats() {
        return baseCar().seats(0).build();
    }

    public static CarLombok carWithoutCarClass() {
        return baseCar().carClass("").build();
    }

    public static CarLombok carWithoutSerialNumber() {
        return baseCar().serialNumber("").build();
    }

    public static CarLombok carWithoutPrice() {
        return baseCar().pricePerDay(0.0).build();
    }
}
