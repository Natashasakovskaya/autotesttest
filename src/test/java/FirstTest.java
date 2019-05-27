import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

/**
 * It is the first test.
 * The purpose of this test is to demonstrate teamwork using a hit control for versions
 */
public class FirstTest {

    private String SITE_URL = "https://bovegas.com/";

    private static String os = System.getProperty("os.name").toLowerCase();
    private static String cwd = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        if (!isWindows()) {
            System.setProperty("webdriver.chrome.driver", cwd + "/externalVendors/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", cwd + "/externalVendors/chromedriver.exe");
        }
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

    @Test
    public void test() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://textandmarketing.com/shop/");

        List<WebElement> items = driver.findElements(By.cssSelector(".item"));
        Assert.assertEquals(items.size(), 16);

        driver.close();
    }

    private static boolean isWindows() {
        return (os.indexOf("win") >= 0);
    }
}
