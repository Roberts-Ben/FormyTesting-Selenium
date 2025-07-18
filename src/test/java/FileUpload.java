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

public class FileUpload
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/fileupload");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://formy-project.herokuapp.com/fileupload", URL);
    }


    @Test
    public void verifyFileUploadViaInputField()
    {
        var fileToUpload = "src/main/resources/testuploadfile/testFile.txt";
        WebElement uploadViaField = driver.findElement(By.id("file-upload-field"));

        uploadViaField.sendKeys(fileToUpload);

        assertEquals(fileToUpload, uploadViaField.getAttribute("value"));
    }

    @Test
    public void verifyReset()
    {
        var fileToUpload = "src/main/resources/testuploadfile/testFile.txt";
        WebElement uploadViaField = driver.findElement(By.id("file-upload-field"));
        WebElement resetButton = driver.findElement(By.className("btn-reset"));

        uploadViaField.sendKeys(fileToUpload);

        assertEquals(fileToUpload, uploadViaField.getAttribute("value"));

        resetButton.click();

        assertEquals("", uploadViaField.getAttribute("value"));
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}