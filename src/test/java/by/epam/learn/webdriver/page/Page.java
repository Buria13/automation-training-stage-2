package by.epam.learn.webdriver.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Page {

    protected WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    protected Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
