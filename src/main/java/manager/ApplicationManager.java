package manager;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LetCarWorkPage;
import pages.LoginPage;
import pages.SignUpPage;
import utils.HeaderMenuItem;

import java.time.Duration;

@Getter
public class ApplicationManager {

    public WebDriver driver;
    public SignUpPage signUpPage;
    public LoginPage loginPage;
    public LetCarWorkPage letCarWorkPage;

    public WebDriver initDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return driver;
    }

    public void openSignUpPage() {
        signUpPage = new HomePage(driver).clickHeaderMenuItem(HeaderMenuItem.SIGN_UP);
    }

    public void openLoginPage() {
        loginPage = new HomePage(driver).clickHeaderMenuItem(HeaderMenuItem.LOGIN);
    }

    public void openLetCarWorkPage() {
        letCarWorkPage = new HomePage(driver).clickHeaderMenuItem(HeaderMenuItem.LET_CAR_WORK);
    }

    @BeforeMethod
    public void setUpTest() {
        if (driver == null) {
            driver = initDriver();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
