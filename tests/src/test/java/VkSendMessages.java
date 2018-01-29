import org.aeonbits.owner.ConfigFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Date;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

import org.aeonbits.owner.Config;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VkSendMessages {

    @Config.Sources({"file:${JAVA_CFG}/vk.properties"})
    public interface vkconfig extends Config {
        String login();

        String pass();

        String site();

        String friend();

        String friendId();
    }

    private static WebDriver driver;
    private static vkconfig cfg = ConfigFactory.create(vkconfig.class);

    @BeforeClass
    public static void openPageAndAuthorization() {
        System.setProperty("webdriver.gecko.driver", "C://webdrivers/geckodriver/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(cfg.site());
        driver.findElement(By.xpath("//*[@id='index_email']")).sendKeys(cfg.login());
        driver.findElement(By.xpath("//*[@id='index_pass']")).sendKeys(cfg.pass());
        driver.findElement(By.xpath("//*[@id='index_login_button']")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//*[@id='l_fr']//a[@href='/friends']")));
    }

    @AfterClass
    public static void logoutAndQuitDriver() {
        driver.findElement(By.xpath("//*[@id='top_profile_link']")).click();
        driver.findElement(By.xpath("//*[@id='top_logout_link']")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//*[@id='top_reg_link']")));
        driver.quit();
    }

    @Test
    public void findFriendAndWriteMessage() {
        //поиск друга и отправка сообщения
        driver.findElement(By.xpath("//*[@id='l_fr']//a[@href='/friends']")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//*[@id='s_search']")));
        driver.findElement(By.xpath("//*[@id='s_search']")).sendKeys(cfg.friend());
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//*[@id='friends_list']//a[text()='Написать сообщение']")));
        driver.findElement(By.xpath("//*[@id='friends_list']//a[text()='Написать сообщение']")).click();
        long messageDate = new Date().getTime();
        String message = "Привет" + String.valueOf(messageDate);
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("//*[@id='mail_box_editable']")));
        driver.findElement(By.xpath("//*[@id='mail_box_editable']")).sendKeys(message);
        driver.findElement(By.xpath("//*[@id='mail_box_send']")).click();
        //проверка успешности отправки сообщения
        driver.get(cfg.site() + "/im?sel=" + cfg.friendId());
        WebElement myMessage = driver.findElement(By
                .xpath(format("//*[@class='im-mess--text wall_module _im_log_body' and contains(., '%s')]", message)));
        assertThat(myMessage).isNotNull();
    }
}
