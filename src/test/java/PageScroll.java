import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class PageScroll
{
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/scroll");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        js = (JavascriptExecutor) driver;

        WebElement header = driver.findElement(By.cssSelector("h1"));
        assertEquals("Large page content",header.getText());
    }

    @Test
    public void verifyScroll()
    {
        WebElement nameInputField = driver.findElement(By.id("name"));

        js.executeScript("arguments[0].scrollIntoView(true);", nameInputField);

        assertTrue(nameInputField.isDisplayed());
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}