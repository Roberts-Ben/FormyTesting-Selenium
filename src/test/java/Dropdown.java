import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Dropdown
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/dropdown");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://formy-project.herokuapp.com/dropdown", URL);
    }

    @Test
    public void verifyDropdownDifferentPage()
    {
        WebElement dropdownButton = driver.findElement(By.id("dropdownMenuButton"));

        dropdownButton.click();

        List<WebElement> dropDownOptions = driver.findElements(By.className("dropdown-item"));

        for(WebElement option : dropDownOptions) {
            if(option.getText().trim().contains("Drag and Drop")) {
                option.click();
            }
        }

        var currentURL = driver.getCurrentUrl();

        assertEquals("https://formy-project.herokuapp.com/dragdrop", currentURL);
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}