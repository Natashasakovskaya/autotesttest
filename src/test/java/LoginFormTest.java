import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class LoginFormTest {

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
    public void testLogin() {


        WebDriver webPageInBrowser = this.openWebPage();

        WebElement loginBtn = webPageInBrowser.findElement(By.cssSelector("#login-btn"));
        loginBtn.click();


        WebElement formInputUsername = webPageInBrowser.findElement(By.cssSelector("#l-username"));
        formInputUsername.sendKeys("qawsweeww");

        WebElement formInputPassword = webPageInBrowser.findElement(By.cssSelector("#l-password"));
        formInputPassword.sendKeys("eeededede");


        WebElement formLoginBtn = webPageInBrowser.findElement(By.cssSelector("#login"));
        formLoginBtn.click();


        this.wailt();
        Assert.assertTrue(webPageInBrowser
                .getCurrentUrl()
                .contains("https://lobby.slotsempire.com:2572/Lobby.aspx?SkinId=1&login=qawsweeww&token="));


        webPageInBrowser.close();
    }

    private WebDriver openWebPage()
    {
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();

        browser.get("https://stage.slotsempire.com/");

        return browser;
    }

    private void wailt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
    }

    private static boolean isWindows() {
        return os.contains("win");
    }
}
