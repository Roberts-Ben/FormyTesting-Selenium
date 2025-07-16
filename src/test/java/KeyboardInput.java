import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

public class KeyboardInput
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/keypress");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://formy-project.herokuapp.com/keypress", URL);
    }

    @Test
    public void verifyEnterText()
    {
        WebElement nameField = driver.findElement(By.id("name"));

        assertEquals("", nameField.getAttribute("value"));

        nameField.click();
        nameField.sendKeys("User Name");

        assertEquals("User Name", nameField.getAttribute("value"));
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}