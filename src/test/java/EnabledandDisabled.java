import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class EnabledandDisabled
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/enabled");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement header = driver.findElement(By.cssSelector("h1"));
        assertEquals("Enabled and Disabled elements",header.getText());
    }

    @Test
    public void verifyDisabledField()
    {
        WebElement disabledField = driver.findElement(By.id("disabledInput"));

        boolean isEnabled = disabledField.isEnabled();

        assertFalse(isEnabled);
    }

    @Test
    public void verifyEnabledField()
    {
        WebElement enabledField = driver.findElement(By.id("input"));

        boolean isEnabled = enabledField.isEnabled();

        assertTrue(isEnabled);
        assertEquals("", enabledField.getAttribute("value"));

        enabledField.sendKeys("Test text");

        assertEquals("Test text", enabledField.getAttribute("value"));
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}