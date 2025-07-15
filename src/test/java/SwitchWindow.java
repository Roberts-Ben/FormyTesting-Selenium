import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class SwitchWindow
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/switch-window");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement header = driver.findElement(By.cssSelector("h1"));
        assertEquals("Switch Window",header.getText());
    }

    @Test
    public void verifyNewTabAndClose()
    {
        WebElement newTabButton = driver.findElement(By.id("new-tab-button"));

        newTabButton.click();

        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        WebElement newTabHeader = driver.findElement(By.cssSelector("h1"));
        assertEquals("Welcome to Formy",newTabHeader.getText());

        driver.close();
    }

    @Test
    public void verifyNewTabAndSwitch()
    {
        WebElement newTabButton = driver.findElement(By.id("new-tab-button"));

        newTabButton.click();

        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        WebElement newTabHeader = driver.findElement(By.cssSelector("h1"));
        assertEquals("Welcome to Formy",newTabHeader.getText());

        driver.switchTo().window((String) windowHandles[0]);

        WebElement header = driver.findElement(By.cssSelector("h1"));
        assertEquals("Switch Window",header.getText());

        driver.close();
    }

    @Test
    public void verifyAlert()
    {
        WebElement alertButton = driver.findElement(By.id("alert-button"));

        alertButton.click();

        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();

        assertEquals("This is a test alert!", alertMessage);

        alert.accept();
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}