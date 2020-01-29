package by.epam.learn.webdriver.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DraftTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();


        driver.get("https://pastebin.com");

        WebElement searchInput = waitForElementLocatedBy(driver, By.id("paste_code"));
        String enteredData = "git config --global user.name  \"New Sheriff in Town\"\n" +
                "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                "git push origin master --force";
        searchInput.sendKeys(enteredData);

        WebElement dominance = waitForElementLocatedBy(driver, By.xpath("//input[@class='post_input']"));
        dominance.sendKeys("how to gain dominance among developers");

        WebElement selectElement = driver.findElement(
                By.xpath("//select[@name='paste_expire_date']"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("10 Minutes");

        List<WebElement> submitButton = driver.findElements(By.id("submit"));
        submitButton.get(0).click();

        //WebElement pastedData = driver.findElement(By.xpath("//head/title"));
        String olClass = driver.getTitle();
        System.out.println("T I T L E: " + olClass);

        Thread.sleep(4000);
        driver.quit();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
