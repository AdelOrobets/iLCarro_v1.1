package pages;

import dto.CarLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;

public class LetCarWorkPage extends BasePage {

    public LetCarWorkPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(LetCarWorkPage.class);

    @FindBy(id = "pickUpPlace")
    WebElement inputCity;

    @FindBy(id = "make")
    WebElement inputManufacture;

    @FindBy(id = "model")
    WebElement inputModel;

    @FindBy(id = "year")
    WebElement inputYear;

    @FindBy(id = "fuel")
    WebElement selectFuel;

    @FindBy(id = "seats")
    WebElement inputSeats;

    @FindBy(id = "class")
    WebElement inputCarClass;

    @FindBy(id = "serialNumber")
    WebElement inputRegNumber;

    @FindBy(id = "price")
    WebElement inputPrice;

    @FindBy(id = "about")
    WebElement inputAboutText;

    @FindBy(id = "//label[contains(text(), 'Add photos')]")
    WebElement inputPhoto;

    @FindBy(xpath = "//button[@type='submit' and text()='Submit']")
    WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class, 'error') and contains(text(), 'Wrong address')]")
    WebElement errorMsg;


    public void addNewCarInForm(CarLombok car) {
        logger.info("Filling car form with test data:");
        logger.info("City: {}", car.getCity());
        logger.info("Manufacture: {}", car.getManufacture());
        logger.info("Model: {}", car.getModel());
        logger.info("Year: {}", car.getYear());
        logger.info("Fuel: {}", car.getFuel());
        logger.info("Seats: {}", car.getSeats());
        logger.info("Class: {}", car.getCarClass());
        logger.info("Reg Number: {}", car.getSerialNumber());
        logger.info("Price: {}", car.getPricePerDay());
        logger.info("About: {}", car.getAbout());

        inputCity.sendKeys(car.getCity());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(String.valueOf(car.getYear()));

        Select fuelSelect = new Select(selectFuel);
        fuelSelect.selectByVisibleText(car.getFuel());

        inputSeats.sendKeys(String.valueOf(car.getSeats()));
        inputCarClass.sendKeys(car.getCarClass());
        inputRegNumber.sendKeys(car.getSerialNumber());
        inputPrice.sendKeys(String.valueOf(car.getPricePerDay()));
        inputAboutText.sendKeys(car.getAbout());
        addPhoto(car.getImage());
        submitButton.click();
    }

    public boolean btnSubmitEnabled() {
        return elementIsEnabled(submitButton);
    }

    public void fillCarFormFromFileCSV(CarLombok car) {
        inputCity.sendKeys(car.getCity());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(String.valueOf(car.getYear()));
        inputSeats.sendKeys(String.valueOf(car.getSeats()));
        inputCarClass.sendKeys(car.getCarClass());
        inputRegNumber.sendKeys(car.getSerialNumber());
        inputPrice.sendKeys(String.valueOf(car.getPricePerDay()));
        inputAboutText.sendKeys(car.getAbout());
    }

    private void addPhoto(String fileName) {
        String path = Paths.get("src", "test", "resources", "photos", fileName)
                .toAbsolutePath()
                .toString();
        inputPhoto.sendKeys(path);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }
}
