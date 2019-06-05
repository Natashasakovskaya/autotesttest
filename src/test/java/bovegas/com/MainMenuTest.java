package bovegas.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ChromeDriverLoader;

import java.util.List;

public class MainMenuTest {

    private String SITE_URL = "https://bovegas.com/";

    @BeforeClass
    public void beforeClass() {
        ChromeDriverLoader.loadConfig();
    }

    @Test
    public void testTopMainMenu() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(this.SITE_URL);

        List<WebElement> items = driver.findElements(By.cssSelector(".top-menu-item"));


        Assert.assertEquals(items.size(), 9);
        driver.close();
    }

    @Test
    public void testTopMenuBtnGames() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(this.SITE_URL);


        WebDriver driver1 = new ChromeDriver();

        List<WebElement> items = driver.findElements(By.cssSelector(".top-menu-item a"));
        for (WebElement item : items) {
            driver1.get(item.getAttribute("href"));
        }

        Assert.assertEquals(driver.getTitle(), "The Best Online Casino - $5500 Welcome Bonus | BoVegas");

        driver1.close();
        driver.close();
    }

    @Test
    public void testAllLinks() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(this.SITE_URL);

        WebDriver driver1 = new ChromeDriver();

        List<WebElement> items = driver.findElements(By.cssSelector("a"));
        for (WebElement item : items) {
            driver1.get(item.getAttribute("href"));
        }

        driver1.close();
        driver.close();
    }

    @Test
    public void testWindowCustomerService() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(this.SITE_URL);

        WebElement item = driver.findElement(By.cssSelector(".btn.bar-btn.live-chat"));
        item.click();

        driver.findElement(By.cssSelector("iframe.open"));

        driver.close();
    }

    @Test
    public void testBtnClickContactUs()
    {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(this.SITE_URL);

        WebElement item = driver.findElement(By.cssSelector(".btn.bar-btn.phone"));
        item.click();

        Assert.assertEquals(driver.getTitle(), "Contact Us | BoVegas");

        driver.close();
    }
}
