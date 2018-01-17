import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class MordaTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.gecko.driver", "C://webdrivers/geckodriver/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://yandex.ru");
    }

    @Test
    public void createSerpAndCheckTitle() {
        WebElement inputField = driver.findElement(By.id("text"));
        inputField.sendKeys("selenium");
        WebElement findButton = driver.findElement(By.cssSelector(".home-arrow__search .search2__button"));
        findButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("Яндекс",driver.getTitle());
    }

    @AfterClass
    public static void driverQuit() {
        driver.quit();
    }
}