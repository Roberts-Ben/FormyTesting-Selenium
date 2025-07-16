import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Checkboxes
{
    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> checkboxes = new ArrayList<>();

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/checkbox");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://formy-project.herokuapp.com/checkbox", URL);

        for(int i = 1; i <= 3; i++)
        {
            checkboxes.add(driver.findElement(By.id("checkbox-" + i)));
        }
    }

    @Test
    public void verifyCheckboxes()
    {
        CheckboxState(false);

        ClickCheckboxes();

        CheckboxState(true);

        ClickCheckboxes();
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }

    private void CheckboxState(boolean isChecked)
    {
        for (WebElement checkbox : checkboxes) {
            if (isChecked) {
                assertTrue(checkbox.isSelected());
            } else {
                assertFalse(checkbox.isSelected());
            }
        }
    }

    private void ClickCheckboxes()
    {
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
        }
    }
}