package by.epam.learn.webdriver.test;

import by.epam.learn.webdriver.page.PastebinHomePage;
import by.epam.learn.webdriver.page.PastebinPastedPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class ICanWinTest {

    private WebDriver driver;
    private PastebinPastedPage pastedPage;

    @BeforeTest
    private void browserSetup() {
        driver = new ChromeDriver();
        pastedPage = new PastebinHomePage(driver)
                .openPage()
                .fillFormAndCreateNewPaste();
    }

    @Test(enabled = true)
    public void verifyPageTitleCorrespondsToEnteredData() {
        String actualPageTitle = pastedPage.verifyPageTitle();
        Assert.assertEquals(actualPageTitle, "[Bash] how to gain dominance among developers - Pastebin.com");
    }

    @Test(enabled = true)
    public void verifySyntaxHighlightingPastebinPastedPage() {
        String actualSyntaxHighlighting = pastedPage.verifySyntaxHighlighting();
        Assert.assertEquals(actualSyntaxHighlighting, "bash");
    }

    @Test(enabled = true)
    public void verifyEnteredTextPastebinPastedPage() {
        Boolean textOnPastedPageCorrespondsToEnteredText = pastedPage.verifyEnteredText();
        Assert.assertTrue(textOnPastedPageCorrespondsToEnteredText);
    }

    @AfterTest
    public void browserQuit() {
        driver.quit();
        driver = null;
    }
}
