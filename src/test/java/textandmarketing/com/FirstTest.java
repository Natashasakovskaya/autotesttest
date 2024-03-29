package textandmarketing.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import util.ChromeDriverLoader;

import java.util.List;

/**
 * It is the first test.
 * The purpose of this test is to demonstrate teamwork using a hit control for versions
 */
public class FirstTest {

    @BeforeClass
    public void beforeClass() {
        ChromeDriverLoader.loadConfig();
    }

    @Test
    public void test() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://textandmarketing.com/shop/");

        List<WebElement> items = driver.findElements(By.cssSelector(".item"));
        Assert.assertEquals(items.size(), 16);

        driver.close();
    }
}
