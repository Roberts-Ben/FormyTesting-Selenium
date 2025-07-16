import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RadioButtons
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/radiobutton");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://formy-project.herokuapp.com/radiobutton", URL);
    }

    @Test
    public void verifyRadioButtons()
    {
        List<WebElement> radioButtons = driver.findElements(By.className("form-check-input"));

        CheckRadioButtonStatus(radioButtons, 0);

        radioButtons.get(1).click();

        CheckRadioButtonStatus(radioButtons, 1);
    }

    @Test
    public void verifySameRadioButton()
    {
        List<WebElement> radioButtons = driver.findElements(By.className("form-check-input"));

        CheckRadioButtonStatus(radioButtons, 0);

        radioButtons.getFirst().click();

        CheckRadioButtonStatus(radioButtons, 0);
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }

    void CheckRadioButtonStatus(List<WebElement> radioButtons, int activeButton)
    {
        for(int i = 0; i < radioButtons.size(); i++)
        {
            boolean isChecked = radioButtons.get(i).isSelected();

            if(i == activeButton)
            {
                assertTrue(isChecked);
            }
            else
            {
                assertFalse(isChecked);
            }
        }
    }
}