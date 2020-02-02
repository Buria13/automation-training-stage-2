package by.epam.learn.webdriver.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePage extends Page {
    private static String HOMEPAGE_URL = "https://pastebin.com";

    @FindBy(id = "paste_code")
    private WebElement textFormInput;

    @FindBy(xpath = "//select[@name='paste_format']")
    private WebElement syntaxHighlightingSelect;

    @FindBy(xpath = "//select[@name='paste_expire_date']")
    private WebElement pasteExpirationSelect;

    @FindBy(xpath = "//input[@class='post_input']")
    private WebElement pasteNameInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public PastebinHomePage(WebDriver driver) {
        super(driver);
    }

    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public PastebinPastedPage fillFormAndCreateNewPaste() {

        textFormInput.sendKeys(textFormInputData);
        pasteNameInput.sendKeys("how to gain dominance among developers");
        selectOption(syntaxHighlightingSelect, "Bash");
        selectOption(pasteExpirationSelect, "10 Minutes");
        submitButton.click();

        return new PastebinPastedPage(driver);
    }


    private void selectOption(WebElement webElement, String option)    {
        Select select = new Select(webElement);
        select.selectByVisibleText(option);
    }
}
