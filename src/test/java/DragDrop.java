import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class DragDrop
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/dragdrop");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement header = driver.findElement(By.cssSelector("h1"));
        assertEquals("Drag the image into the box",header.getText());
    }

    @Test
    public void verifyDragDropImage()
    {
        WebElement dragImage = driver.findElement(By.id("image"));
        WebElement dropLocation = driver.findElement(By.id("box"));

        assertEquals("Drop here",dropLocation.getText());

        Actions dragDropAction = new Actions(driver);
        dragDropAction.dragAndDrop(dragImage, dropLocation).build().perform();

        assertEquals("Dropped!",dropLocation.getText());
    }

    @Test
    public void verifyIncorrectDragDrop()
    {
        WebElement dragImage = driver.findElement(By.id("image"));
        WebElement dropLocation = dragImage;
        WebElement dropLocationVerificationText = driver.findElement(By.id("box"));

        assertEquals("Drop here",dropLocationVerificationText.getText());

        Actions dragDropAction = new Actions(driver);
        dragDropAction.dragAndDrop(dragImage, dropLocation).build().perform();

        assertEquals("Drop here",dropLocationVerificationText.getText());
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}