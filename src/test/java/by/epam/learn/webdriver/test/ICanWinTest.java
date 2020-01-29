package by.epam.learn.webdriver.test;

import by.epam.learn.webdriver.page.PastebinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ICanWinTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    private void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void browserPageTitleCorrespondsToEnteredData() {

        String actualPageTitle = new PastebinHomePage(driver)
                .openPage()
                .createNewPaste()
                .verifyPageTitle();

        Assert.assertEquals(actualPageTitle, "[Bash] how to gain dominance among developers - Pastebin.com");
    }

    @Test(enabled = false)
    public void verifySyntaxHighlightingPastebinPostedPage() {

        String actualSyntaxHighlighting = new PastebinHomePage(driver)
                .openPage()
                .createNewPaste()
                .verifySyntaxHighlighting();

        Assert.assertEquals(actualSyntaxHighlighting, "bash");
    }

    @AfterMethod(alwaysRun = true)
    public void browserQuit() {
        driver.quit();
        driver = null;
    }
}
