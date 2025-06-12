package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.HeaderMenuItem;
import utils.TestDataFactory;
import utils.TestNGListener;

@Listeners(TestNGListener.class)
public class SignUpTests extends ApplicationManager {

    @BeforeMethod
    public void precondition() {
        openSignUpPage();
    }

    // Positive tests
    @Test
    public void testRegistrationFormDisplayed() {
        Assert.assertTrue(signUpPage.isRegistrationFormDisplayed(),
                "Registration form is not displayed");
    }

    @Test
    public void testUserSignUpSuccessfully() {
        UserLombok user = TestDataFactory.validUser();
        signUpPage.typeSignUpForm(user);
        Assert.assertTrue(signUpPage.popUpMessageContains("You are logged in success"));
    }

    // Negative tests
    @Test
    public void testUserAlreadyExist() {
        // create new user
        UserLombok user = TestDataFactory.validUser();
        signUpPage.typeSignUpForm(user);
        Assert.assertTrue(signUpPage.popUpMessageContains("You are logged in success"));
        signUpPage.clickBtnOkPopupMsg();
        // exit
        new HomePage(getDriver()).clickHeaderMenuItem(HeaderMenuItem.LOGOUT);
        // re-registration
        openSignUpPage();
        signUpPage.typeSignUpForm(user);
        Assert.assertTrue(signUpPage.popUpMessageContains("User already exists"));
    }

    @Test
    public void testUserSignUp_withEmptyFirstName() {
        UserLombok invalidUser = TestDataFactory.userWithoutFirstName();
        signUpPage.typeSignUpForm(invalidUser);
        Assert.assertTrue(signUpPage.errorMessageContains("Name is required"),
                "Expected error message is not displayed");
        Assert.assertFalse(signUpPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with empty email");
    }

    @Test
    public void testUserSignUp_withEmptyLastName() {
        UserLombok invalidUser = TestDataFactory.userWithoutLastName();
        signUpPage.typeSignUpForm(invalidUser);
        Assert.assertTrue(signUpPage.errorMessageContains("Last name is required"),
                "Expected error message is not displayed");
        Assert.assertFalse(signUpPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with empty email");
    }

    @Test
    public void testUserSignUp_withEmptyEmail() {
        UserLombok invalidUser = TestDataFactory.userWithoutEmail();
        signUpPage.typeSignUpForm(invalidUser);
        Assert.assertTrue(signUpPage.errorMessageContains("Email is required"),
                "Expected error message is not displayed");
        Assert.assertFalse(signUpPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with empty email");
    }

    @Test
    public void testUserSignUp_withEmptyPassword() {
        UserLombok invalidUser = TestDataFactory.userWithoutPassword();
        signUpPage.typeSignUpForm(invalidUser);
        Assert.assertTrue(signUpPage.errorMessageContains("Password is required"),
                "Expected error message is not displayed");
        Assert.assertFalse(signUpPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with empty email");
    }

    @Test
    public void testUserSignUp_withInvalidEmailFormat() {
        UserLombok invalidUser = TestDataFactory.invalidEmailNoAtSymbol();
        signUpPage.typeSignUpForm(invalidUser);
        Assert.assertTrue(signUpPage.errorMessageContains("Wrong email format"),
                "Expected error message is not displayed");
        Assert.assertFalse(signUpPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with empty email");
    }

    @Test
    public void testUserSignUp_invalidEmailDomain() {
        UserLombok invalidUser = TestDataFactory.invalidEmailNoDomain();
        signUpPage.typeSignUpForm(invalidUser);
        Assert.assertTrue(signUpPage.errorMessageContains("Wrong email format"),
                "Expected error message is not displayed");
        Assert.assertFalse(signUpPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with empty email");
    }

    @Test
    public void testUserSignUp_invalidEmailWithSpace() {
        UserLombok invalidUser = TestDataFactory.invalidEmailWithSpace();
        signUpPage.typeSignUpForm(invalidUser);
        Assert.assertTrue(signUpPage.errorMessageContains("Wrong email format"),
                "Expected error message is not displayed");
        Assert.assertFalse(signUpPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with empty email");
    }

    @Test
    public void testUserSignUp_invalidPasswordShort() {
        UserLombok invalidUser = TestDataFactory.invalidPasswordTooShort();
        signUpPage.typeSignUpForm(invalidUser);
        Assert.assertTrue(signUpPage.errorMessageContains("Password must contain minimum 8 symbols"),
                "Expected error message is not displayed");
        Assert.assertFalse(signUpPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with empty email");
    }

    // Bug - password with length 16 is accepted and the registration is successful
    @Test
    public void testUserSignUp_invalidPasswordLong() {
        UserLombok invalidUser = TestDataFactory.invalidPasswordTooLong();
        signUpPage.typeSignUpForm(invalidUser);
        Assert.assertTrue(signUpPage.errorMessageContains("Password must contain maximum 15 symbols"),
                "Expected error message is not displayed");
        Assert.assertFalse(signUpPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with empty email");
    }

    @Test
    public void testUserSignUp_invalidPasswordNoDigit() {
        UserLombok invalidUser = TestDataFactory.invalidPasswordNoDigit();
        signUpPage.typeSignUpForm(invalidUser);
        Assert.assertTrue(signUpPage.errorMessageContains
                        ("Password must contain 1 uppercase letter, " +
                                "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"),
                "Expected error message is not displayed");
        Assert.assertFalse(signUpPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with empty email");
    }

    @Test
    public void testUserSignUp_invalidPasswordNoSymbol() {
        UserLombok invalidUser = TestDataFactory.invalidPasswordNoSymbol();
        signUpPage.typeSignUpForm(invalidUser);
        Assert.assertTrue(signUpPage.errorMessageContains
                        ("Password must contain 1 uppercase letter, " +
                                "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"),
                "Expected error message is not displayed");
        Assert.assertFalse(signUpPage.btnYallaIsEnabled(),
                "Error: button Yalla enabled with empty email");
    }
}