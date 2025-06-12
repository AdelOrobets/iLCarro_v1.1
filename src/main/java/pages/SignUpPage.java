package pages;

import dto.UserLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    @FindBy(xpath = "//form[.//button[text()='Y’alla!']]")
    WebElement registrationForm;

    @FindBy(id = "name")
    WebElement inputName;

    @FindBy(id = "lastName")
    WebElement inputLastName;

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkBox;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public SignUpPage enterFirstName(String name) {
        inputName.clear();
        inputName.sendKeys(name);
        return this;
    }

    public SignUpPage enterLastName(String lastName) {
        inputLastName.clear();
        inputLastName.sendKeys(lastName);
        return this;
    }

    public SignUpPage enterEmail(String email) {
        inputEmail.clear();
        inputEmail.sendKeys(email);
        return this;
    }

    public SignUpPage enterPassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
        return this;
    }

    public SignUpPage typeSignUpForm(UserLombok user) {
        fillCredentials(user);
        clickCheckBox();
        clickSignUpButton();
        return this;
    }

    public SignUpPage fillCredentials(UserLombok user) {
        enterFirstName(user.getFirstName());
        blurField(inputName, inputLastName);
        enterLastName(user.getLastName());
        blurField(inputLastName, inputEmail);
        enterEmail(user.getUsername());
        blurField(inputEmail, inputPassword);
        enterPassword(user.getPassword());
        return this;
    }

    private void blurField(WebElement from, WebElement to) {
        from.click();
        to.click();
    }

    public SignUpPage clickCheckBox() {
        if (!checkBox.isSelected()) {
            int width = checkBox.getRect().getWidth();
            //int height = checkBox.getRect().getHeight();
            new Actions(driver)
                    .moveToElement(checkBox, -width / 20 * 9, 0)
                    .click()
                    .perform();
        }
        return this;
    }

    public SignUpPage clickSignUpButton() {
        btnYalla.click();
        return this;
    }

    // Checks
    public boolean isRegistrationFormDisplayed() {
        return waitForVisibility(registrationForm, 10);
    }

    public boolean btnYallaIsEnabled() {
        return elementIsEnabled(btnYalla);
    }
}
