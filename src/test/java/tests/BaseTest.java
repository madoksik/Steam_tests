package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.MainPage;

import java.time.Duration;

abstract public class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterTest
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
