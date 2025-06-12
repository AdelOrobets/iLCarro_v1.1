package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.HeaderMenuItem;
import utils.TestDataFactory;
import utils.TestNGListener;

@Listeners(TestNGListener.class)
public class LoginTests extends ApplicationManager {

    public UserLombok userRegistration() {
        openSignUpPage();
        UserLombok user = TestDataFactory.validUser();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickBtnOkPopupMsg();
        new HomePage(getDriver()).clickHeaderMenuItem(HeaderMenuItem.LOGOUT);
        return user;
    }

    // Positive tests
    @Test
    public void testLoginFormDisplayed() {
        openLoginPage();
        Assert.assertTrue(loginPage.isLoginFormDisplayed(),
                "Login form is not displayed");
    }

    @Test
    public void testUserCanLoginAfterRegistration() {
        UserLombok validUser = userRegistration();
        openLoginPage();
        loginPage.typeLoginForm(validUser);
        Assert.assertTrue(loginPage.popUpMessageContains("Logged in success"),
                "Popup message does not confirm successful login");
    }

    // Negative tests
    @Test
    public void testUserLogin_unregisteredUser() {
        UserLombok unregisteredUser = TestDataFactory.validUser();
        openLoginPage();
        loginPage.typeLoginForm(unregisteredUser);
        Assert.assertTrue(loginPage.popUpMessageContains("Login or Password incorrect"),
                "Popup message does not contain expected text");
    }

    @Test
    public void testUserLogin_wrongPassword() {
        UserLombok validUser = userRegistration();
        String validEmail = validUser.getUsername();
        String wrongPassword = "WrongPass1$";
        openLoginPage();
        loginPage.fillCredentials(validEmail, wrongPassword);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.popUpMessageContains("Login failed"),
                "Popup message does not contain expected text");
    }

    @Test
    public void testUserLogin_uppercaseEmail() {
        UserLombok validUser = userRegistration();
        String upperCaseEmail = validUser.getUsername().toUpperCase();
        String password = validUser.getPassword();
        openLoginPage();
        loginPage.fillCredentials(upperCaseEmail, password);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.popUpMessageContains("Login failed"),
                "Popup message does not contain expected text");
    }

    @Test
    public void testUserLogin_withEmptyEmail() {
        UserLombok invalidUser = TestDataFactory.userWithoutEmail();
        openLoginPage();
        loginPage.typeLoginForm(invalidUser);
        Assert.assertTrue(loginPage.errorMessageContains("Email is required"),
                                    "Expected error message is not displayed");
        Assert.assertFalse(loginPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with invalid input");
    }

    @Test
    public void testUserLogin_withEmptyPassword() {
        UserLombok invalidUser = TestDataFactory.userWithoutPassword();
        openLoginPage();
        loginPage.typeLoginForm(invalidUser);
        Assert.assertTrue(loginPage.errorMessageContains("Password is required"),
                                    "Expected error message is not displayed");
        Assert.assertFalse(loginPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with invalid input");
    }

    @Test
    public void testUserLogin_withInvalidEmailFormat() {
        UserLombok invalidUser = TestDataFactory.invalidEmailNoAtSymbol();
        openLoginPage();
        loginPage.typeLoginForm(invalidUser);
        Assert.assertTrue(loginPage.errorMessageContains("It'snot look like email"),
                                    "Expected error message is not displayed");
        Assert.assertFalse(loginPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with invalid input");
    }

    /**
     * No validation message is shown near the input field
     * Instead, a generic popup error appears after clicking the Login button
     * This behavior is likely a bug.
     * The application should display specific field validation messages
     * before attempting to submit the form
     */
    @Test
    public void testUserLogin_invalidEmailDomain() {
        UserLombok invalidUser = TestDataFactory.invalidEmailNoDomain();
        openLoginPage();
        loginPage.typeLoginForm(invalidUser);
        Assert.assertTrue(loginPage.errorMessageContains("It'snot look like email"),
                                    "Expected error message is not displayed");
        Assert.assertFalse(loginPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with invalid input");
    }

    @Test
    public void testUserLogin_invalidEmailWithSpace() {
        UserLombok invalidUser = TestDataFactory.invalidEmailWithSpace();
        openLoginPage();
        loginPage.typeLoginForm(invalidUser);
        Assert.assertTrue(loginPage.errorMessageContains("It'snot look like email"),
                                "Expected error message is not displayed");
        Assert.assertFalse(loginPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with invalid input");
    }

    /**
     * No validation message is shown near the input field
     * Instead, a generic popup error appears after clicking the Login button
     * This behavior is likely a bug.
     * The application should display specific field validation messages
     * before attempting to submit the form
     */
    @Test
    public void testUserLogin_invalidPasswordShort() {
        UserLombok invalidUser = TestDataFactory.invalidPasswordTooShort();
        openLoginPage();
        loginPage.typeLoginForm(invalidUser);
        Assert.assertTrue(loginPage.errorMessageContains("Password must contain minimum 8 symbols"),
                                "Expected error message is not displayed");
        Assert.assertFalse(loginPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with invalid input");
    }

    /**
     * No validation message is shown near the input field
     * Instead, a generic popup error appears after clicking the Login button
     * This behavior is likely a bug.
     * The application should display specific field validation messages
     * before attempting to submit the form
     */
    @Test
    public void testUserLogin_invalidPasswordLong() {
        UserLombok invalidUser = TestDataFactory.invalidPasswordTooLong();
        openLoginPage();
        loginPage.typeLoginForm(invalidUser);
        Assert.assertTrue(loginPage.errorMessageContains("Password must contain maximum 15 symbols"),
                                "Expected error message is not displayed");
        Assert.assertFalse(loginPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with invalid input");
    }

    /**
     * No validation message is shown near the input field
     * Instead, a generic popup error appears after clicking the Login button
     * This behavior is likely a bug.
     * The application should display specific field validation messages
     * before attempting to submit the form
     */
    @Test
    public void testUserLogin_invalidPasswordNoDigit() {
        UserLombok invalidUser = TestDataFactory.invalidPasswordNoDigit();
        openLoginPage();
        loginPage.typeLoginForm(invalidUser);
        Assert.assertTrue(loginPage.errorMessageContains("Password must contain 1 uppercase letter,"
                        + "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"),
                                 "Expected error message is not displayed");
        Assert.assertFalse(loginPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with invalid input");
    }

    /**
     * No validation message is shown near the input field
     * Instead, a generic popup error appears after clicking the Login button
     * This behavior is likely a bug.
     * The application should display specific field validation messages
     * before attempting to submit the form
     */
    @Test
    public void testUserLogin_invalidPasswordNoSymbol() {
        UserLombok invalidUser = TestDataFactory.invalidPasswordNoSymbol();
        openLoginPage();
        loginPage.typeLoginForm(invalidUser);
        Assert.assertTrue(loginPage.errorMessageContains("Password must contain 1 uppercase letter,"
                        + "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"),
                                 "Expected error message is not displayed");
        Assert.assertFalse(loginPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with invalid input");
    }
}