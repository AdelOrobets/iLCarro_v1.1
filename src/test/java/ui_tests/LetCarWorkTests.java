package ui_tests;

import data_provider.CarsDataProviders;
import dto.CarLombok;
import manager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestDataFactory;
import utils.TestNGListener;

@Listeners(TestNGListener.class)
public class LetCarWorkTests extends ApplicationManager {

    private static final Logger logger = LoggerFactory.getLogger(LetCarWorkTests.class);

    @BeforeMethod
    public void precondition() {
        openSignUpPage();
        signUpPage.typeSignUpForm(TestDataFactory.validUser());
        signUpPage.clickBtnOkPopupMsg();
    }

    //Positive tests
    @Test
    public void testSuccessful_addNewCar() {
        CarLombok car = TestDataFactory.validCar();
        openLetCarWorkPage();
        letCarWorkPage.addNewCarInForm(car);
        Assert.assertTrue(letCarWorkPage.popUpMessageContains("Car was added"),
                "Car was not added");
    }

    @Test(dataProvider = "addNewCarDPFromFile", dataProviderClass = CarsDataProviders.class)
    public void testSuccessful_addNewCarFromFileCSV(CarLombok car) {
        openLetCarWorkPage();
        logger.info("Test data: {}", car);
        letCarWorkPage.fillCarFormFromFileCSV(car);
        letCarWorkPage.clickSubmitButton();
        Assert.assertTrue(letCarWorkPage.popUpMessageContains("Car was added"),
                "Car was not added");
    }

    //Negative tests
    @Test
    public void testAddNewCar_withoutLocation(){
        CarLombok car = TestDataFactory.carWithoutLocation();
        openLetCarWorkPage();
        letCarWorkPage.addNewCarInForm(car);
        Assert.assertTrue(letCarWorkPage.errorMessageContains("Wrong address"),
                "Expected error message is not displayed");
        Assert.assertFalse(letCarWorkPage.btnSubmitEnabled(),
                "Error: button Submit enabled with empty location");
    }

    @Test
    public void testAddNewCar_withoutManufacture(){
        CarLombok car = TestDataFactory.carWithoutManufacture();
        openLetCarWorkPage();
        letCarWorkPage.addNewCarInForm(car);
        Assert.assertTrue(letCarWorkPage.errorMessageContains("Make is required"),
                "Expected error message is not displayed");
        Assert.assertFalse(letCarWorkPage.btnSubmitEnabled(),
                "Error: button Submit enabled with empty Manufacture");
    }

    @Test
    public void testAddNewCar_withoutModel() {
        CarLombok car = TestDataFactory.carWithoutModel();
        openLetCarWorkPage();
        letCarWorkPage.addNewCarInForm(car);
        Assert.assertTrue(letCarWorkPage.errorMessageContains("Model is required"),
                "Expected error message is not displayed");
        Assert.assertFalse(letCarWorkPage.btnSubmitEnabled(),
                "Error: button Submit enabled with empty Model");
    }

    @Test
    public void testAddNewCar_withInvalidYear() {
        CarLombok car = TestDataFactory.carWithInvalidYear();
        openLetCarWorkPage();
        letCarWorkPage.addNewCarInForm(car);
        Assert.assertTrue(letCarWorkPage.errorMessageContains("Year is required"),
                "Expected error message is not displayed");
        Assert.assertFalse(letCarWorkPage.btnSubmitEnabled(),
                "Error: button Submit enabled with invalid Year");
    }

    @Test
    public void testAddNewCar_withoutFuel() {
        CarLombok car = TestDataFactory.carWithoutFuel();
        openLetCarWorkPage();
        letCarWorkPage.addNewCarInForm(car);
        Assert.assertTrue(letCarWorkPage.errorMessageContains("Fuel is required"),
                "Expected error message is not displayed");
        Assert.assertFalse(letCarWorkPage.btnSubmitEnabled(),
                "Error: button Submit enabled with empty Fuel");
    }

    @Test
    public void testAddNewCar_withoutSeats() {
        CarLombok car = TestDataFactory.carWithoutSeats();
        openLetCarWorkPage();
        letCarWorkPage.addNewCarInForm(car);
        Assert.assertTrue(letCarWorkPage.errorMessageContains("Seats are required"),
                "Expected error message is not displayed");
        Assert.assertFalse(letCarWorkPage.btnSubmitEnabled(),
                "Error: button Submit enabled with null Seats");
    }

    @Test
    public void testAddNewCar_withoutCarClass() {
        CarLombok car = TestDataFactory.carWithoutCarClass();
        openLetCarWorkPage();
        letCarWorkPage.addNewCarInForm(car);
        Assert.assertTrue(letCarWorkPage.errorMessageContains("Class is required"),
                "Expected error message is not displayed");
        Assert.assertFalse(letCarWorkPage.btnSubmitEnabled(),
                "Error: button Submit enabled with empty CarClass");
    }

    @Test
    public void testAddNewCar_withoutSerialNumber() {
        CarLombok car = TestDataFactory.carWithoutSerialNumber();
        openLetCarWorkPage();
        letCarWorkPage.addNewCarInForm(car);
        Assert.assertTrue(letCarWorkPage.errorMessageContains("Serial number is required"),
                "Expected error message is not displayed");
        Assert.assertFalse(letCarWorkPage.btnSubmitEnabled(),
                "Error: button Submit enabled with empty SerialNumber");
    }

    @Test
    public void testAddNewCar_withoutPrice() {
        CarLombok car = TestDataFactory.carWithoutPrice();
        openLetCarWorkPage();
        letCarWorkPage.addNewCarInForm(car);
        Assert.assertTrue(letCarWorkPage.errorMessageContains("Price is required"),
                "Expected error message is not displayed");
        Assert.assertFalse(letCarWorkPage.btnSubmitEnabled(),
                "Error: button Submit enabled with null Price");
    }
}
