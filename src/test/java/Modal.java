import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Modal
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com/modal");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement header = driver.findElement(By.cssSelector("h1"));
        assertEquals("Modal",header.getText());
    }

    @Test
    public void verifyModalAccept()
    {
        WebElement modalHeader = driver.findElement(By.className("modal-header"));

        ConfirmModalVisibility(modalHeader, false);

        OpenModal();

        wait.until(ExpectedConditions.visibilityOf(modalHeader));
        ConfirmModalVisibility(modalHeader, true);

        WebElement modalAccept = driver.findElement(By.id("ok-button"));
        modalAccept.click();

        ConfirmModalVisibility(modalHeader, true);
    }

    @Test
    public void verifyModalClose()
    {
        WebElement modalHeader = driver.findElement(By.className("modal-header"));

        ConfirmModalVisibility(modalHeader, false);

        OpenModal();

        wait.until(ExpectedConditions.visibilityOf(modalHeader));
        ConfirmModalVisibility(modalHeader, true);

        WebElement modalClose = driver.findElement(By.id("close-button"));
        modalClose.click();

        wait.until(ExpectedConditions.invisibilityOf(modalHeader));
        ConfirmModalVisibility(modalHeader, false);
    }

    @Test
    public void verifyModalDismiss()
    {
        WebElement modalHeader = driver.findElement(By.className("modal-header"));

        ConfirmModalVisibility(modalHeader, false);

        OpenModal();

        wait.until(ExpectedConditions.visibilityOf(modalHeader));
        ConfirmModalVisibility(modalHeader, true);

        WebElement modalDismiss = driver.findElement(By.className("close"));
        modalDismiss.click();

        wait.until(ExpectedConditions.invisibilityOf(modalHeader));
        ConfirmModalVisibility(modalHeader, false);
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }

    private void OpenModal()
    {
        WebElement modalButton = driver.findElement(By.id("modal-button"));
        modalButton.click();
    }

    private void ConfirmModalVisibility(WebElement modalHeader, boolean shouldBeVisible)
    {
        if(shouldBeVisible)
        {
            assertTrue(modalHeader.isDisplayed());
        }
        else
        {
            assertFalse(modalHeader.isDisplayed());
        }

    }
}