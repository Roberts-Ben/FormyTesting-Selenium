import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompleteForm
{
    WebDriver driver;
    WebDriverWait wait;

    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    Date date = new Date();

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/form");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        WebElement header = driver.findElement(By.cssSelector("h1"));
        assertEquals("Complete Web Form",header.getText());
    }

    @Test
    public void verifyCompleteForm()
    {
        WebElement firstName = driver.findElement(By.id("first-name"));
        WebElement lastName = driver.findElement(By.id("last-name"));;
        WebElement jobTitle = driver.findElement(By.id("job-title"));;
        List<WebElement> educationRadioButtons = driver.findElements(By.cssSelector("[type='radio']"));
        List<WebElement> sexCheckboxButtons = driver.findElements(By.cssSelector("[type='checkbox']"));;
        WebElement experienceDropdown = driver.findElement(By.id("select-menu"));
        WebElement datePicker = driver.findElement(By.id("datepicker"));;
        WebElement submitButton = driver.findElement(By.className("btn-primary"));;

        // Text Fields
        firstName.sendKeys("First");
        assertEquals("First", firstName.getAttribute("value"));

        lastName.sendKeys("Last");
        assertEquals("Last", lastName.getAttribute("value"));

        jobTitle.sendKeys("QA Engineer");
        assertEquals("QA Engineer", jobTitle.getAttribute("value"));

        // Radio Buttons
        for (WebElement educationRadioButton : educationRadioButtons)
        {
            boolean isSelected = educationRadioButton.isSelected();

            assertFalse(isSelected);
        }

        educationRadioButtons.getFirst().click();

        for ( int i = 0; i < educationRadioButtons.size(); i++)
        {
            boolean isSelected = educationRadioButtons.get(i).isSelected();

            if(i == 0)
            {
                assertTrue(isSelected);
            }
            else
            {
                assertFalse(isSelected);
            }
        }

        // Checkboxes
        for (WebElement sexCheckbox : sexCheckboxButtons)
        {
            boolean isSelected = sexCheckbox.isSelected();

            assertFalse(isSelected);
        }

        sexCheckboxButtons.getFirst().click();

        for ( int i = 0; i < sexCheckboxButtons.size(); i++)
        {
            boolean isSelected = sexCheckboxButtons.get(i).isSelected();

            if(i == 0)
            {
                assertTrue(isSelected);
            }
            else
            {
                assertFalse(isSelected);
            }
        }

        //Dropdown
        experienceDropdown.click();

        Select select = new Select(experienceDropdown);
        select.selectByValue("1");

        WebElement selectedOption = select.getFirstSelectedOption();
        assertEquals("0-1", selectedOption.getText());

        // Date
        var currentDate = dateFormat.format(date);

        assertEquals("", datePicker.getAttribute("value"));

        datePicker.click();
        datePicker.sendKeys(currentDate);
        datePicker.sendKeys(Keys.RETURN);

        assertEquals(currentDate, datePicker.getAttribute("value"));

        // Submit
        submitButton.click();

        // Verify confirmation page
        WebElement confirmationAlert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("alert-success"))
        );
        assertEquals("The form was successfully submitted!",confirmationAlert.getText());
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}