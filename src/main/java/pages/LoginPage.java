package pages;

import dto.UserLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//form[.//button[text()='Y’alla!']]")
    WebElement loginForm;

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterEmail(String email) {
        inputEmail.clear();
        inputEmail.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
        return this;
    }

    public void fillCredentials(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }

    public void typeLoginForm(UserLombok user) {
        fillCredentials(user.getUsername(), user.getPassword());
        clickLoginButton();
    }

    public void clickLoginButton() {
        btnYalla.click();
    }

    public boolean isLoginFormDisplayed() {
        return waitForVisibility(loginForm, 10);
    }

    public boolean btnYallaIsEnabled() {
        return elementIsEnabled(btnYalla);
    }
}

