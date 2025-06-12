package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HeaderMenuItem;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;

    @FindBy(xpath = "//div[@class='dialog-container']")
    protected WebElement popUpMessage;

    @FindBy(xpath = "//button[normalize-space()='Ok']")
    WebElement btnOkPopupMsg;

    @FindBy(xpath = "//div[contains(@class, 'error')]")
    List<WebElement> errorMessagesForInput;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    @SuppressWarnings("unchecked")
    public <T extends BasePage> T clickBtnOkPopupMsg() {
        btnOkPopupMsg.click();
        return (T) this;
    }

    public boolean popUpMessageContains(String expectedText) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(popUpMessage));
        System.out.println("Popup message: " + popUpMessage.getText());
        return popUpMessage.getText().contains(expectedText);
    }

    public <T extends BasePage> T clickHeaderMenuItem(HeaderMenuItem headerMenuItem) {
        String locator = headerMenuItem.getLocator();
        WebElement element = driver.findElement(By.xpath(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        if (headerMenuItem == HeaderMenuItem.LOGOUT) {
            return null;
        }
        return switch (headerMenuItem) {
            case SEARCH -> (T) new HomePage(driver);
            case LET_CAR_WORK -> (T) new LetCarWorkPage(driver);
            case TERMS -> (T) new TermsOfUsePage(driver);
            case SIGN_UP -> (T) new SignUpPage(driver);
            case LOGIN -> (T) new LoginPage(driver);
            case LOGOUT -> (T) new LogoutPage(driver);
            case DELETE_ACCOUNT -> (T) new DeleteAccountPage(driver);
            default -> throw new IllegalArgumentException("Invalid header menu item: " + headerMenuItem);
        };
    }

    public boolean elementIsEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean errorMessageContains(String expectedText) {
        if (waitUntilNotEmpty(errorMessagesForInput, 10)) {
            return errorMessagesForInput.stream()
                    .anyMatch(error -> error.getText().contains(expectedText));
        }
        return false;
    }

    protected boolean waitForVisibility(WebElement element, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean waitUntilNotEmpty(List<WebElement> elements, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(driver -> !elements.isEmpty());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
