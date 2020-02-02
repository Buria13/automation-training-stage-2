package by.epam.learn.webdriver.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PastebinPastedPage extends Page {

    @FindBy(xpath = "//div[@id='selectable']/ol")
    private WebElement pastedDataSyntaxHighlighting;

    @FindBy(tagName = "title")
    private WebElement pastedPageTitle;

    @FindBy(id = "paste_code")
    WebElement pastedText;

    public PastebinPastedPage(WebDriver driver) {
        super(driver);
    }

    public String verifyPageTitle() {
        return driver.getTitle();
    }

    public String verifySyntaxHighlighting() {
        return pastedDataSyntaxHighlighting.getAttribute("class");
    }

    public Boolean verifyEnteredText() {
        return pastedText.getText().equals(textFormInputData);
    }

}
