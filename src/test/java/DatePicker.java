import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class DatePicker
{
    WebDriver driver;
    WebDriverWait wait;

    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    DateFormat dateOnlyFormat = new SimpleDateFormat("dd");
    DateFormat monthOnlyFormat = new SimpleDateFormat("MM");
    DateFormat yearOnlyFormat = new SimpleDateFormat("yyyy");
    Date date = new Date();

    @Before
    public void Setup() throws Exception
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/datepicker");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement header = driver.findElement(By.cssSelector("h1"));
        assertEquals("Datepicker",header.getText());
    }

    @Test
    public void verifyDateByTextInput()
    {
        WebElement datePicker = driver.findElement(By.id("datepicker"));

        var currentDate = dateFormat.format(date);

        assertEquals("", datePicker.getAttribute("value"));

        datePicker.click();
        datePicker.sendKeys(currentDate);
        datePicker.sendKeys(Keys.RETURN);

        assertEquals(currentDate, datePicker.getAttribute("value"));
    }

    @Test
    public void verifyDateClick()
    {
        WebElement datePicker = driver.findElement(By.id("datepicker"));

        var currentDate = dateFormat.format(date);

        datePicker.click();

        WebElement currentDateButton = driver.findElement(By.className("today"));

        currentDateButton.click();
        assertEquals(currentDate, datePicker.getAttribute("value"));
    }

    @Test
    public void verifyDifferentMonth()
    {
        WebElement datePicker = driver.findElement(By.id("datepicker"));

        datePicker.click();

        WebElement yearPicker = driver.findElement(By.className("datepicker-switch"));

        yearPicker.click();

        List<WebElement> months = driver.findElements(By.className("month"));

        // Select the random month
        var selectedMonth = GenerateRandomMonth(months);
        months.get(selectedMonth).click();

        List<WebElement> dates = driver.findElements(By.xpath("//td[@class='day']"));

        var selectedDate = GenerateRandomDate(dates);
        dates.get(selectedDate).click();

        var finalDate = FormatFinalDate(selectedMonth, selectedDate);

        assertEquals(finalDate, datePicker.getAttribute("value"));
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }

    private int GenerateRandomMonth(List<WebElement> potentialMonths)
    {
        var currentMonth = date.getMonth();

        // Select any month but the current one
        int randomMonth;

        do {
            randomMonth = ThreadLocalRandom.current().nextInt(0, potentialMonths.size());

        }while(randomMonth == currentMonth);

        return randomMonth;
    }

    private int GenerateRandomDate(List<WebElement> potentialDates)
    {
        return ThreadLocalRandom.current().nextInt(0, potentialDates.size());
    }

    private String FormatFinalDate(int selectedMonth, int selectedDate)
    {
        String finalMonth = Integer.toString(selectedMonth + 1); // Offset dates by 1 as the values picked from the array are indexed at 0, not 1
        String finalDate = Integer.toString(selectedDate + 1);
        var selectedYear = yearOnlyFormat.format(date);

        if(selectedMonth < 9)
        {
            finalMonth = "0" + finalMonth;
        }

        if(selectedDate < 9)
        {
            finalDate = "0" + finalDate;
        }

        return finalMonth + "/" + finalDate + "/" + selectedYear;
    }
}