package ui_tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.HeaderMenuItem;
import utils.TestNGListener;

@Listeners(TestNGListener.class)
public class HomeTests extends ApplicationManager {

    HomePage homePage;

    // Positive tests
    @Test
    public void testHomePageDisplayed() {
        new HomePage(getDriver()).clickHeaderMenuItem(HeaderMenuItem.SEARCH);
        Assert.assertTrue(homePage.isHomePageDisplayed(),
                "HomePage is not displayed");
    }
}
